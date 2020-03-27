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

import online.pandasoft.nanacore.lite.core.websocket.event.MessageReceivedEvent;
import online.pandasoft.nanacore.lite.core.websocket.event.NoteReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class ListenerAdapter implements EventListener {
    @Override
    public final void onEvent(@NotNull IWebSocketEvent event) {
        // TODO: 2020/03/20 そのうちバリエーション増やす
        onWebSocketEvent(event);

        if (event instanceof NoteReceivedEvent)
            onNoteReceivedEvent((NoteReceivedEvent) event);
        if (event instanceof MessageReceivedEvent)
            onMessageReceivedEvent((MessageReceivedEvent) event);
    }

    public void onWebSocketEvent(IWebSocketEvent event) {
    }

    public void onNoteReceivedEvent(NoteReceivedEvent event) {
    }

    public void onMessageReceivedEvent(MessageReceivedEvent event) {
    }
}
