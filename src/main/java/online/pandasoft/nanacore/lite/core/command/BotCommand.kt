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

import java.util.*

open class BotCommand(
    /**
     * @return 実際に送信された生の文字列
     */
    val commandRaw: String,
    /**
     * @return 実行されたコマンド名
     */
    val trigger: String,
    /**
     * @return 指定されたオプション
     */
    val args: Array<String>,
    /**
     * @return 送信されたコマンド名に該当するコマンドクラス
     */
    val command: CommandExecutor
) {

    open fun printMessage(message: String) {
        println(message)
    }

    override fun toString(): String {
        return "BotCommand{" +
                "commandRaw='" + commandRaw + '\'' +
                ", trigger='" + trigger + '\'' +
                ", args=" + Arrays.toString(args) +
                ", command=" + command +
                '}'
    }
}