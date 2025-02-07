package net.psunset.kwargs.collections

import kotlin.reflect.KClass

/**
 * Idea is from python.
 * In python, keyword arguments is a very convenient way to store data.
 * Though we can't make it directly be as parameters in a function.
 * We can still use that to save some data with this format.
 *
 *
 * It is like a map, but key is always [String]. And value is [Any] because it can save all data you want.
 * But there is a point that I make all function here won't return `null`.
 * I know it is too dangerous if there are some `null` data as a value in the kwargs.
 * But I think it is more convenient and intuitive.
 */
class KWArgs : LinkedHashMap<String, Any> {

    constructor(initialCapacity: Int, loadFactor: Float) : super(initialCapacity, loadFactor)
    constructor(initialCapacity: Int) : super(initialCapacity)
    constructor() : super()
    constructor(m: Map<out String, Any>) : super(m)

    /**
     * Idea from python.
     * @return `true` when the length of this kwargs is not 0; otherwise `false`.
     */
    inline fun toBool(): Boolean = !this.isEmpty()

    /**
     * Idea from python.
     * When you use `!` operator, it stands for you make it as a [Boolean].
     * @return `!this.toBool()`.
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
     * @param type The class which return value should belong to. Please ensure that the cast will be successful.
     * @return The value which is already cast to the correct class.
     */
    @Suppress("UNCHECKED_CAST")
    @JvmName("getAs")
    operator fun <T : Any> get(key: String, type: KClass<T>): T = this[key] as T

    /**
     * @param type The class which return value should belong to. Please ensure that the cast will be successful.
     * @return The value which is already cast to the correct class.
     * @see get get(key: String, type: KClass<T>)
     */
    @Suppress("UNCHECKED_CAST")
    @JvmName("getAs")
    operator fun <T : Any> get(key: String, type: Class<T>): T = this[key] as T

    /**
     * I recommend that always add the type of return value with other overload function.
     * @return The value got by key if the key exists in kwargs; otherwise defaultValue.
     * @see get get(key: String, defaultValue: T, type: KClass<T>) or get(key: String, defaultValue: T, type: Class<T>)
     */
    operator fun get(key: String, defaultValue: Any): Any = this.getOrDefault(key, defaultValue)

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
     * println("Total price is ${priceOfApple + priceOfBanana}")
     * // Result: Total price is 3
     * ```
     * @param type The class which return value should belong to. Please ensure that the cast will be successful.
     * @return The value got by key if the key exists in kwargs; otherwise defaultValue. No matter what you got, it will be cast into correct class.
     */
    @Suppress("UNCHECKED_CAST")
    @JvmName("getAs")
    operator fun <T : Any> get(key: String, defaultValue: T, type: KClass<T>): T =
        this.getOrDefault(key, defaultValue) as T

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
     * println("Total price is ${priceOfApple + priceOfBanana}")
     * // Result: Total price is 3
     * ```
     * @param type The class which return value should belong to. Please ensure that the cast will be successful.
     * @return The value got by key if the key exists in kwargs; otherwise defaultValue. No matter what you got, it will be cast into correct class.
     */
    @Suppress("UNCHECKED_CAST")
    @JvmName("getAs")
    operator fun <T : Any> get(key: String, defaultValue: T, type: Class<T>): T =
        this.getOrDefault(key, defaultValue) as T

    /**
     * The difference between this function and [remove] is that this function only accepts non-null value.
     * So please ensure that the key exists in the kwargs.
     * @see remove
     */
    fun pop(key: String): Any = this.remove(key)!!

    /**
     * @param type The class which return value should belong to. Please ensure that the cast will be successful.
     * @return The value which is already cast to the correct class.
     */
    @Suppress("UNCHECKED_CAST")
    @JvmName("popAs")
    fun <T : Any> pop(key: String, type: KClass<T>): T = this.pop(key) as T

    /**
     * @param type The class which return value should belong to. Please ensure that the cast will be successful.
     * @return The value which is already cast to the correct class.
     */
    @Suppress("UNCHECKED_CAST")
    @JvmName("popAs")
    fun <T : Any> pop(key: String, type: Class<T>): T = this.pop(key) as T

