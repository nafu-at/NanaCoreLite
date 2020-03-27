/*
 * Copyright 2020 なふちょこ.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package online.pandasoft.nanacore.lite.core.http

import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue

/**
 * リクエストは通常設定ファイルで指定されたインターバル時間の間隔を開けて実行されます。
 */
class RequestExecutor(private val intervalTime: Long, private val instanceUrl: String) : Thread() {
    private val requests: Queue<Requester> = LinkedBlockingQueue()
    private val executor = Executors.newSingleThreadExecutor()

    fun execute(context: RequestContext, resultHandler: RequestResultHandler) {
        requests.offer(Requester(instanceUrl, context, resultHandler))
    }

    override fun run() { // リクエスト実行用無限ループ
        try {
            while (true) {
                val requester = requests.poll()
                if (requester != null) {
                    log.debug(
                        "Request to {}, [{}]",
                        requester.context.endpoint,
                        requester.context.requestParameter.toString()
                    )
                    try {
                        val result = executor.submit(requester).get()
                        requester.handler.onRequestSuccess(requester.context, result)
                        requests.remove(requester)
                    } catch (e: ExecutionException) {
                        requester.handler.onRequestFailed(requester.context, e)
                        requests.remove(requester)
                    } catch (e: Throwable) {
                        log.error("Uncaught exception in listener", e)
                    }
                    sleep(intervalTime)
                }
            }
        } catch (e: InterruptedException) {
            log.info("Request executor has ended.")
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(RequestExecutor::class.java)
    }
}