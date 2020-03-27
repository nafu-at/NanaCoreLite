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

class TableRegistry {
    private val tables: MutableMap<String?, DatabaseTable> = hashMapOf()

    /**
     * データベーステーブルを登録します。
     *
     * @param table 登録するデータベーステーブル
     */
    fun registerTable(table: DatabaseTable) {
        tables[table.tablename] = table
    }

    /**
     * データベーステーブルを取得します。
     *
     * @param tablename 取得するデータベーステーブル名
     * @return 該当するデータベーステーブル, 存在しない場合はnull
     */
    fun getDatabaseTable(tablename: String?): DatabaseTable? {
        return tables[tablename]
    }

    /**
     * 登録されたデータベーステーブルを削除します。
     *
     * @param tablename 削除するデータベーステーブル名
     * @return 削除されたデータベーステーブル
     */
    fun removeTable(tablename: String?): DatabaseTable? {
        return tables.remove(tablename)
    }
}