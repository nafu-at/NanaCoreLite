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

package online.pandasoft.nanacore.lite.core.websocket;

import org.jetbrains.annotations.NotNull;

public class WebSocketEvent implements IWebSocketEvent {
    private final String connectionId;
    private final String eventType;
    private final String eventBody;
    private final String messageType;
    private final String messageBody;

    public WebSocketEvent(String connectionId, String eventType, String eventBody, String messageType, String messageBody) {
        this.connectionId = connectionId;
        this.eventType = eventType;
        this.eventBody = eventBody;
        this.messageType = messageType;
        this.messageBody = messageBody;
    }

    @NotNull
    @Override
    public String getConnectionId() {
        return connectionId;
    }

    @NotNull
    @Override
    public String getEventType() {
        return eventType;
    }

    @NotNull
    @Override
    public String getEventBody() {
        return eventBody;
    }

    @NotNull
    @Override
    public String getMessageType() {
        return messageType;
    }

    @NotNull
    @Override
    public String getMessageBody() {
        return messageBody;
    }

    @Override
    public String toString() {
        return "WebSocketEvent{" +
                "connectionId='" + connectionId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", eventBody='" + eventBody + '\'' +
                ", messageType='" + messageType + '\'' +
                ", messageBody='" + messageBody + '\'' +
                '}';
    }
}
