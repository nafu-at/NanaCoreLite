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

import java.sql.SQLException

abstract class DatabaseTable(
    prefix: String,
    tablename: String,
    connector: DatabaseConnector
) {
    val tablename: String = prefix + tablename
    protected val connector: DatabaseConnector = connector

    /**
     * 指定された構造のテーブルを作成します。既に同名のテーブルが存在する場合は処理を実行せずに終了します。
     *
     * @param construction 作成するテーブルの構造
     * @throws SQLException テーブルの作成に失敗した場合にスローされます。
     */
    @Throws(SQLException::class)
    open fun createTable(construction: String) {
        connector.connection.use { connection ->
            connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS $tablename ($construction)"
            ).use { ps -> ps.execute() }
        }
    }

    /**
     * テーブルにカラムを追加します。
     *
     * @param name 追加するカラムの名前
     * @param type カラムのデータ型
     * @throws SQLException カラムの追加に失敗した場合にスローされます。
     */
    @Throws(SQLException::class)
    open fun createTableColumn(name: String, type: String) {
        connector.connection.use { connection ->
            connection.prepareStatement(
                "ALTER TABLE $tablename ADD $name $type"
            ).use { ps -> ps.execute() }
        }
    }

    /**
     * テーブルからカラムを削除します。
     *
     * @param name 削除するカラムの名前
     * @throws SQLException カラムの削除に失敗した場合にスローされます。
     */
    @Throws(SQLException::class)
    open fun dropTableColumn(name: String) {
        connector.connection.use { connection ->
            connection.prepareStatement(
                "ALTER TABLE $tablename DROP $name"
            ).use { ps -> ps.execute() }
        }
    }
}