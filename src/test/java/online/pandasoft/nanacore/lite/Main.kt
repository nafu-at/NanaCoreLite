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

package online.pandasoft.nanacore.lite

import online.pandasoft.nanacore.lite.core.websocket.HttpWebSocketClient
import org.slf4j.LoggerFactory
import java.util.*

object Main {
    private val log = LoggerFactory.getLogger(Main::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        val url = "nijimiss.moe"
        val token = "uwOG9vBeskqjHTBc"
        val client = HttpWebSocketClient(url, token)

        client.connect()
        client.sendMessage(
            "{\"type\": \"connect\",\"body\": {\"channel\": \"main\",\"id\": " +
                    "\"%CONNECTION_ID%\"}}".replaceFirst("%CONNECTION_ID%", getUUID())
        )
        /*
        client.sendMessage(
            "{\"type\": \"connect\",\"body\": {\"channel\": \"homeTimeline\",\"id\": " +
                    "\"%CONNECTION_ID%\"}}".replaceFirst("%CONNECTION_ID%", getUUID())
        )
        client.sendMessage(
            "{\"type\": \"connect\",\"body\": {\"channel\": \"localTimeline\",\"id\": " +
                    "\"%CONNECTION_ID%\"}}".replaceFirst("%CONNECTION_ID%", getUUID())
        )
        client.sendMessage(
            "{\"type\": \"connect\",\"body\": {\"channel\": \"hybridTimeline\",\"id\": " +
                    "\"%CONNECTION_ID%\"}}".replaceFirst("%CONNECTION_ID%", getUUID())
        )
        */
        client.sendMessage(
            "{\"type\": \"connect\",\"body\": {\"channel\": \"globalTimeline\",\"id\": " +
                    "\"%CONNECTION_ID%\"}}".replaceFirst("%CONNECTION_ID%", getUUID())
        )

        client.addListeners(TestEventHandler())
    }

    private fun getUUID(): String {
        return UUID.randomUUID().toString().replace("-".toRegex(), "")
    }
}