    /**
     * I recommend that always add the type of return value with other overload function.
     * The difference between this function and [remove] is that the second parameter in this function is defaultValue.
     * @return The value got by key if the key exists in kwargs; otherwise defaultValue
     * @see remove remove(Object key, Object value) (Java)
     * @see pop pop(key: String, defaultValue: T, type: KClass<T>) or pop(key: String, defaultValue: T, type: Class<T>)
     */
    fun pop(key: String, defaultValue: Any): Any = this.remove(key) ?: defaultValue

    /**
     * The difference between this function and [remove] is that the second parameter in this function is defaultValue.
     * @param type The class which return value should belong to. Please ensure that the cast will be successful.
     * @return The value got by key if the key exists in kwargs; otherwise defaultValue. No matter what you got, it will be cast into correct class.
     */
    @Suppress("UNCHECKED_CAST")
    @JvmName("popAs")
    fun <T : Any> pop(key: String, defaultValue: T, type: KClass<T>): T = (this.remove(key) as T?) ?: defaultValue

    /**
     * The difference between this function and [remove] is that the second parameter in this function is defaultValue.
     * @param type The class which return value should belong to. Please ensure that the cast will be successful.
     * @return The value got by key if the key exists in kwargs; otherwise defaultValue. No matter what you got, it will be cast into correct class.
     */
    @Suppress("UNCHECKED_CAST")
    @JvmName("popAs")
    fun <T : Any> pop(key: String, defaultValue: T, type: Class<T>): T = (this.remove(key) as T?) ?: defaultValue

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

    /**
     * Call [pop] function
     * @see pop pop(key: String)
     */
    operator fun div(key: String): Any = this.pop(key)

    /**
     * Call [pop] function
     * @param p Should be in correct format: (key to type) or Pair(key, type)
     * @see pop pop(key: String, type: KClass<T>)
     */
    @JvmName("divAsKClass")
    operator fun <T: Any> div(p: Pair<String, KClass<T>>): T = this.pop(p.first, p.second)

    /**
     * Call [pop] function
     * @param p Should be in correct format: (key to type) or Pair(key, type)
     * @see pop pop(key: String, type: Class<T>)
     */
    @JvmName("divAsClass")
    operator fun <T: Any> div(p: Pair<String, Class<T>>): T = this.pop(p.first, p.second)

    /**
     * Call [pop] function.
     * But, to be honestly, I recommend you to directly use [pop] function.
     * Because this function is too non-intuitive.
     * @param p Should be in correct format: (key to defaultValue) or Pair(key, defaultValue)
     * @see pop pop(key: String, defaultValue: Any)
     */
    operator fun div(p: Pair<String, Any>): Any = this.pop(p.first, p.second)

    /**
     * Call [pop] function
     * For example:
     * ```
     * val goodToPrice = kwargsOf("apple" to 3, "orange" to 2.5f)
     * val priceOfApple = goodToPrice / Triple("apple", 0, Int::class)
     * val priceOfBanana = goodToPrice / Triple("banana", 0, Int::class)
     * println("Total price is ${priceOfApple + priceOfBanana}") // Result: Total price is 3
     * println("Good existing now: $goodToPrice") // Result: Good existing now: {orange=2.5}
     * ```
     * @param t Should be in correct format: Triple(key, defaultValue, type)
     * @see pop pop(key: String, defaultValue: T, type: KClass<T>)
     */
    @JvmName("divAsKClass")
    operator fun <T: Any> div(t: Triple<String, T, KClass<T>>): T = this.pop(t.first, t.second, t.third)

    /**
     * Call [pop] function
     * For example:
     * ```
     * val goodToPrice = kwargsOf("apple" to 3, "orange" to 2.5f)
     * val priceOfApple = goodToPrice / Triple("apple", 0, Int::class)
     * val priceOfBanana = goodToPrice / Triple("banana", 0, Int::class)
     * println("Total price is ${priceOfApple + priceOfBanana}") // Result: Total price is 3
     * println("Good existing now: $goodToPrice") // Result: Good existing now: {orange=2.5}
     * ```
     * @param t Should be in correct format: Triple(key, defaultValue, type)
     * @see pop pop(key: String, defaultValue: T, type: Class<T>)
     */
    @JvmName("divAsClass")
    operator fun <T: Any> div(t: Triple<String, T, Class<T>>): T = this.pop(t.first, t.second, t.third)
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
    expectedSize < 0 -> expectedSize
    expectedSize < 3 -> expectedSize + 1
    expectedSize < (1 shl (Int.SIZE_BITS - 2)) -> ((expectedSize / 0.75F) + 1.0F).toInt()
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