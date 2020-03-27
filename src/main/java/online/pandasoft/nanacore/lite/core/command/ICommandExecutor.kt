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
package online.pandasoft.nanacore.lite.core.command

interface ICommandExecutor {
    /**
     * @return 登録されたコマンドの名前
     */
    val name: String?

    /**
     * @return 関連付けられたエイリアス
     */
    val aliases: List<String?>?

    /**
     * コマンドを実行します。
     *
     * @param command 実行時に使用するコマンドコンテキスト
     */
    fun onInvoke(command: BotCommand)

    /**
     * @return コマンドについての説明
     */
    val description: String?

    /**
     * @return コマンドに関するヘルプ
     */
    val help: String?

    /**
     * @return コマンドの実行権限
     */
    val permission: CommandPermission
}