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

import com.fasterxml.jackson.annotation.JsonProperty;

public class Discord {
    @JsonProperty("id")
    private String id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("accessToken")
    private String accessToken;
    @JsonProperty("expiresDate")
    private long expiresDate;
    @JsonProperty("refreshToken")
    private String refreshToken;
    @JsonProperty("discriminator")
    private String discriminator;

    public String getID() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public long getExpiresDate() {
        return expiresDate;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getDiscriminator() {
        return discriminator;
    }
}
