package net.psunset.kwargs.collections

import java.util.stream.DoubleStream
import java.util.stream.IntStream
import java.util.stream.LongStream
import java.util.stream.Stream
import kotlin.experimental.ExperimentalTypeInference

/**
 * A static function which is like `sum(iterable)` in python and [max]`(a, b)` in kotlin.
 * But I think that using `iter.sum()` function which is below this function is a better way.
 * Also, We don't know what precise user needs.
 * So we directly cast all elements into [Double] which causes that the return type is [Double] as well.
 * @return The sum of the all elements in the `iter` as [Double].
 */
@Deprecated(
    "Please use this.sum() instead.",
    replaceWith = ReplaceWith("iter.sum()"),
    level = DeprecationLevel.WARNING
)
fun <N: Number> sum(iter: Iterable<N>): Double {
    return iter.sum()
}

/**
 * @return The sum of the all elements in the collection as [Double].
 */
@JvmName("sumOfNumberToDouble")
fun <N: Number> Iterable<N>.sum(): Double {
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
    replaceWith = ReplaceWith("iter.prod()"),
    level = DeprecationLevel.WARNING
)
fun <N: Number> prod(iter: Iterable<N>): Double {
    return iter.prod()
}

/**
 * @return The prod of the all elements in the collection as [Double].
 */
@JvmName("prodOfNumberToDouble")
fun <N: Number> Iterable<N>.prod(): Double {
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
@Deprecated(
    "We won't remove this function. But it very likely return unexpected value. Because this function possibly store a negative value into UInt.",
    level = DeprecationLevel.WARNING
)
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
@Deprecated(
    "We won't remove this function. But it very likely return unexpected value. Because this function possibly store a negative value into ULong.",
    level = DeprecationLevel.WARNING
)
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
@Deprecated(
    "We won't remove this function. But it very likely return unexpected value. Because this function possibly store a negative value into UInt.",
    level = DeprecationLevel.WARNING
)
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
@Deprecated(
    "We won't remove this function. But it very likely return unexpected value. Because this function possibly store a negative value into ULong.",
    level = DeprecationLevel.WARNING
)
inline fun <T> Stream<T>.prodOf(selector: (T) -> ULong): ULong {
    var prod: ULong = 1.toULong()
    for (element in this) {
        prod *= selector(element)
    }
    return prod
}
