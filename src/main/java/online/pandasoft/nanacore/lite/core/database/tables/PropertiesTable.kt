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

package online.pandasoft.nanacore.lite.core.database.tables

import online.pandasoft.nanacore.lite.core.database.DatabaseConnector
import online.pandasoft.nanacore.lite.core.database.DatabaseTable
import java.sql.SQLException

class PropertiesTable : DatabaseTable {
    constructor(prefix: String, tablename: String, connector: DatabaseConnector) : super(
        prefix,
        tablename,
        connector
    )

    constructor(prefix: String, connector: DatabaseConnector) : super(prefix, "properties", connector)

    @Deprecated("このクラスではこのメソッドは動作しません。実行された場合はUnsupportedOperationExceptionを返します。")
    @Throws(SQLException::class)
    override fun createTableColumn(name: String, type: String) {
        throw UnsupportedOperationException()
    }

    @Deprecated("このクラスではこのメソッドは動作しません。実行された場合はUnsupportedOperationExceptionを返します。")
    @Throws(SQLException::class)
    override fun dropTableColumn(name: String) {
        throw UnsupportedOperationException()
    }

    /**
     * <table><tbody>
     * <tr><td>Name</td><td>Type</td><td>Null</td></tr>
     * <tr><td>properties</td><td>TINYTEXT</td><td>NOT NULL</td></tr>
     * <tr><td>property</td><td>LONGTEXT</td><td>NULL</td></tr>
     * </tbody></table>
     * <br></br>
     *
     * @throws SQLException テーブルの作成に失敗した場合にスローされます。
     */
    @Throws(SQLException::class)
    fun createTable() {
        createTable("properties TINYTEXT NOT NULL, property LONGTEXT NULL, PRIMARY KEY(properties(255))")
    }

    @get:Throws(SQLException::class)
    val properties: Map<String, String>
        get() {
            val map: MutableMap<String, String> = hashMapOf()
            connector.connection.use { connection ->
                connection.prepareStatement(
                    "SELECT * FROM $tablename"
                ).use { ps ->
                    ps.executeQuery().use { resultSet ->
                        while (resultSet.next()) map[resultSet.getString("properties")] =
                            resultSet.getString("property")
                        return map
                    }
                }
            }
        }

    @Throws(SQLException::class)
    fun getProperty(properties: String?): String? {
        connector.connection.use { connection ->
            connection.prepareStatement(
                "SELECT property FROM $tablename WHERE properties = ?"
            ).use { ps ->
                ps.setString(1, properties)
                ps.executeQuery().use { resultSet ->
                    while (resultSet.next()) return resultSet.getString("property")
                    return null
                }
            }
        }
    }

    @Throws(SQLException::class)
    fun setProperty(property: String?, value: String?) {
        connector.connection.use { connection ->
            connection.prepareStatement(
                "INSERT INTO " + tablename + " (properties, property) VALUES (?, ?)" +
                        "ON DUPLICATE KEY UPDATE properties = VALUES (property)"
            ).use { ps ->
                ps.setString(1, property)
                ps.setString(2, value)
                ps.execute()
            }
        }
    }

    @Throws(SQLException::class)
    fun deleteProperty(property: String?) {
        connector.connection.use { connection ->
            connection.prepareStatement(
                "DELETE FROM $tablename WHERE properties = ?"
            ).use { ps ->
                ps.setString(1, property)
                ps.execute()
            }
        }
    }
}