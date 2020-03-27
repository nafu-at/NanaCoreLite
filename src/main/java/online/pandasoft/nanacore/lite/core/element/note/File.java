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

package online.pandasoft.nanacore.lite.core.element.note;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class File {
    @JsonProperty("id")
    private String id;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("md5")
    private String md5;
    @JsonProperty("size")
    private long size;
    @JsonProperty("isSensitive")
    private boolean isSensitive;
    @JsonProperty("properties")
    private MediaProperties properties;
    @JsonProperty("url")
    private String url;
    @JsonProperty("thumbnailUrl")
    private String thumbnailURL;
    @JsonProperty("folderId")
    private Object folderID; // TODO: 2020/03/06 形式不明
    @JsonProperty("folder")
    private Object folder; // TODO: 2020/03/06 形式不明
    @JsonProperty("user")
    private Object user; // TODO: 2020/03/06 形式不明

    public String getID() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getMd5() {
        return md5;
    }

    public long getSize() {
        return size;
    }

    public boolean getIsSensitive() {
        return isSensitive;
    }

    public MediaProperties getProperties() {
        return properties;
    }

    public String getURL() {
        return url;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public Object getFolderID() {
        return folderID;
    }

    public Object getFolder() {
        return folder;
    }

    public Object getUser() {
        return user;
    }
}
