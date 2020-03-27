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
package online.pandasoft.nanacore.lite.core.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.slf4j.LoggerFactory
import java.io.File
import java.io.IOException
import java.nio.file.Files

class ConfigLoader {
    private val configFile: File

    /**
     * ななこあに保存された設定を返します。
     *
     * @return ななこあに保存された設定。取得に失敗した場合はnull
     */
    var config: NanacoreConfig? = null
        get() = try {
            if (field == null) field =
                mapper.readValue(configFile, NanacoreConfig::class.java)
            field
        } catch (e: IOException) {
            log.error("Failed to load configuration file.", e)
            null
        }
        private set

    constructor(configFile: File) {
        this.configFile = configFile
    }

    constructor(filePath: String) {
        configFile = File(filePath)
    }

    /**
     * 指定されたパスに設定ファイルが存在するかを確認します。
     * 存在しない場合は指定されたパスに設定ファイルを生成します。
     *
     * @return 設定ファイルが存在する場合はtrue, それ以外はfalse
     */
    fun checkConfig(): Boolean {
        if (!configFile.exists()) {
            try {
                ClassLoader.getSystemResourceAsStream("config.yml")
                    .use { original -> Files.copy(original, configFile.toPath()) }
            } catch (e: IOException) {
                log.error("Failed to copy Configuration file.", e)
            }
            return false
        }
        return true
    }

    /**
     * 古い設定ファイルを削除して最新の設定ファイルを再生成します。
     */
    fun refreshConfig() {
        if (configFile.exists()) {
            val oldFile =
                File(configFile.path.replace(configFile.name, configFile.name + ".old"))
            // Remove very old configuration file.
            try {
                if (oldFile.exists()) Files.delete(oldFile.toPath())
            } catch (e: IOException) {
                log.error("Failed to delete old configuration file.", e)
            }
            // Backup old configuration file.
            try {
                Files.move(configFile.toPath(), oldFile.toPath())
            } catch (e: IOException) {
                log.error("Failed to backup of the configuration file.")
            }
            // Copy new configuration file.
            try {
                ClassLoader.getSystemResourceAsStream("config.yml")
                    .use { original -> Files.copy(original, configFile.toPath()) }
            } catch (e: IOException) {
                log.error("Failed to copy Configuration file.", e)
            }
        }
    }

    companion object {
        private val mapper = ObjectMapper(YAMLFactory())
        private val log = LoggerFactory.getLogger(ConfigLoader::class.java)
    }
}