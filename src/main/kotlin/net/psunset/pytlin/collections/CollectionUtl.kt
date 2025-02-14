package net.psunset.pytlin.collections

import java.util.stream.DoubleStream
import java.util.stream.IntStream
import java.util.stream.LongStream
import java.util.stream.Stream
import kotlin.experimental.ExperimentalTypeInference

/**
 * For example:
 * ```
 * val list = mutableListOf(0, 1, 2)
 * val list2 = list * 2
 * println(list) // Result: [0, 1, 2]
 * // It stands for list isn't changed though you use this function or operator.
 * println(list2) // Result: [0, 1, 2, 0, 1, 2]
 * // It is successful!
 * ```
 * @return [EmptyList] if `n` is less than 1; otherwise, A new list which has `n` elements in the raw collection.
 */
operator fun <T> List<T>.times(n: Int): List<T> {
    if (n <= 0) return emptyList()
    if (n == 1) return this.toList()

    val newList = this.toMutableList()
    for (i in 1 until n) {
        newList += this
    }
    return newList
}

/**
 * The feature is same as [List.times] function which is preceding this function.
 * But make it available in an array.
 */
inline operator fun <reified T> Array<T>.times(n: Int): Array<T> {
    if (n <= 0) throw IllegalArgumentException("`n` should be a positive value because the length of an array should be greater than 0.")
    if (n == 1) return this.clone()

    val newList = this.toMutableList()
    for (i in 1 until n) {
        newList += this
    }
    return newList.toTypedArray()
}

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [ByteArray].
 */
operator fun ByteArray.times(n: Int): ByteArray = (this.toTypedArray() * n).toByteArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [CharArray].
 */
operator fun CharArray.times(n: Int): CharArray = (this.toTypedArray() * n).toCharArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [ShortArray].
 */
operator fun ShortArray.times(n: Int): ShortArray = (this.toTypedArray() * n).toShortArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [IntArray].
 */
operator fun IntArray.times(n: Int): IntArray = (this.toTypedArray() * n).toIntArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [LongArray].
 */
operator fun LongArray.times(n: Int): LongArray = (this.toTypedArray() * n).toLongArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [FloatArray].
 */
operator fun FloatArray.times(n: Int): FloatArray = (this.toTypedArray() * n).toFloatArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [DoubleArray].
 */
operator fun DoubleArray.times(n: Int): DoubleArray = (this.toTypedArray() * n).toDoubleArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [BooleanArray].
 */
operator fun BooleanArray.times(n: Int): BooleanArray = (this.toTypedArray() * n).toBooleanArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [UByteArray].
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.times(n: Int): UByteArray = (this.toTypedArray() * n).toUByteArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [UShortArray].
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.times(n: Int): UShortArray = (this.toTypedArray() * n).toUShortArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [UIntArray].
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.times(n: Int): UIntArray = (this.toTypedArray() * n).toUIntArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [ULongArray].
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.times(n: Int): ULongArray = (this.toTypedArray() * n).toULongArray()

/**
 * If `n` is less than 1, this mutable list object will be empty;
 * otherwise, this list will be stretch and the data inside is duplicated.
 * For example:
 * ```
 * val list = mutableListOf(0, 1, 2)
 * list *= 3
 * println(list) // Result: [0, 1, 2, 0, 1, 2, 0, 1, 2]
 * list *= 0
 * println(list) // Result: []
 * ```
 * In the example, I use `val` instead `var`, but it's still working after I call `*=` operator.
 * It's because that we make the list directly update the elements without changing the list's pointer.
 *
 * But If you're using an immutable list, the compiler will crash.
 * Because, in fact, it calls [List.times] first, and then update its pointer.
 * ```
 * val list = listOf(0, 1, 2)
 * list *= 3 // Error: val cannot be reassigned
 * ```
 * There are two solutions: one is using `var` instead, the other is make the list a mutable list by [toMutableList]
 * ```
 * var list = listOf(0, 1, 2)
 * // or
 * val list = listOf(0, 1, 2).toMutableList()
 * ```
 */
operator fun <T> MutableList<T>.timesAssign(n: Int) {
    if (n <= 0) {
        this.removeAll { true }
        return
    }
    if (n == 1) return

    val elements = this.toList()
    for (i in 1 until n) {
        this.addAll(elements)
    }
}

/**
 * A static function which is like `sum(iterable)` in python and [max]`(a, b)` in kotlin.
 * But I think that using `iter.sum()` function which is below this function is a better way.
 * Also, We don't know what precise user needs.
 * So we directly cast all elements into [Double] which causes that the return type is [Double] as well.
 * @return The sum of the all elements in the `iter` as [Double].
 */
@Deprecated(
    "Please use this.sum() instead.",
    replaceWith = ReplaceWith("iter.sum()")
)
fun <N : Number> sum(iter: Iterable<N>): Double {
    return iter.sum()
}

/**
 * @return The sum of the all elements in the collection as [Double].
 */
