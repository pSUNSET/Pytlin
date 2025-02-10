package net.psunset.kwargs.collections

import kotlin.reflect.KClass

/**
 * In python, keyword arguments feature, as known as kwargs, is a very convenient way to store data.
 * Though we can't make it directly be as parameters in a function.
 * We can still use that to save some data with this format.
 *
 * It's like a map, but key is always [String]. And value is [Any]`?` because it can save all data you want.
 * All the values are nullable.
 */
class Kwargs : LinkedHashMap<String, Any?> {

    constructor(initialCapacity: Int, loadFactor: Float) : super(initialCapacity, loadFactor)
    constructor(initialCapacity: Int) : super(initialCapacity)
    constructor() : super()
    constructor(m: Map<out String, Any?>) : super(m)

    /**
     * `this.toBool()` equals to `!!this`
     * @return `true` when the kwargs isn't empty; otherwise `false`.
     */
    inline fun toBool(): Boolean = !this.isEmpty()

    /**
     * When you use `!` operator, it stands for that you hope to make it as a [Boolean].
     * @return `!this.toBool()`
     */
    inline operator fun not(): Boolean = this.isEmpty()

    /**
     * For example:
     * ```
     * val goodToPrice = kwargsOf("apple" to 3, "orange" to 2.5f)
     * ```
     * Now, we know the value from `"apple"` is 3 which is a [Int].
     * And the value from `"orange"` is 2.5f which is a [Float].
     * So we can directly cast them into right class when we need to get them.
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
    operator fun <T : Any> get(key: String, type: KClass<T>): T? = this[key] as T?

    /**
     * For example:
     * ```
     * val goodToPrice = kwargsOf("apple" to 3, "orange" to 2.5f)
     * ```
     * Now, we know the value from `"apple"` is 3 which is a [Int].
     * And the value from `"orange"` is 2.5f which is a [Float].
     * So we can directly cast them into the right class when we need to get them.
     * ```
     * val goodToPrice = kwargsOf("apple" to 3, "orange" to 2.5f)
     * val priceOfApple = goodToPrice["apple", 0]
     * val priceOfBanana = goodToPrice["banana", 0.0]
     * // It is 0.0 because there is no key named `banana` in goodToPrice.
     * println("Total price is ${priceOfApple + priceOfBanana}")
     * // Result: Total price is 3.0
     * // It means that priceOfBanana is set to the defaultValue we provide and that priceOfBanana is cast into a Double.
     * ```
     * @return The value got by key with cast into the class of defaultValue if the value is present and not `null`;
     * otherwise defaultValue
     */
    @Suppress("UNCHECKED_CAST")
    operator fun <T : Any?> get(key: String, defaultValue: T): T = (this[key] as T?) ?: defaultValue

    /**
     * In fact, this function is same as [remove].
     * @see remove remove(Object key) (Java)
     */
    fun pop(key: String): Any? = this.remove(key)

    /**
     * Get the value with the key.
     * In the meantime, key-value pairs got removed.
     * The difference between this function and [remove] is that the second parameter in target type will be returned.
     * @param type The class which return value should belong to. Please ensure that the cast will be successful.
     * @return The value got by key with cast into the correct class.
     * @see remove remove(Object key, Object value) (Java)
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> pop(key: String, type: KClass<T>): T? = this.pop(key) as T?

    /**
     * Get the value with the key.
     * In the meantime, key-value pairs got removed if the key is present.
     * The difference between this function and [remove] is that the second parameter in this function is defaultValue.
     * @return The value got by key with cast into the class of defaultValue if the value is present and not `null`;
     * otherwise defaultValue
     * @see remove remove(Object key, Object value) (Java)
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any?> pop(key: String, defaultValue: T): T = (this.remove(key) as T?) ?: defaultValue

    /**
     * Add a `Pair<String, Any?>` should return [Kwargs] instead of [Map]
     * @see Map.plus Map<out K, V>.plus(pair: Pair<K, V>)
     */
    operator fun plus(pair: Pair<String, Any?>): Kwargs = Kwargs(this).apply { put(pair.first, pair.second) }

    /**
     * Add plural `Pair<String, Any?>` should return [Kwargs] instead of [Map]
     * @see Map.plus Map<out K, V>.plus(pairs: Iterable<Pair<K, V>>)
     */
    operator fun plus(pairs: Iterable<Pair<String, Any?>>): Kwargs = Kwargs(this).apply { putAll(pairs) }

    /**
     * Add plural `Pair<String, Any?>` should return [Kwargs] instead of [Map]
     * @see Map.plus Map<out K, V>.plus(pairs: Array<out Pair<K, V>>)
     */
    operator fun plus(pairs: Array<out Pair<String, Any?>>): Kwargs = Kwargs(this).apply { putAll(pairs) }

