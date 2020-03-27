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

import java.util.function.Consumer
import java.util.stream.Collectors

class CommandRegistry {
    private val commands: MutableMap<String, CommandExecutor> = mutableMapOf()

    /**
     * コマンドを登録します。
     *
     * @param executor 登録するコマンドクラス
     */
    fun registerCommand(executor: CommandExecutor) {
        val name = executor.name
        commands[name] = executor
        for (alias in executor.aliases) commands[alias] = executor
    }

    /**
     * モジュールに紐付けられた特定のコマンドを削除します。
     *
     * @param name コマンドの名前
     */
    fun removeCommand(name: String?) {
        commands.remove(name)
    }

    /**
     * モジュールに紐付けられた特定のコマンドを削除します。
     *
     * @param executor コマンド実行クラス
     */
    fun removeCommand(executor: CommandExecutor) {
        commands.remove(executor.name)
        executor.aliases.forEach(Consumer { key: String? -> commands.remove(key) })
    }

    /**
     * モジュールに紐付けられたコマンドをすべて削除します。
     */
    fun removeCommands() {
        commands.clear()
    }

    /**
     * 登録されている全てのコマンドの一覧を返します。
     *
     * @return 登録されている全てのコマンド
     */
    fun getCommands(): List<CommandExecutor> {
        return commands.values.stream().distinct().collect(Collectors.toList())
    }

    /**
     * 名前から対応するコマンドクラスを取得します。
     *
     * @param name 取得したいコマンド名
     * @return 対応するコマンドクラス
     */
    fun getExecutor(name: String?): CommandExecutor? {
        return commands[name]
    }
}