@JvmName("sumOfNumberToDouble")
fun <N : Number> Iterable<N>.sum(): Double {
    return this.sumOf(Number::toDouble)
}

/**
 * @return the sum of all values produced by [selector] function applied to each element in the stream.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("sumOfDouble")
inline fun <T> Stream<T>.sumOf(selector: (T) -> Double): Double {
    var sum = 0.0
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

/**
 * @return the sum of all values produced by [selector] function applied to each element in the stream.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("sumOfInt")
inline fun <T> Stream<T>.sumOf(selector: (T) -> Int): Int {
    var sum = 0
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

/**
 * @return the sum of all values produced by [selector] function applied to each element in the stream.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("sumOfLong")
inline fun <T> Stream<T>.sumOf(selector: (T) -> Long): Long {
    var sum = 0L
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

/**
 * @return the sum of all values produced by [selector] function applied to each element in the stream.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("sumOfUInt")
inline fun <T> Stream<T>.sumOf(selector: (T) -> UInt): UInt {
    var sum: UInt = 0.toUInt()
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

/**
 * @return the sum of all values produced by [selector] function applied to each element in the stream.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("sumOfULong")
inline fun <T> Stream<T>.sumOf(selector: (T) -> ULong): ULong {
    var sum: ULong = 0.toULong()
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

/**
 * A static function which is like `sum(iterable)` in python and [max]`(a, b)` in kotlin.
 * But I think the better way is using `iter.prod()` function which is below this function.
 * Also, We don't know what precise user needs.
 * So we directly cast all elements into [Double] which causes that the return type is [Double] as well.
 * @return The prod of the all elements in the `iter` as [Double].
 */
@Deprecated(
    "Please use this.prod() instead.",
    replaceWith = ReplaceWith("iter.prod()")
)
fun <N : Number> prod(iter: Iterable<N>): Double {
    return iter.prod()
}

/**
 * @return The prod of the all elements in the collection as [Double].
 */
@JvmName("prodOfNumberToDouble")
fun <N : Number> Iterable<N>.prod(): Double {
    return this.prodOf { it.toDouble() }
}

/**
 * @return the prod of all values produced by [selector] function applied to each element in the collection.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("prodOfDouble")
inline fun <T> Iterable<T>.prodOf(selector: (T) -> Double): Double {
    var prod = 1.0
    for (element in this) {
        prod *= selector(element)
    }
    return prod
}

/**
 * @return the prod of all values produced by [selector] function applied to each element in the collection.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("prodOfInt")
inline fun <T> Iterable<T>.prodOf(selector: (T) -> Int): Int {
    var prod = 1
    for (element in this) {
        prod *= selector(element)
    }
    return prod
}

/**
 * @return the prod of all values produced by [selector] function applied to each element in the collection.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("prodOfLong")
inline fun <T> Iterable<T>.prodOf(selector: (T) -> Long): Long {
    var prod = 1L
    for (element in this) {
        prod *= selector(element)
    }
    return prod
}

/**
 * @return the prod of all values produced by [selector] function applied to each element in the collection.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("prodOfUInt")
@Deprecated("We won't remove this function. But it very likely return unexpected value. Because this function possibly store a negative value into UInt.")
inline fun <T> Iterable<T>.prodOf(selector: (T) -> UInt): UInt {
    var prod: UInt = 1.toUInt()
    for (element in this) {
        prod *= selector(element)
    }
    return prod
}

/**
 * @return the prod of all values produced by [selector] function applied to each element in the collection.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("prodOfULong")
@Deprecated("We won't remove this function. But it very likely return unexpected value. Because this function possibly store a negative value into ULong.")
inline fun <T> Iterable<T>.prodOf(selector: (T) -> ULong): ULong {
    var prod: ULong = 1.toULong()
    for (element in this) {
        prod *= selector(element)
    }
    return prod
}

/**
 * @return the prod of all elements in the collection.
 */
