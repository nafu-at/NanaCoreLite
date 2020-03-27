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

package online.pandasoft.nanacore.lite.core.element.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import online.pandasoft.nanacore.lite.core.element.note.Emoji;
import online.pandasoft.nanacore.lite.core.element.note.Note;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {
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
    @JsonProperty("isAdmin")
    private boolean isAdmin;
    @JsonProperty("isModerator")
    private boolean isModerator;
    @JsonProperty("isBot")
    private boolean isBot;
    @JsonProperty("isCat")
    private boolean isCat;
    @JsonProperty("emojis")
    private List<Emoji> emojis;
    @JsonProperty("url")
    private String url;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("bannerUrl")
    private String bannerURL;
    @JsonProperty("bannerColor")
    private String bannerColor;
    @JsonProperty("isLocked")
    private boolean isLocked;
    @JsonProperty("isSilenced")
    private boolean isSilenced;
    @JsonProperty("isSuspended")
    private boolean isSuspended;
    @JsonProperty("description")
    private String description;
    @JsonProperty("location")
    private String location;
    @JsonProperty("birthday")
    private String birthday;
    @JsonProperty("fields")
    private List<Field> fields;
    @JsonProperty("followersCount")
    private long followersCount;
    @JsonProperty("followingCount")
    private long followingCount;
    @JsonProperty("notesCount")
    private long notesCount;
    @JsonProperty("pinnedNoteIds")
    private List<String> pinnedNoteIDS;
    @JsonProperty("pinnedNotes")
    private List<Note> pinnedNotes;
    @JsonProperty("pinnedPageId")
    private List<String> pinnedPageID;
    @JsonProperty("pinnedPage")
    private Object pinnedPage; // TODO: 2020/03/22 クラスに置き換え
    @JsonProperty("twoFactorEnabled")
    private boolean twoFactorEnabled;
    @JsonProperty("usePasswordLessLogin")
    private boolean usePasswordLessLogin;
    @JsonProperty("securityKeys")
    private boolean securityKeys;
    @JsonProperty("avatarId")
    private String avatarID;
    @JsonProperty("bannerId")
    private String bannerID;
    @JsonProperty("autoWatch")
    private boolean autoWatch;
    @JsonProperty("injectFeaturedNote")
    private boolean injectFeaturedNote;
    @JsonProperty("alwaysMarkNsfw")
    private boolean alwaysMarkNsfw;
    @JsonProperty("carefulBot")
    private boolean carefulBot;
    @JsonProperty("autoAcceptFollowed")
    private boolean autoAcceptFollowed;
    @JsonProperty("hasUnreadAnnouncement")
    private boolean hasUnreadAnnouncement;
    @JsonProperty("hasUnreadAntenna")
    private boolean hasUnreadAntenna;
    @JsonProperty("hasUnreadMessagingMessage")
    private boolean hasUnreadMessagingMessage;
    @JsonProperty("hasUnreadNotification")
    private boolean hasUnreadNotification;
    @JsonProperty("hasPendingReceivedFollowRequest")
    private boolean hasPendingReceivedFollowRequest;
    @JsonProperty("integrations")
    private Integrations integrations;

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

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isModerator() {
        return isModerator;
    }

    public boolean isBot() {
        return isBot;
    }

    public boolean isCat() {
        return isCat;
    }

    public List<Emoji> getEmojis() {
        return emojis;
    }

    public String getURL() {
        return url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getBannerURL() {
        return bannerURL;
    }

    public String getBannerColor() {
        return bannerColor;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public boolean isSilenced() {
        return isSilenced;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getBirthday() {
        return birthday;
    }

    public List<Field> getFields() {
        return fields;
    }

    public long getFollowersCount() {
        return followersCount;
    }

    public long getFollowingCount() {
        return followingCount;
    }

    public long getNotesCount() {
        return notesCount;
    }

    public List<String> getPinnedNoteIDS() {
        return pinnedNoteIDS;
    }

    public List<Note> getPinnedNotes() {
        return pinnedNotes;
    }

    public List<String> getPinnedPageID() {
        return pinnedPageID;
    }

    public Object getPinnedPage() {
        return pinnedPage;
    }

    public boolean getTwoFactorEnabled() {
        return twoFactorEnabled;
    }

    public boolean getUsePasswordLessLogin() {
        return usePasswordLessLogin;
    }

    public boolean getSecurityKeys() {
        return securityKeys;
    }

    public String getAvatarID() {
        return avatarID;
    }

    public String getBannerID() {
        return bannerID;
    }

    public boolean getAutoWatch() {
        return autoWatch;
    }

    public boolean getInjectFeaturedNote() {
        return injectFeaturedNote;
    }

    public boolean getAlwaysMarkNsfw() {
        return alwaysMarkNsfw;
    }

    public boolean getCarefulBot() {
        return carefulBot;
    }

    public boolean getAutoAcceptFollowed() {
        return autoAcceptFollowed;
    }

    public boolean hasUnreadAnnouncement() {
        return hasUnreadAnnouncement;
    }

    public boolean hasUnreadAntenna() {
        return hasUnreadAntenna;
    }

    public boolean hasUnreadMessagingMessage() {
        return hasUnreadMessagingMessage;
    }

    public boolean hasUnreadNotification() {
        return hasUnreadNotification;
    }

    public boolean hasPendingReceivedFollowRequest() {
        return hasPendingReceivedFollowRequest;
    }

    public Integrations getIntegrations() {
        return integrations;
    }
}
