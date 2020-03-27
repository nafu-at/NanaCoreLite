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
package online.pandasoft.nanacore.lite.core.discord

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import online.pandasoft.nanacore.lite.core.command.CommandCache.getCache
import online.pandasoft.nanacore.lite.core.command.CommandPermission
import online.pandasoft.nanacore.lite.core.command.CommandRegistry
import online.pandasoft.nanacore.lite.core.command.DiscordBotCommand
import online.pandasoft.nanacore.lite.functions.moderation.UserInfo
import online.pandasoft.nanacore.lite.functions.moderation.UserInfoTable
import org.slf4j.LoggerFactory
import java.sql.SQLException

class MessageReceivedEventHandler(
    private val prefix: String,
    var registry: CommandRegistry?
) : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        // レジストリが登録されて居ない場合は無視
        if (registry == null) return

        // 自分自身の投稿, Botによる投稿, WebHookによる投稿, Botが投稿できないチャンネルへの投稿を無視
        if (event.author === event.jda.selfUser ||
            event.author.isBot ||
            event.isWebhookMessage ||
            !event.textChannel.canTalk()
        ) return

        val raw = event.message.contentRaw
        val input: String
        input = when {
            raw.startsWith(prefix) -> {
                raw.substring(prefix.length).trim { it <= ' ' }
            }
            event.message.getMentions().isNotEmpty() -> { // 自分宛てのメンションの場合はコマンドとして認識
                if (!event.message.isMentioned(event.jda.selfUser)) return
                raw.substring(event.jda.selfUser.asMention.length).trim { it <= ' ' }
            }
            else -> { // コマンドではないメッセージを無視
                return
            }
        }

        if (input.isEmpty()) return
        val commands = input.split("; ").toTypedArray()
        for (collState in commands) {
            val user: UserInfo
            try {
                val infoTable = getCache(null, "userInfoTable") as UserInfoTable?
                val info = infoTable!!.getUser(infoTable.searchUserFromDiscord(event.member!!.idLong))
                val builder = UserInfo.Builder(info["id"] as String?)
                builder.setMisskeyUser(info["misskey_user"] as String?)
                builder.setDiscordId(info["discord_id"] as Long?)
                builder.setIsAdmin(info["admin"] as Boolean)
                builder.setIsModerator(info["moderator"] as Boolean)
                user = builder.build()
            } catch (e: SQLException) {
                event.channel.sendMessage(
                    """
                                ユーザー情報の取得中にエラーが発生しました。
                                時間を置いて再度お試しください。
                                """.trimIndent()
                ).queue()
                log.error("ユーザー情報の取得中にエラーが発生しました。", e)
                return
            }

            val command = toCommand(collState, user, event)
            if (command == null) {
                event.channel.sendMessage(
                    """
                        入力されたコマンド `$collState` はななこあLiteには登録されていません。
                        ななこあLiteのコマンドについて知りたい場合は `${prefix}help` を入力してください。
                        """.trimIndent()
                ).queue()
            } else {
                log.debug("Command Received: {}", command.toString())
                if (command.command.permission !== CommandPermission.NONE) {
                    if (command.command.permission === CommandPermission.ADMIN && !command.user.isAdmin ||
                        command.command.permission === CommandPermission.MODERATOR && (!command.user.isAdmin && !command.user.isModerator)
                    ) {
                        command.channel.sendMessage("このコマンドを実行するための権限を持っていないため実行できません。").queue()
                        return
                    }
                }
                try {
                    command.command.onInvoke(command)
                } catch (e: Throwable) {
                    log.error("コマンドの実行に失敗しました。", e)
                }
            }
        }
    }

    /**
     * 受信したメッセージをBotコマンドとして使用できる形に整形します。
     *
     * @param callState 入力されたコマンド呼び出し文
     * @param event     受信したメッセージイベント
     * @return 生成されたBotコマンド
     */
    private fun toCommand(callState: String, user: UserInfo, event: MessageReceivedEvent): DiscordBotCommand? {
        // コマンドオプションを分割
        val args = callState.split(Regex("\\p{javaSpaceChar}+")).toTypedArray()
        if (args.isEmpty()) return null
        val commandTrigger = args[0]

        // コマンドクラスの取得
        val command = registry!!.getExecutor(commandTrigger.toLowerCase())
        return if (command == null) null else DiscordBotCommand(
            event.message.contentRaw,
            commandTrigger,
            args.copyOfRange(1, args.size),
            command,
            event.guild,
            event.textChannel,
            event.member,
            user,
            event.message
        )
    }

    companion object {
        private val log = LoggerFactory.getLogger(MessageReceivedEventHandler::class.java)
    }
}