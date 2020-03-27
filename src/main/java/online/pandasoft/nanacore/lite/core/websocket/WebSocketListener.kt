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

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import online.pandasoft.nanacore.lite.core.element.message.Message
import online.pandasoft.nanacore.lite.core.element.note.Note
import online.pandasoft.nanacore.lite.core.websocket.event.MessageReceivedEvent
import online.pandasoft.nanacore.lite.core.websocket.event.NoteReceivedEvent
import org.slf4j.LoggerFactory
import java.util.*


class WebSocketListener : WebSocketListener() {
    private val listeners: MutableList<EventListener> = ArrayList()

    // TODO: 2020/03/06 なんか変な処理。どっかのタイミングで書き直すかも。
    fun addListeners(vararg listeners: EventListener?) {
        for (listener in listeners) this.listeners.add(listener!!)
    }

    fun fireEvent(event: IWebSocketEvent) {
        for (listener in listeners) {
            try {
                listener.onEvent(event)
            } catch (throwable: Throwable) {
                log.error("イベントリスナーにキャッチされない例外がありました。")
            }
        }
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        log.info("Connection Closed!")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        log.debug(text)
        val jsonObject = gson.fromJson(text, JsonObject::class.java)
        val eventType = jsonObject.get("type").asString
        val eventBody = jsonObject.get("body").asJsonObject
        val connectionId = eventBody.get("id").asString
        val messageType = eventBody.get("type").asString
        val messageBody = eventBody.get("body").toString()

        val webSocketEvent = WebSocketEvent(connectionId, eventType, eventBody.toString(), messageType, messageBody)
        fireEvent(webSocketEvent)

        // イベントの個別処理
        if (eventType == "channel") {
            when (messageType) {
                "note" -> fireEvent(NoteReceivedEvent(webSocketEvent, mapper.readValue(messageBody, Note::class.java)))
                "messagingMessage" -> fireEvent(
                    MessageReceivedEvent(
                        webSocketEvent,
                        mapper.readValue(messageBody, Message::class.java)
                    )
                )
            }
        }
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        log.info("WebSocket Opened!")
    }

    companion object {
        private val log = LoggerFactory.getLogger(WebSocketListener::class.java)
        private val gson = Gson()
        private val mapper = ObjectMapper()
    }
}