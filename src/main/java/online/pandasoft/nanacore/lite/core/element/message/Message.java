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

package online.pandasoft.nanacore.lite.core.element.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import online.pandasoft.nanacore.lite.core.element.note.File;
import online.pandasoft.nanacore.lite.core.element.note.User;

import java.util.List;

public class Message {
    @JsonProperty("id")
    private String id;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("text")
    private String text;
    @JsonProperty("userId")
    private String userID;
    @JsonProperty("user")
    private User user;
    @JsonProperty("recipientId")
    private String recipientID;
    @JsonProperty("recipient")
    private User recipient;
    @JsonProperty("groupId")
    private String groupID;
    @JsonProperty("fileId")
    private String fileID;
    @JsonProperty("file")
    private File file;
    @JsonProperty("isRead")
    private boolean isRead;
    @JsonProperty("reads")
    private List<Object> reads; // TODO: 2020/03/25 形式不明

    public String getID() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getText() {
        return text;
    }

    public String getUserID() {
        return userID;
    }

    public User getUser() {
        return user;
    }

    public String getRecipientID() {
        return recipientID;
    }

    public User getRecipient() {
        return recipient;
    }

    public Object getGroupID() {
        return groupID;
    }


    public String getFileID() {
        return fileID;
    }

    public File getFile() {
        return file;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public List<Object> getReads() {
        return reads;
    }
}