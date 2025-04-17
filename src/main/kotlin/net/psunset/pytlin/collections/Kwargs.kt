@file:JvmName("KwargsUtil")

package net.psunset.pytlin.collections

import net.psunset.pytlin.lang.not
import java.util.function.BiFunction
import kotlin.reflect.KClass

interface Kwargs : Map<String, Any?> {

    operator fun <T : Any> get(key: String, type: KClass<T>): T?

    operator fun <T : Any?> get(key: String, defaultValue: T): T

    operator fun <T : Any, D : T> get(key: String, defaultValue: D, type: KClass<T>): T
}

/**
 * Returns an empty new [Kwargs].
 */
inline fun kwargsOf(): Kwargs = MutableKwargs()


/**
 * Returns a new [Kwargs] with the specified contents, given as a list of pairs
 * where the first component is the key and the second is the value.
 *
 * If multiple pairs have the same key, the resulting map will contain the value from the last of those pairs.
 *
 * Entries of the map are iterated in the order they were specified.
 */
fun kwargsOf(vararg pairs: Pair<String, Any?>): Kwargs =
    if (!pairs) MutableKwargs() else MutableKwargs(mapOf(*pairs))

/**
 * Returns a new [Kwargs] containing all key-value pairs from the original map.
 *
 * The returned map preserves the entry iteration order of the original map.
 */
fun Map<String, Any?>.toKwargs(): Kwargs = MutableKwargs(this)


/**
 * Returns a new [Kwargs] containing all key-value pairs from the original map.
 * But all keys inside are cast into [String]
 *
 * The returned map preserves the entry iteration order of the original map.
 */
@JvmName("toKwargsByStrKeys")
fun <K : Any, V : Any?> Map<K, V>.toKwargs(): Kwargs = MutableKwargs(this.mapKeys { it.toString() })

/**
 * In python, keyword arguments feature, as known as kwargs, is a very convenient way to store data.
 * Though we can't make it directly be as parameters in a function.
 * We can still use that to save some data with this format.
 *
 * It's like a map, but key is always [String]. And value is [Any]`?` because it can save all data you want.
 * All the values are nullable.
 */
class MutableKwargs : HashMap<String, Any?>, Kwargs {

    constructor(initialCapacity: Int, loadFactor: Float) : super(initialCapacity, loadFactor)
    constructor(initialCapacity: Int) : super(initialCapacity)
    constructor() : super()
    constructor(m: Map<out String, Any?>) : super(m)

    /**
     * For example:
     * ```
     * val goodToPrice = kwargsOf("apple" to 3, "orange" to 2.5f)
     * ```
     * Now, we know the value from `"apple"` is 3 which is a [Int].
     * And the value from `"orange"` is 2.5f which is a [Float].
     * So we can directly cast them into the right class when we need them.
     * ```
     * val priceOfApple = goodToPrice["apple", Int::class]
     * // It is an integer
     * val priceOfOrange = goodToPrice["orange", Float::class]
     * // It is a float
     * println("Total price is ${priceOfApple + priceOfOrange}")
     * // Result: Total price is 5.5
     * ```
     * @param type The class which return value should belong to. Please ensure that the cast can be executed smoothly.
     * @return The value got by key with cast into the correct class.
     */
    @Suppress("UNCHECKED_CAST")
    override operator fun <T : Any> get(key: String, type: KClass<T>): T? = this[key] as T?

    /**
     * This function doesn't equal to [getOrDefault]
     * If the key-value pair is valid but the value is `null`, the return is still defaultValue.
     *
     * For example:
     * ```
     * val goodToPrice = kwargsOf("apple" to 3, "orange" to 2.5f, "guava" to null)
     * val priceOfApple = goodToPrice["apple", 0]
     * val priceOfBanana = goodToPrice["banana", 0.0]
     * // It will be 0.0 because there is no key named "banana" in goodToPrice.
     * val priceOfGuava = goodToPrice["guava", 0.0]
     * // It will be 0.0 because the value got by "guava" key in goodToPrice is null.
     * println("Apple: $priceOfApple, Banana: $priceOfBanana, Guava: $priceOfGuava")
     * // Result: Apple: 3, Banana: 0.0, Guava: 0.0
     * // It means that priceOfBanana and priceOfGuava are set to the provided defaultValue and that they're cast into a Double.
     * println("Total price is ${priceOfApple + priceOfBanana + priceOfGuava}")
     * // Result: Total price is 3.0
     * ```
     * @return The value got by key with cast into the class of defaultValue if the value is present and not `null`;
     * defaultValue otherwise
     */
    @Suppress("UNCHECKED_CAST")
    override operator fun <T : Any?> get(key: String, defaultValue: T): T = (this[key] as T?) ?: defaultValue

