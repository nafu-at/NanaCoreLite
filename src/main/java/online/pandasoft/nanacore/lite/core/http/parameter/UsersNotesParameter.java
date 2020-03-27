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

package online.pandasoft.nanacore.lite.core.http.parameter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import online.pandasoft.nanacore.lite.core.http.RequestParameter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UsersNotesParameter extends RequestParameter {
    @JsonProperty("userId")
    private String userID;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("includeReplies")
    private boolean includeReplies;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("limit")
    private long limit;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("sinceId")
    private String sinceID;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("untilId")
    private String untilID;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("sinceDate")
    private long sinceDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("untilDate")
    private long untilDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("includeMyRenotes")
    private boolean includeMyRenotes;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("withFiles")
    private boolean withFiles;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("fileType")
    private List<String> fileType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("excludeNsfw")
    private boolean excludeNsfw;

    public UsersNotesParameter(@NotNull String token) {
        super("users/notes", token);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String value) {
        this.userID = value;
    }

    public boolean getIncludeReplies() {
        return includeReplies;
    }

    public void setIncludeReplies(boolean value) {
        this.includeReplies = value;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long value) {
        this.limit = value;
    }

    public String getSinceID() {
        return sinceID;
    }

    public void setSinceID(String value) {
        this.sinceID = value;
    }

    public String getUntilID() {
        return untilID;
    }

    public void setUntilID(String value) {
        this.untilID = value;
    }

    public long getSinceDate() {
        return sinceDate;
    }

    public void setSinceDate(long value) {
        this.sinceDate = value;
    }

    public long getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(long value) {
        this.untilDate = value;
    }

    public boolean getIncludeMyRenotes() {
        return includeMyRenotes;
    }

    public void setIncludeMyRenotes(boolean value) {
        this.includeMyRenotes = value;
    }

    public boolean getWithFiles() {
        return withFiles;
    }

    public void setWithFiles(boolean value) {
        this.withFiles = value;
    }

    public List<String> getFileType() {
        return fileType;
    }


    public void setFileType(List<String> value) {
        this.fileType = value;
    }

    public boolean getExcludeNsfw() {
        return excludeNsfw;
    }

    public void setExcludeNsfw(boolean value) {
        this.excludeNsfw = value;
    }
}
