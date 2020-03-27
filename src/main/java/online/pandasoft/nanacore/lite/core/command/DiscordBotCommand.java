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

package online.pandasoft.nanacore.lite.core.command;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import online.pandasoft.nanacore.lite.functions.moderation.UserInfo;
import org.jetbrains.annotations.NotNull;

public class DiscordBotCommand extends BotCommand {
    private final Guild guild;
    private final TextChannel channel;
    private final Member invoker;
    private final UserInfo user;
    private final Message message;

    public DiscordBotCommand(String commandRaw, String trigger, String[] args, CommandExecutor command, Guild guild, TextChannel channel, Member invoker, UserInfo user, Message message) {
        super(commandRaw, trigger, args, command);
        this.guild = guild;
        this.channel = channel;
        this.invoker = invoker;
        this.user = user;
        this.message = message;
    }

    /**
     * @return コマンドが実行されたギルド
     */
    public Guild getGuild() {
        return guild;
    }

    /**
     * @return コマンドが実行されたテキストチャンネル
     */
    public TextChannel getChannel() {
        return channel;
    }

    /**
     * @return コマンドを実行したメンバー
     */
    public Member getInvoker() {
        return invoker;
    }

    /**
     * @return コマンドを実行したユーザー情報
     */
    public UserInfo getUser() {
        return user;
    }

    /**
     * @return 実際に送信されたメッセージ
     */
    public Message getMessage() {
        return message;
    }

    @Override
    public void printMessage(@NotNull String message) {
        getChannel().sendMessage(message).queue();
    }

    @Override
    public String toString() {
        return "DiscordBotCommand{" +
                "commandRaw='" + getCommandRaw() + '\'' +
                ", trigger='" + getTrigger() + '\'' +
                ", args=" + getArgs() +
                ", command=" + getCommand() +
                ", guild=" + guild +
                ", channel=" + channel +
                ", invoker=" + invoker +
                ", message=" + message +
                '}';
    }
}