    /**
     * The overload function preceding this function converts the value in the map into the type of the defaultValue.
     * It's usually convenient.
     * But when the type of defaultValue is different from that of map value.
     * Using this function is necessary.
     *
     * For example:
     * ```
     * val goodToPrice = kwargsOf("apple" to 3, "orange" to 2.5f, "guava" to null)
     * val priceOfApple = goodToPrice["apple", 0.0] // The type of defaultValue is Double instead of Int
     * println(priceOfApple) // Result: 3.0
     * // Even not using the defaultValue, it is converted into Double
     * ```
     *
     * But when using this function instead.
     * ```
     * val goodToPrice = kwargsOf("apple" to 3, "orange" to 2.5f, "guava" to null)
     * val priceOfApple = goodToPrice["apple", 0.0, Number::class] // The type which both the map value and defaultValue belong to
     * println(priceOfApple) // Result: 3
     * // It's still an Int
     * ```
     */
    @Suppress("UNCHECKED_CAST")
    override operator fun <T : Any, D : T> get(key: String, defaultValue: D, type: KClass<T>): T =
        (this[key] as T?) ?: defaultValue

    /**
     * In fact, this function is same as [remove].
     * @see remove remove(Object key) (Java)
     */
    fun pop(key: String): Any? = this.remove(key)

    /**
     * Gets the value with the key.
     * In the meantime, key-value pairs got removed.
     * The difference between this function and [remove] is that the second parameter is the target type to be returned.
     * @param type The class which return value should belong to. Please ensure that the cast will be successful.
     * @return The value got by key with cast into the correct class.
     * @see remove remove(Object key, Object value) (Java)
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> pop(key: String, type: KClass<T>): T? = this.pop(key) as T?

    /**
     * Gets the value with the key.
     * In the meantime, key-value pairs got removed if the key is present.
     * The difference between this function and [remove] is that the second parameter in this function is defaultValue.
     * @return The value got by key with cast into the class of defaultValue if the value is present and not `null`;
     * defaultValue otherwise
     * @see remove remove(Object key, Object value) (Java)
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any?> pop(key: String, defaultValue: T): T = (this.remove(key) as T?) ?: defaultValue

    /**
     * Gets the value with the key.
     * In the meantime, key-value pairs got removed if the key is present.
     * The difference between this function and [remove] is that the second parameter in this function is defaultValue
     * and that the third parameter is the target type to be returned.
     * @return The value got by key with cast into the class of defaultValue if the value is present and not `null`;
     * defaultValue otherwise
     * @see remove remove(Object key, Object value) (Java)
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any, D : T> pop(key: String, defaultValue: D, type: KClass<T>): T = (this.remove(key) as T?) ?: defaultValue

    /**
     * Add a `Pair<String, Any?>` should return [MutableKwargs] instead of [Map]
     * @see Map.plus Map<out K, V>.plus(pair: Pair<K, V>)
     */
    operator fun plus(pair: Pair<String, Any?>): MutableKwargs =
        MutableKwargs(this).apply { put(pair.first, pair.second) }

    /**
     * Add plural `Pair<String, Any?>` should return [MutableKwargs] instead of [Map]
     * @see Map.plus Map<out K, V>.plus(pairs: Iterable<Pair<K, V>>)
     */
    operator fun plus(pairs: Iterable<Pair<String, Any?>>): MutableKwargs = MutableKwargs(this).apply { putAll(pairs) }

    /**
     * Add plural `Pair<String, Any?>` should return [MutableKwargs] instead of [Map]
     * @see Map.plus Map<out K, V>.plus(pairs: Array<out Pair<K, V>>)
     */
    operator fun plus(pairs: Array<out Pair<String, Any?>>): MutableKwargs = MutableKwargs(this).apply { putAll(pairs) }

    /**
     * Add plural `Pair<String, Any?>` should return [MutableKwargs] instead of [Map]
     * @see Map.plus Map<out K, V>.plus(pairs: Sequence<Pair<K, V>>)
     */
    operator fun plus(pairs: Sequence<Pair<String, Any?>>): MutableKwargs = MutableKwargs(this).apply { putAll(pairs) }

    /**
     * Add other [MutableKwargs] or a [Map] in correct format should return [MutableKwargs] instead of [Map]
     * @see Map.plus Map<out K, V>.plus(map: Map<out K, V>)
     */
    operator fun plus(other: Map<String, Any?>): MutableKwargs = MutableKwargs(this).apply { putAll(other) }

    /**
     * Removes some entries of the kwargs should return [MutableKwargs] instead of [Map]
     * @see Map.minus Map<out K, V>.minus(key: K)
     */
    operator fun minus(key: String): MutableKwargs = MutableKwargs(this).apply { minusAssign(key) }

    /**
     * Removes some entries of the kwargs should return [MutableKwargs] instead of [Map]
     * @see Map.minus Map<out K, V>.minus(keys: Iterable<K>)
     */
    operator fun minus(keys: Iterable<String>): MutableKwargs = MutableKwargs(this).apply { minusAssign(keys) }

