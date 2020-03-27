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
package online.pandasoft.nanacore.lite.core.config

import com.fasterxml.jackson.annotation.JsonProperty
import online.pandasoft.nanacore.lite.core.database.DatabaseType

class DatabaseConfig {
    @JsonProperty("databaseType")
    val databaseType: DatabaseType? = null

    @JsonProperty("prefix")
    val tablePrefix: String? = null

    @JsonProperty("address")
    val address: String? = null

    @JsonProperty("database")
    val database: String? = null

    @JsonProperty("user")
    val username: String? = null

    @JsonProperty("password")
    val password: String? = null

    override fun toString(): String {
        return "DatabaseSection{" +
                "databaseType=" + databaseType +
                ", tablePrefix='" + tablePrefix + '\'' +
                ", address='" + address + '\'' +
                ", database='" + database + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}'
    }
}