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

package online.pandasoft.nanacore.lite.core.console

import online.pandasoft.nanacore.lite.core.command.BotCommand
import online.pandasoft.nanacore.lite.core.command.CommandInvoker
import online.pandasoft.nanacore.lite.core.command.CommandPermission
import online.pandasoft.nanacore.lite.core.command.CommandRegistry
import org.slf4j.LoggerFactory
import java.util.*

class ConsoleReader(private val commandRegistry: CommandRegistry) : Thread() {
    private var isShutdown = false

    override fun run() {
        val scan = Scanner(System.`in`)
        while (!isShutdown && scan.hasNextLine()) {
            val command = toCommand(scan.nextLine()) ?: continue
            val commandClass: Class<*> = command.command.javaClass
            try {
                val discordOnly = commandClass.getMethod("onInvoke", BotCommand::class.java).getAnnotation(
                    CommandInvoker::class.java
                ).discordOnly
                if (discordOnly || command.command.permission !== CommandPermission.NONE) {
                    log.warn(
                        """このコマンドはコンソールでの実行に対応していません。
                            |Discord上で実行してください。""".trimMargin()
                    )
                } else {
                    try {
                        command.command.onInvoke(command)
                    } catch (e: Throwable) {
                        log.error("コマンドの実行中に何らかのエラーが発生しました。", e)
                    }
                }
            } catch (e: NoSuchMethodException) {
                log.error("実行メソッドが見つかりませんでした。", e)
            }
        }
        scan.close()
    }

    /**
     * 受信したメッセージをBotコマンドとして使用できる形に整形します。
     *
     * @param callState 入力されたコマンド呼び出し文
     * @return 生成されたBotコマンド
     */
    private fun toCommand(callState: String): BotCommand? { // コマンドオプションを分割
        val args = callState.split("\\p{javaSpaceChar}+").toTypedArray()
        if (args.isEmpty()) return null
        val commandTrigger = args[0]
        // コマンドクラスの取得
        val command =
            commandRegistry.getExecutor(commandTrigger.toLowerCase())
        return if (command == null) null else BotCommand(
            callState,
            commandTrigger,
            args.copyOfRange(1, args.size),
            command
        )
    }

    fun shutdown() {
        isShutdown = true
    }

    companion object {
        private val log =
            LoggerFactory.getLogger(ConsoleReader::class.java)
    }
}