    /**
     * Removes some entries of the kwargs should return [MutableKwargs] instead of [Map]
     * @see Map.minus Map<out K, V>.minus(keys: Array<out K>)
     */
    operator fun minus(keys: Array<out String>): MutableKwargs = MutableKwargs(this).apply { minusAssign(keys) }

    /**
     * Removes some entries of the kwargs should return [MutableKwargs] instead of [Map]
     * @see Map.minus Map<out K, V>.minus(keys: Sequence<K>)
     */
    operator fun minus(keys: Sequence<String>): MutableKwargs = MutableKwargs(this).apply { minusAssign(keys) }

    /**
     * Calls [pop] function
     * @see pop pop(key: String)
     */
    operator fun div(key: String): Any? = this.pop(key)

    /**
     * Calls [pop] function
     * @param keyToType Should be in a correct format: `key to type` or `Pair(key, type)`
     * @see pop pop(key: String, type: KClass<T>)
     */
    @JvmName("div2PopAs")
    operator fun <T : Any> div(keyToType: Pair<String, KClass<T>>): T? = this.pop(keyToType.first, keyToType.second)

    /**
     * Calls [pop] function.
     * @param keyToDefaultValue Should be in a correct format: `key to defaultValue` or `Pair(key, defaultValue)`
     * @see pop pop(key: String, defaultValue: Any?)
     */
    operator fun <T : Any?> div(keyToDefaultValue: Pair<String, T>): T =
        this.pop(keyToDefaultValue.first, keyToDefaultValue.second)

    /**
     * Calls [pop] function.
     * @param key_defaultValue_type Should be in a correct format: `Triple(key, defaultValue, type)`
     * @see pop pop(key: String, defaultValue: D, type: KClass<T>)
     */
    operator fun <T : Any, D : T> div(key_defaultValue_type: Triple<String, D, KClass<T>>): T =
        this.pop(key_defaultValue_type.first, key_defaultValue_type.second, key_defaultValue_type.third)


    /**
     * Calls [remove] function
     * @see remove remove(Object key) (Java)
     */
    operator fun divAssign(key: String) {
        this.remove(key)
    }

    /**
     * Calls [pop] function
     * @param keyToType Should be in a correct format: `key to type` or `Pair(key, type)`
     * @see pop pop(key: String, type: KClass<T>)
     */
    @Deprecated("If you don't need the value returned, you may can simply call the function without the type.")
    @JvmName("divAssignWithType")
    operator fun <T : Any> divAssign(keyToType: Pair<String, KClass<T>>) {
        this.pop(keyToType.first, keyToType.second)
    }

    /**
     * Calls [pop] function.
     * @param keyToDefaultValue Should be in a correct format: `key to defaultValue` or `Pair(key, defaultValue)`
     * @see pop pop(key: String, defaultValue: Any?)
     */
    @Deprecated("If you don't need the value returned, you may can simply call the function without the defaultValue.")
    operator fun <T : Any?> divAssign(keyToDefaultValue: Pair<String, T>) {
        this.pop(keyToDefaultValue.first, keyToDefaultValue.second)
    }

    /**
     * Calls [pop] function.
     * @param key_defaultValue_type Should be in a correct format: `key to defaultValue` or `Pair(key, defaultValue)`
     * @see pop pop(key: String, defaultValue: Any?)
     */
    @Deprecated("If you don't need the value returned, you may can simply call the function without the defaultValue and the type.")
    operator fun <T : Any, D : T> divAssign(key_defaultValue_type: Triple<String, D, KClass<T>>) {
        this.pop(key_defaultValue_type.first, key_defaultValue_type.second, key_defaultValue_type.third)
    }
}

/**
 * Returns an empty new [MutableKwargs].
 */
inline fun mutableKwargsOf(): MutableKwargs = MutableKwargs()


/**
 * Returns a new [MutableKwargs] with the specified contents, given as a list of pairs
 * where the first component is the key and the second is the value.
 *
 * If multiple pairs have the same key, the resulting map will contain the value from the last of those pairs.
 *
 * Entries of the map are iterated in the order they were specified.
 */
fun mutableKwargsOf(vararg pairs: Pair<String, Any?>): MutableKwargs =
    if (!pairs) MutableKwargs() else pairs.toMap(MutableKwargs(mapCapacity(pairs.size)))

/**
 * Returns a new [MutableKwargs] containing all key-value pairs from the original map.
 *
 * The returned map preserves the entry iteration order of the original map.
 */
fun Map<String, Any?>.toMutableKwargs(): MutableKwargs = MutableKwargs(this)


/**
 * Returns a new [MutableKwargs] containing all key-value pairs from the original map.
 * But all keys inside are cast into [String]
 *
 * The returned map preserves the entry iteration order of the original map.
 */
@JvmName("toMutableKwargsByStrKeys")
fun <K : Any, V : Any?> Map<K, V>.toMutableKwargs(): MutableKwargs =
    MutableKwargs(this.mapKeys { it.toString() })