@JvmName("prodOfByte")
fun Iterable<Byte>.prod(): Int {
    var prod = 1
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the collection.
 */
@JvmName("prodOfShort")
fun Iterable<Short>.prod(): Int {
    var prod = 1
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the collection.
 */
@JvmName("prodOfInt")
fun Iterable<Int>.prod(): Int {
    var prod = 1
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the collection.
 */
@JvmName("prodOfLong")
fun Iterable<Long>.prod(): Long {
    var prod = 1L
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the collection.
 */
@JvmName("prodOfFloat")
fun Iterable<Float>.prod(): Float {
    var prod = 1.0f
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the collection.
 */
@JvmName("prodOfDouble")
fun Iterable<Double>.prod(): Double {
    var prod = 1.0
    for (element in this) {
        prod *= element
    }
    return prod
}


/**
 * @return the prod of all elements in the array.
 */
@JvmName("prodOfByte")
fun Array<out Byte>.prod(): Int {
    var prod = 1
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the array.
 */
@JvmName("prodOfShort")
fun Array<out Short>.prod(): Int {
    var prod = 1
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the array.
 */
@JvmName("prodOfInt")
fun Array<out Int>.prod(): Int {
    var prod = 1
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the array.
 */
@JvmName("prodOfLong")
fun Array<out Long>.prod(): Long {
    var prod = 1L
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the array.
 */
@JvmName("prodOfFloat")
fun Array<out Float>.prod(): Float {
    var prod = 1.0f
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the array.
 */
@JvmName("prodOfDouble")
fun Array<out Double>.prod(): Double {
    var prod = 1.0
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the array.
 */
fun ByteArray.prod(): Int {
    var prod = 1
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the array.
 */
fun ShortArray.prod(): Int {
    var prod = 1
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the array.
 */
fun IntArray.prod(): Int {
    var prod = 1
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the array.
 */
fun LongArray.prod(): Long {
    var prod = 1L
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the array.
 */
fun FloatArray.prod(): Float {
    var prod = 1.0f
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the array.
 */
fun DoubleArray.prod(): Double {
    var prod = 1.0
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the array.
 * @see Iterable.prodOf prodOf(selector: (T) -> UInt)
 */
@OptIn(ExperimentalUnsignedTypes::class)
@Deprecated("We won't remove this function. But it very likely return unexpected value. Because this function possibly store a negative value into UInt.")
fun UByteArray.prod(): UInt = this.prodOf { it.toUInt() }

/**
 * @return the prod of all elements in the array.
 * @see Iterable.prodOf prodOf(selector: (T) -> UInt)
 */
@OptIn(ExperimentalUnsignedTypes::class)
@Deprecated("We won't remove this function. But it very likely return unexpected value. Because this function possibly store a negative value into UInt.")
fun UShortArray.prod(): UInt = this.prodOf { it.toUInt() }

/**
 * @return the prod of all elements in the array.
 * @see Iterable.prodOf prodOf(selector: (T) -> UInt)
 */
@OptIn(ExperimentalUnsignedTypes::class)
@Deprecated("We won't remove this function. But it very likely return unexpected value. Because this function possibly store a negative value into UInt.")
fun UIntArray.prod(): UInt = this.prodOf { it }


/**
 * @return the prod of all elements in the array.
 * @see Iterable.prodOf prodOf(selector: (T) -> ULong)
 */
@OptIn(ExperimentalUnsignedTypes::class)
fun ULongArray.prod(): ULong = this.prodOf { it }

/**
 * @return the prod of all elements in the number stream.
 */
fun IntStream.prod(): Int {
    var prod = 1
    for (element in this) {
        prod *= element
    }
    return prod
}


/**
 * @return the prod of all elements in the number stream.
 */
fun LongStream.prod(): Long {
    var prod = 1L
    for (element in this) {
        prod *= element
    }
    return prod
}


/**
 * @return the prod of all elements in the number stream.
 */
fun DoubleStream.prod(): Double {
    var prod = 1.0
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all values produced by [selector] function applied to each element in the stream.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("prodOfDouble")
inline fun <T> Stream<T>.prodOf(selector: (T) -> Double): Double {
    var prod = 1.0
    for (element in this) {
        prod *= selector(element)
    }
    return prod
}

/**
 * @return the prod of all values produced by [selector] function applied to each element in the stream.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("prodOfInt")
inline fun <T> Stream<T>.prodOf(selector: (T) -> Int): Int {
    var prod = 1
    for (element in this) {
        prod *= selector(element)
    }
    return prod
}

/**
 * @return the prod of all values produced by [selector] function applied to each element in the stream.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("prodOfLong")
inline fun <T> Stream<T>.prodOf(selector: (T) -> Long): Long {
    var prod = 1L
    for (element in this) {
        prod *= selector(element)
    }
    return prod
}

/**
 * @return the prod of all values produced by [selector] function applied to each element in the stream.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("prodOfUInt")
@Deprecated("We won't remove this function. But it very likely return unexpected value. Because this function possibly store a negative value into UInt.")
inline fun <T> Stream<T>.prodOf(selector: (T) -> UInt): UInt {
    var prod: UInt = 1.toUInt()
    for (element in this) {
        prod *= selector(element)
    }
    return prod
}

/**
 * @return the prod of all values produced by [selector] function applied to each element in the stream.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("prodOfULong")
@Deprecated("We won't remove this function. But it very likely return unexpected value. Because this function possibly store a negative value into ULong.",)
inline fun <T> Stream<T>.prodOf(selector: (T) -> ULong): ULong {
    var prod: ULong = 1.toULong()
    for (element in this) {
        prod *= selector(element)
    }
    return prod
}

/**
 * A copy
 * @see [kotlin.collections.mapCapacity]
 */
fun mapCapacity(expectedSize: Int): Int = when {
    expectedSize < 0 -> expectedSize
    expectedSize < 3 -> expectedSize + 1
    expectedSize < (1 shl (Int.SIZE_BITS - 2)) -> ((expectedSize / 0.75F) + 1.0F).toInt()
    else -> Int.MAX_VALUE
}
