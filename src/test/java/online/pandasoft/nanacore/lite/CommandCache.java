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

package online.pandasoft.nanacore.lite;

import online.pandasoft.nanacore.lite.core.command.CommandExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandCache {
    private final static HashMap<CommandExecutor, HashMap<String, Object>> cacheRegistry = new HashMap<>();

    public static void registerCache(@Nullable CommandExecutor executor, @NotNull String key, @NotNull Object value) {
        cacheRegistry.computeIfAbsent(executor, k -> new HashMap<>()).put(key, value);
    }

    public static Object getCache(@Nullable CommandExecutor executor, @NotNull String key) {
        return cacheRegistry.computeIfAbsent(executor, k -> new HashMap<>()).get(key);
    }

    public static Set<Map.Entry<String, Object>> getCaches(@Nullable CommandExecutor executor) {
        return cacheRegistry.computeIfAbsent(executor, k -> new HashMap<>()).entrySet();
    }
}
