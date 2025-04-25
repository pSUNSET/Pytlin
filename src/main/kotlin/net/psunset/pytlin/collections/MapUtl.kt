package net.psunset.pytlin.collections

/**
 * For example:
 * ```
 * val a = mutableKwargsOf("i" to 1, "v" to 5)
 * println(a.setDefault("x", 10)) // Result: 10
 * // The key named "x" is not present so it returns the default value.
 * println(a) // Result: {x=10, i=1, v=5}
 * // ("x" to 10) key-value pair is already saved into the map.
 * ```
 *
 * @return The value got by key if the key is present;
 * otherwise, returns the defaultValue and set the key to defaultValue in the map.
 */
@Suppress("UNCHECKED_CAST")
fun <K, V> MutableMap<K, V>.setDefault(key: K, defaultValue: V): V =
    if (key in this) (this[key] as V) else defaultValue.also { this[key] = defaultValue }

/**
 * For example:
 * ```
 * val a = mutableKwargsOf("i" to 1, "v" to 5)
 * println(a.setDefault("x", 10)) // Result: 10
 * // The key named "x" is not present so it returns the default value.
 * println(a) // Result: {x=10, i=1, v=5}
 * // ("x" to 10) key-value pair is already saved into the map.
 * ```
 *
 * @param keyToDefaultValue Should be in correct format: `key to defaultValue` or `Pair(key, defaultValue)`
 *
 * @return The value got by key if the key is present;
 * otherwise, returns the defaultValue and set the key to defaultValue in the map.
 */
fun <K, V> MutableMap<K, V>.setDefault(keyToDefaultValue: Pair<K, V>): V =
    this.setDefault(keyToDefaultValue.first, keyToDefaultValue.second)