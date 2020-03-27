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
package online.pandasoft.nanacore.lite.core.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection
import java.sql.SQLException

class DatabaseConnector(
    databaseType: DatabaseType,
    address: String,
    database: String,
    username: String,
    password: String
) {
    private val dataSource: HikariDataSource

    @get:Throws(SQLException::class)
    val connection: Connection
        get() = dataSource.connection

    fun close() {
        dataSource.close()
    }

    init {
        val hconfig = HikariConfig()
        hconfig.driverClassName = databaseType.jdbcClass
        hconfig.jdbcUrl = databaseType.addressPrefix + address + "/" + database
        hconfig.addDataSourceProperty("user", username)
        hconfig.addDataSourceProperty("password", password)
        dataSource = HikariDataSource(hconfig)
    }
}