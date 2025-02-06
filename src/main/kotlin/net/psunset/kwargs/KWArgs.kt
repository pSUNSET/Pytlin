package net.psunset.kwargs

import kotlin.reflect.KClass

/**
 * Idea is from python.
 * In python, keyword arguments is a very convenient way to store data.
 * Though we can't make it directly be as parameters in a function.
 * We can still use that to save some data with this format.
 *
 * It is like a map, but key is always [String]. And value is [Any] because it can save all data you want.
 */
class KWArgs : LinkedHashMap<String, Any> {

    constructor(initialCapacity: Int, loadFactor: Float): super(initialCapacity, loadFactor)
    constructor(initialCapacity: Int): super(initialCapacity)
    constructor(): super()
    constructor(m: Map<out String, Any>): super(m)

    /**
     * Idea from python.
     * @return `true` when the length of this kwargs is not 0; otherwise `false`.
     */
    inline fun asBool(): Boolean {
        return !this.isEmpty()
    }

    /**
     * Idea from python.
     * When you use `!` operator, it stands for you make it as a [Boolean].
     * @return `!this.asBool()`.
     */
    inline operator fun not(): Boolean {
        return this.isEmpty()
    }

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
     * @param type The class which return value should belong to. Please ensure that the cast will be successful.
     * @return The value which is already cast to the correct class.
     */
    operator fun <T : Any> get(key: String, type: KClass<T>): T {
        @Suppress("UNCHECKED_CAST")
        return this[key] as T
    }

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
     * @param type The class which return value should belong to. Please ensure that the cast will be successful.
     * @return The value which is already cast to the correct class.
     */
    operator fun <T : Any> get(key: String, type: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return this[key] as T
    }

    /**
     * For example:
     * ```
     * val goodToPrice = kwargsOf("apple" to 3, "orange" to 2.5f)
     * ```
     * Now, we know the value from `"apple"` is 3 which is a [Int].
     * And the value from `"orange"` is 2.5f which is a [Float].
     * So we can directly cast them into right class when we need to get them.
     * But when we need the price of banana, it should return 0 or null because here is no banana.
     * To avoid the key not existing error, we can add a default value in getter.
     * ```
     * val priceOfApple = goodToPrice["apple", 0]
     * // It will be 3 instead of 0 because the key named "apple" exists.
     * val priceOfBanana = goodToPrice["banana", 0]
     * // It will be 0 because can't find key named "banana".
     * println("Total price is ${priceOfApple + priceOfOrange}")
     * // Result: Total price is 3
     * ```
     * @return The value got by key if the key exists in kwargs; otherwise default.
     */
    operator fun get(key: String, default: Any): Any {
        return this.getOrDefault(key, default)
    }


    /**
     * For example:
     * ```
     * val goodToPrice = kwargsOf("apple" to 3, "orange" to 2.5f)
     * ```
     * Now, we know the value from `"apple"` is 3 which is a [Int].
     * And the value from `"orange"` is 2.5f which is a [Float].
     * So we can directly cast them into right class when we need to get them.
     * But when we need the price of banana, it should return 0 or null because here is no banana.
     * To avoid the key not existing error, we can add a default value in getter.
     * ```
     * val priceOfApple = goodToPrice["apple", 0, Int::class]
     * // It will be 3 instead of 0 because the key named "apple" exists.
     * // Both 3 and 0 are integer, so we can directly cast the value returned into Int.
     * val priceOfBanana = goodToPrice["banana", 0, Int::class]
     * // It will be 0 which is a integer because can't find key named "banana".
     * println("Total price is ${priceOfApple + priceOfOrange}")
     * // Result: Total price is 3
     * ```
     * @param type The class which return value should belong to. Please ensure that the cast will be successful.
     * @return The value got by key if the key exists in kwargs; otherwise default. No matter what you got, it will be cast into correct class.
     */
    operator fun <T: Any> get(key: String, default: T, type: KClass<T>): T {
        @Suppress("UNCHECKED_CAST")
        return this.getOrDefault(key, default) as T
    }

    /**
     * Plus other [KWArgs] or a [Map] in correct format should return [KWArgs] instead of [Map]
     * @see Map.plus
     */
    operator fun plus(other: Map<String, Any>): KWArgs = KWArgs(this).apply { putAll(other) }

    /**
     * Plus plural `Pair<String, Any>` should return [KWArgs] instead of [Map]
     * @see Map.plus
     */
    operator fun plus(pairs: Sequence<Pair<String, Any>>): KWArgs = KWArgs(this).apply { putAll(pairs) }

    /**
     * Plus plural `Pair<String, Any>` should return [KWArgs] instead of [Map]
     * @see Map.plus
     */
    operator fun plus(pairs: Array<out Pair<String, Any>>): KWArgs = KWArgs(this).apply { putAll(pairs) }

    /**
     * Plus plural `Pair<String, Any>` should return [KWArgs] instead of [Map]
     * @see Map.plus
     */
    operator fun plus(pairs: Iterable<Pair<String, Any>>): KWArgs = KWArgs(this).apply { putAll(pairs) }

    /**
     * Plus a `Pair<String, Any>` should return [KWArgs] instead of [Map]
     * @see Map.plus
     */
    operator fun plus(pairs: Pair<String, Any>): KWArgs = KWArgs(this).apply { put(pairs.first, pairs.second) }

    /**
     * Minus some entries of the kwargs should return [KWArgs] instead of [Map]
     * @see Map.minus
     */
    operator fun minus(key: String): KWArgs = this.toKwargs().apply { minusAssign(key) }

    /**
     * Minus some entries of the kwargs should return [KWArgs] instead of [Map]
     * @see Map.minus
     */
    operator fun minus(keys: Array<out String>): KWArgs = this.toKwargs().apply { minusAssign(keys) }

    /**
     * Minus some entries of the kwargs should return [KWArgs] instead of [Map]
     * @see Map.minus
     */
    operator fun minus(keys: Iterable<String>): KWArgs = this.toKwargs().apply { minusAssign(keys) }

    /**
     * Minus some entries of the kwargs should return [KWArgs] instead of [Map]
     * @see Map.minus
     */
    operator fun minus(keys: Sequence<String>): KWArgs = this.toKwargs().apply { minusAssign(keys) }
}

/**
 * Returns an empty new [KWArgs].
 */
inline fun kwargsOf(): KWArgs = KWArgs()


/**
 * Returns a new [KWArgs] with the specified contents, given as a list of pairs
 * where the first component is the key and the second is the value.
 *
 * If multiple pairs have the same key, the resulting map will contain the value from the last of those pairs.
 *
 * Entries of the map are iterated in the order they were specified.
 */
fun kwargsOf(vararg pairs: Pair<String, Any>): KWArgs = KWArgs(mapCapacity(pairs.size)).apply { putAll(pairs) }

/**
 * Copy of [kotlin.collections.mapCapacity]
 */
fun mapCapacity(expectedSize: Int): Int = when {
    // We are not coercing the value to a valid one and not throwing an exception. It is up to the caller to
    // properly handle negative values.
    expectedSize < 0 -> expectedSize
    expectedSize < 3 -> expectedSize + 1
    expectedSize < (1 shl (Int.SIZE_BITS - 2)) -> ((expectedSize / 0.75F) + 1.0F).toInt()
    // any large value
    else -> Int.MAX_VALUE
}

/**
 * Returns a new [KWArgs] containing all key-value pairs from the original map.
 *
 * The returned map preserves the entry iteration order of the original map.
 */
fun Map<String, Any>.toKwargs(): KWArgs {
    return KWArgs(this)
}