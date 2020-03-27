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

object CommandCache {
    private val cacheRegistry: HashMap<CommandExecutor?, MutableMap<String, Any>> = hashMapOf()

    fun registerCache(
        executor: CommandExecutor?,
        key: String,
        value: Any
    ) {
        val guildCache =
            cacheRegistry.computeIfAbsent(
                executor
            ) { mutableMapOf() }
        guildCache[key] = value
    }

    fun getCache(
        executor: CommandExecutor?,
        key: String
    ): Any? {
        return cacheRegistry.computeIfAbsent(
            executor
        ) { mutableMapOf() }[key]
    }

    fun getCaches(executor: CommandExecutor?): Set<Map.Entry<String, Any>> {
        return cacheRegistry.computeIfAbsent(
            executor
        ) { mutableMapOf() }.entries
    }

    fun deleteCache(
        executor: CommandExecutor?,
        key: String
    ) {
        cacheRegistry.computeIfAbsent(
            executor
        ) { mutableMapOf() }.remove(key)
    }

    fun deleteAllCache(executor: CommandExecutor?) {
        cacheRegistry.remove(executor)
    }
}