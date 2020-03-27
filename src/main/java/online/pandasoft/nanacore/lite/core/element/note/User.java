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

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("username")
    private String username;
    @JsonProperty("host")
    private String host;
    @JsonProperty("avatarUrl")
    private String avatarURL;
    @JsonProperty("avatarColor")
    private String avatarColor;
    @JsonProperty("isBot")
    private boolean isBot;
    @JsonProperty("emojis")
    private List<Emoji> emojis;

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getHost() {
        return host;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public String getAvatarColor() {
        return avatarColor;
    }

    public boolean isBot() {
        return isBot;
    }

    public List<Emoji> getEmojis() {
        return emojis;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", host='" + host + '\'' +
                ", avatarURL='" + avatarURL + '\'' +
                ", avatarColor='" + avatarColor + '\'' +
                ", isBot=" + isBot +
                ", emojis=" + emojis +
                '}';
    }
}