    /**
     * Add plural `Pair<String, Any?>` should return [Kwargs] instead of [Map]
     * @see Map.plus Map<out K, V>.plus(pairs: Sequence<Pair<K, V>>)
     */
    operator fun plus(pairs: Sequence<Pair<String, Any?>>): Kwargs = Kwargs(this).apply { putAll(pairs) }

    /**
     * Add other [Kwargs] or a [Map] in correct format should return [Kwargs] instead of [Map]
     * @see Map.plus Map<out K, V>.plus(map: Map<out K, V>)
     */
    operator fun plus(other: Map<String, Any?>): Kwargs = Kwargs(this).apply { putAll(other) }

    /**
     * Remove some entries of the kwargs should return [Kwargs] instead of [Map]
     * @see Map.minus Map<out K, V>.minus(key: K)
     */
    operator fun minus(key: String): Kwargs = Kwargs(this).apply { minusAssign(key) }

    /**
     * Remove some entries of the kwargs should return [Kwargs] instead of [Map]
     * @see Map.minus Map<out K, V>.minus(keys: Iterable<K>)
     */
    operator fun minus(keys: Iterable<String>): Kwargs = Kwargs(this).apply { minusAssign(keys) }

    /**
     * Remove some entries of the kwargs should return [Kwargs] instead of [Map]
     * @see Map.minus Map<out K, V>.minus(keys: Array<out K>)
     */
    operator fun minus(keys: Array<out String>): Kwargs = Kwargs(this).apply { minusAssign(keys) }

    /**
     * Remove some entries of the kwargs should return [Kwargs] instead of [Map]
     * @see Map.minus Map<out K, V>.minus(keys: Sequence<K>)
     */
    operator fun minus(keys: Sequence<String>): Kwargs = Kwargs(this).apply { minusAssign(keys) }

    /**
     * Call [pop] function
     * @see pop pop(key: String)
     */
    operator fun div(key: String): Any? = this.pop(key)

    /**
     * Call [pop] function
     * @param keyToType Should be in correct format: `key to type` or `Pair(key, type)`
     * @see pop pop(key: String, type: KClass<T>)
     */
    @JvmName("div2PopAs")
    operator fun <T : Any> div(keyToType: Pair<String, KClass<T>>): T? = this.pop(keyToType.first, keyToType.second)

    /**
     * Call [pop] function.
     * @param keyToDefaultValue Should be in correct format: `key to defaultValue` or `Pair(key, defaultValue)`
     * @see pop pop(key: String, defaultValue: Any?)
     */
    operator fun <T : Any?> div(keyToDefaultValue: Pair<String, T>): T =
        this.pop(keyToDefaultValue.first, keyToDefaultValue.second)


    /**
     * Call [remove] function
     * @see remove remove(Object key) (Java)
     */
    operator fun divAssign(key: String) {
        this.remove(key)
    }

    /**
     * Call [pop] function
     * @param keyToType Should be in correct format: `key to type` or `Pair(key, type)`
     * @see pop pop(key: String, type: KClass<T>)
     */
    @JvmName("divAssign2PopAs")
    operator fun <T : Any> divAssign(keyToType: Pair<String, KClass<T>>) {
        this.pop(keyToType.first, keyToType.second)
    }

    /**
     * Call [pop] function.
     * @param keyToDefaultValue Should be in correct format: `key to defaultValue` or `Pair(key, defaultValue)`
     * @see pop pop(key: String, defaultValue: Any?)
     */
    operator fun <T : Any?> divAssign(keyToDefaultValue: Pair<String, T>) {
        this.pop(keyToDefaultValue.first, keyToDefaultValue.second)
    }
}

/**
 * Returns an empty new [Kwargs].
 */
inline fun kwargsOf(): Kwargs = Kwargs()


/**
 * Returns a new [Kwargs] with the specified contents, given as a list of pairs
 * where the first component is the key and the second is the value.
 *
 * If multiple pairs have the same key, the resulting map will contain the value from the last of those pairs.
 *
 * Entries of the map are iterated in the order they were specified.
 */
fun kwargsOf(vararg pairs: Pair<String, Any?>): Kwargs = Kwargs(mapCapacity(pairs.size)).apply { putAll(pairs) }

/**
 * Returns a new [Kwargs] containing all key-value pairs from the original map.
 *
 * The returned map preserves the entry iteration order of the original map.
 */
fun Map<String, Any?>.toKwargs(): Kwargs {
    return Kwargs(this)
}


/**
 * Returns a new [Kwargs] containing all key-value pairs from the original map.
 * But all keys inside are cast into [String]
 *
 * The returned map preserves the entry iteration order of the original map.
 */
@JvmName("toKwargsByStrKeys")
fun <K : Any, V : Any?> Map<K, V>.toKwargs(): Kwargs {
    return Kwargs().apply {
        for ((k, v) in this@toKwargs) {
            this@apply[k.toString()] = v
        }
    }
}