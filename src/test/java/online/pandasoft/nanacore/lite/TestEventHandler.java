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

package online.pandasoft.nanacore.lite;

import online.pandasoft.nanacore.lite.core.websocket.IWebSocketEvent;
import online.pandasoft.nanacore.lite.core.websocket.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEventHandler extends ListenerAdapter {
    private final Logger log = LoggerFactory.getLogger(TestEventHandler.class);


    @Override
    public void onWebSocketEvent(IWebSocketEvent event) {
        super.onWebSocketEvent(event);
        log.info("ConnectionId: {}", event.getConnectionId());
        log.info("EventType: {}", event.getEventType());
        log.info("MessageType: {}", event.getMessageType());
        log.info("MessageBody: {}", event.getMessageBody());
    }


    /*
    @Override
    public void onNoteReceivedEvent(NoteReceivedEvent event) {
        super.onNoteReceivedEvent(event);
        log.info("ConnectionId: {}", event.getConnectionId());
        log.info("EventType: {}", event.getEventType());
        log.info("MessageType: {}", event.getMessageType());
        log.info("Note: Author: {}, Body: {}", event.getAuthor().getUsername(), event.getNote().toString());
    }
    */
}
