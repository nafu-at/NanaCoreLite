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

package online.pandasoft.nanacore.lite.core.websocket

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import java.io.IOException
import java.util.concurrent.TimeUnit

class HttpWebSocketClient(private val instanceUrl: String, private val accessToken: String) {
    private val client = OkHttpClient.Builder().readTimeout(120, TimeUnit.SECONDS).build()
    private val webSocketListener = WebSocketListener()
    private var webSocket: WebSocket? = null

    fun connect() {
        val url = "wss://$instanceUrl/streaming?i=$accessToken"
        val request = Request.Builder().url(url).build()
        webSocket = client.newWebSocket(request, webSocketListener)
    }

    @Throws(IOException::class)
    fun sendMessage(message: String?) {
        try {
            webSocket!!.send(message!!)
        } catch (e: NullPointerException) {
            throw IllegalStateException("WebSocketとの接続が確率されていません。", e)
        }
    }

    fun addListeners(vararg listeners: EventListener?) {
        webSocketListener.addListeners(*listeners)
    }

    fun close() {
        client.dispatcher.executorService.shutdown()
    }
}