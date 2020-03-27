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
package online.pandasoft.nanacore.lite

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import online.pandasoft.nanacore.lite.core.command.CommandCache
import online.pandasoft.nanacore.lite.core.command.CommandRegistry
import online.pandasoft.nanacore.lite.core.config.AuthType
import online.pandasoft.nanacore.lite.core.config.ConfigLoader
import online.pandasoft.nanacore.lite.core.console.ConsoleReader
import online.pandasoft.nanacore.lite.core.database.DatabaseConnector
import online.pandasoft.nanacore.lite.core.database.tables.PropertiesTable
import online.pandasoft.nanacore.lite.core.discord.MessageReceivedEventHandler
import online.pandasoft.nanacore.lite.core.http.RequestExecutor
import online.pandasoft.nanacore.lite.functions.moderation.UserInfoTable
import online.pandasoft.nanacore.lite.functions.moderation.UserManageCommand
import online.pandasoft.nanacore.lite.functions.moderation.UserRegisterCommand
import online.pandasoft.nanacore.lite.functions.moderation.UserShowCommand
import online.pandasoft.nanacore.lite.functions.moderation.logging.ModerationLogTable
import online.pandasoft.nanacore.lite.functions.system.HelpCommand
import online.pandasoft.nanacore.lite.functions.system.ShutdownCommand
import org.slf4j.LoggerFactory
import java.io.File

object Main {
    private val log = LoggerFactory.getLogger(Main::class.java)
    private var configPath = "config.yml"

    @JvmStatic
    fun main(args: Array<String>) {
        log.info("Hello! Welcome to NanaCoreLite.")
        log.info("システムの初期化を開始します。お茶を飲みながらお待ちください。")

        var debugMode = false
        for (prop in args) {
            when (prop.toLowerCase()) {
                "debug" -> debugMode = true
                else -> configPath = prop
            }
        }

        if (debugMode) {
            val root =
                LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
            val cpLogger =
                LoggerFactory.getLogger("com.zaxxer.hikari") as Logger
            val jdaLogger =
                LoggerFactory.getLogger("net.dv8tion") as Logger
            root.level = Level.DEBUG
            cpLogger.level = Level.DEBUG
            jdaLogger.level = Level.DEBUG
        }

        log.info("起動に必要なファイルの確認をしています...。")
        log.debug("設定ファイルをチェック中...")
        val configLoader = ConfigLoader(File(configPath))
        if (!configLoader.checkConfig())
            Runtime.getRuntime().exit(0)
        val config = configLoader.config
        log.debug("========　NanaCore Configuration ========")
        log.debug(config.toString())
        log.info("設定ファイルがロードされました。")

        if (config!!.authConfig!!.key == "Your account token or application secret") {
            log.info("設定ファイルの内容が正しく有りません。設定を正しく行って下さい。")
            Runtime.getRuntime().exit(0)
        }
        CommandCache.registerCache(null, "token", config.authConfig!!.key!!)
        CommandCache.registerCache(null, "instanceUrl", config.basicConfig!!.instanceUrl!!)

        log.info("データベースとの通信を開始しています...")
        val databaseConnector: DatabaseConnector
        val databaseConfig = config.authConfig.databaseConfig
        databaseConnector = DatabaseConnector(
            databaseConfig!!.databaseType!!, databaseConfig.address!!,
            databaseConfig.database!!, databaseConfig.username!!, databaseConfig.password!!
        )
        log.info("データベースとの接続の確立に成功しました。")
        val propertiesTable = PropertiesTable(databaseConfig.tablePrefix!!, databaseConnector)
        val userInfoTable = UserInfoTable(databaseConfig.tablePrefix, databaseConnector)
        val moderationLogTable = ModerationLogTable(databaseConfig.tablePrefix, databaseConnector)
        propertiesTable.createTable()
        userInfoTable.createTable()
        moderationLogTable.createTable()
        log.info("データベースの準備が完了しました。")
        CommandCache.registerCache(null, "propertiesTable", propertiesTable)
        CommandCache.registerCache(null, "userInfoTable", userInfoTable)
        CommandCache.registerCache(null, "moderationLogTable", moderationLogTable)

        if (config.authConfig.authType == AuthType.OAUTH) {
            log.error("この機能は現在提供されていないため使用することができません。")
            Runtime.getRuntime().exit(1)
        }

        val requestExecutor: RequestExecutor
        requestExecutor = RequestExecutor(config.basicConfig.queueInterval, config.basicConfig.instanceUrl!!)
        Thread(requestExecutor, "RequestExecutorThread").start()
        CommandCache.registerCache(null, "requestExecutor", requestExecutor)

        if (config.authConfig.discordToken == "Your account token here!") {
            log.info("設定ファイルの内容が正しく有りません。設定を正しく行って下さい。")
            Runtime.getRuntime().exit(0)
        }

        log.info("Discord APIとの接続を開始しています...")
        var discordApi: JDA
        discordApi = JDABuilder.createDefault(config.authConfig.discordToken).build().awaitReady()
        log.debug("Discord Api Ping! {}ms", discordApi.gatewayPing)
        log.info("Discord APIの準備が完了しました。")

        val commandRegistry = CommandRegistry()
        discordApi.addEventListener(MessageReceivedEventHandler(config.basicConfig.discordPrefix!!, commandRegistry))
        val consoleReader = ConsoleReader(commandRegistry)
        Thread(consoleReader, "ConsoleReaderThread").start()

        Runtime.getRuntime().addShutdownHook(Thread(Runnable {
            log.info("シャットダウンしています...")
            consoleReader.shutdown()
            discordApi.shutdown()
            databaseConnector.close()
            log.info("See you again!")
        }))
    }
}