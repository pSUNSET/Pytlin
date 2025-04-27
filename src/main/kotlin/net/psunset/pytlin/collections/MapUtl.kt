@file:JvmName("MapUtil")

package net.psunset.pytlin.collections

import kotlin.reflect.KClass

/**
 * For example:
 * ```
 * val a = mutableKwargsOf("i" to 1, "v" to 5)
 * println(a.setDefault("x", 10)) // Result: 10
 * // The key named "x" is not present, so it returns the default value.
 * println(a) // Result: {x=10, i=1, v=5}
 * // ("x" to 10) key-value pair is already saved into the map.
 * println(a.setDefault("x", 20)) // Result: 10
 * // The key named "x" is present now, so it returns the value with the specified key.
 * ```
 *
 * @return the value of the item with the specified key.
 * If the key does not exist, insert the key, with the specified value.
 */
@Suppress("UNCHECKED_CAST")
fun <K, V> MutableMap<K, V>.setDefault(key: K, defaultValue: V): V =
    if (key in this) (this[key] as V) else defaultValue.also { this.put(key, defaultValue) }

/**
 * Alias for [MutableMap.setDefault]
 *
 * @param keyToDefaultValue Must be in correct format: `key to defaultValue` or `Pair(key, defaultValue)`
 *
 * @return the value of the item with the specified key.
 * If the key does not exist, insert the key, with the specified value.
 */
fun <K, V> MutableMap<K, V>.setDefault(keyToDefaultValue: Pair<K, V>): V =
    this.setDefault(keyToDefaultValue.first, keyToDefaultValue.second)

/**
 * Runs [Map.get] and casts the result to the specified type.
 */
@Suppress("UNCHECKED_CAST")
fun <K, V, T : Any> Map<out K, V>.getAs(key: K, type: KClass<T>): T? = this[key] as T?

/**
 * Runs [Map.get]
 * If the result is null, this function returns the default value instead.
 */
operator fun <K, V, T : V & Any> Map<out K, V>.get(key: K, defaultValue: T): T =
    this.getOrDefault(key, defaultValue) as T

/**
 * Runs [Map.get] and casts the result to the specified type.
 * If the result is null, this function returns the default value instead.
 */
@Suppress("UNCHECKED_CAST")
operator fun <K, V, T : Any, D : T> Map<out K, V>.get(key: K, defaultValue: D, type: KClass<T>): T =
    this.getOrDefault(key, defaultValue) as T

/**
 * Alias for [MutableMap.remove]
 */
fun <K, V> MutableMap<out K, V>.pop(key: K): V? = this.remove(key)

/**
 * Runs [MutableMap.remove] and casts the result to the specified type.
 */
@Suppress("UNCHECKED_CAST")
fun <K, V, T : V & Any> MutableMap<out K, V>.popAs(key: K, type: KClass<T>): T? = this.remove(key) as T?

/**
 * Runs [MutableMap.remove].
 * If the result is null, this function returns the default value instead.
 */
@Suppress("UNCHECKED_CAST")
fun <K, V, T : V> MutableMap<out K, V>.pop(key: K, defaultValue: T): T = (this.remove(key) as T?) ?: defaultValue

/**
 * Runs [MutableMap.remove] and casts the result to the specified type.
 * If the result is null, this function returns the default value instead.
 */
@Suppress("UNCHECKED_CAST")
fun <K, V, T : V & Any, D : T> MutableMap<out K, V>.pop(key: K, defaultValue: D, type: KClass<T>): T =
    (this.remove(key) as T?) ?: defaultValue

/**
 * Alias for [MutableMap.pop]
 */
operator fun <K, V> MutableMap<out K, V>.div(key: K): V? = this.remove(key)

/**
 * Alias for [MutableMap.pop]
 * @param keyToDefaultValue Must be in a correct format: `key to defaultValue` or `Pair(key, defaultValue)`
 */
operator fun <K, V, T : V> MutableMap<out K, V>.div(keyToDefaultValue: Pair<K, T>): T =
    this.pop(keyToDefaultValue.first, keyToDefaultValue.second)

/**
 * Alias for [MutableMap.pop]
 * @param key_defaultValue_type Must be in a correct format: `Triple(key, defaultValue, type)`
 */
operator fun <K, V, T : V & Any, D : T> MutableMap<out K, V>.div(key_defaultValue_type: Triple<String, D, KClass<T>>): T =
    this.pop(key_defaultValue_type.first, key_defaultValue_type.second, key_defaultValue_type.third)

/**
 * Calls [MutableMap.pop] without returning the result.
 */
operator fun <K, V> MutableMap<out K, V>.divAssign(key: K) {
    this.pop(key)
}

/**
 * Calls [MutableMap.pop] without returning the result.
 * @param keyToDefaultValue Must be in a correct format: `key to defaultValue` or `Pair(key, defaultValue)`
 */
@Deprecated("If you don't need the value returned, you may can simply call the function without the defaultValue.")
operator fun <K, V, T : V> MutableMap<out K, V>.divAssign(keyToDefaultValue: Pair<K, T>) {
    this.pop(keyToDefaultValue.first, keyToDefaultValue.second)
}

/**
 * Calls [MutableMap.pop] without returning the result.
 * @param key_defaultValue_type Must be in a correct format: `Triple(key, defaultValue, type)`
 */
@Deprecated("If you don't need the value returned, you may can simply call the function without the defaultValue and the type.")
operator fun <K, V, T : V & Any, D : T> MutableMap<out K, V>.divAssign(key_defaultValue_type: Triple<String, D, KClass<T>>) {
    this.pop(key_defaultValue_type.first, key_defaultValue_type.second, key_defaultValue_type.third)
}
