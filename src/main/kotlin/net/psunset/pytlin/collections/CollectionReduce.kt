package net.psunset.pytlin.collections

import java.math.BigDecimal
import java.math.BigInteger
import java.util.stream.DoubleStream
import java.util.stream.IntStream
import java.util.stream.LongStream
import java.util.stream.Stream
import kotlin.experimental.ExperimentalTypeInference


/**
 * @return The sum of the all elements in the collection as [Double].
 */
@JvmName("sumOfNumberToDouble")
fun <N : Number> Iterable<N>.sum(): Double = this.sumOf(Number::toDouble)

/**
 * @return The sum of the all elements in the collection.
 */
@JvmName("sumOfBigInteger")
fun Iterable<BigInteger>.sum(): BigInteger {
    var sum = 0.toBigInteger()
    for (element in this) {
        sum += element
    }
    return sum
}

/**
 * @return The sum of the all elements in the collection.
 */
@JvmName("sumOfBigDecimal")
fun Iterable<BigDecimal>.sum(): BigDecimal {
    var sum = 0.toBigDecimal()
    for (element in this) {
        sum += element
    }
    return sum
}

/**
 * @return The sum of the all elements in the array.
 */
@JvmName("sumOfBigInteger")
fun Array<out BigInteger>.sum(): BigInteger {
    var sum = 0.toBigInteger()
    for (element in this) {
        sum += element
    }
    return sum
}

/**
 * @return The sum of the all elements in the array.
 */
@JvmName("sumOfBigDecimal")
fun Array<out BigDecimal>.sum(): BigDecimal {
    var sum = 0.toBigDecimal()
    for (element in this) {
        sum += element
    }
    return sum
}

/**
 * @return the sum of all values produced by [selector] function applied to each element in the collection.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("sumOfFloat")
inline fun <T> Iterable<T>.sumOf(selector: (T) -> Float): Float {
    var sum = 0.0f
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
@JvmName("sumOfFloat")
inline fun <T> Stream<T>.sumOf(selector: (T) -> Float): Float {
    var sum = 0.0f
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
 * @return The prod of the all elements in the collection as [Double].
 */
@JvmName("prodOfNumberToDouble")
fun <N : Number> Iterable<N>.prod(): Double = this.prodOf { it.toDouble() }

/**
 * @return the prod of all values produced by [selector] function applied to each element in the collection.
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("prodOfDouble")
inline fun <T> Iterable<T>.prodOf(selector: (T) -> Float): Float {
    var prod = 1.0f
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
@Deprecated("We won't remove this function. But it is very likely return unexpected value. Because this function possibly store a negative value into UInt.")
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
@Deprecated("We won't remove this function. But it is very likely return unexpected value. Because this function possibly store a negative value into ULong.")
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
 * @return the prod of all elements in the collection.
 */
@JvmName("prodOfBigInteger")
fun Iterable<BigInteger>.prod(): BigInteger {
    var prod = 1.toBigInteger()
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the collection.
 */
@JvmName("prodOfBigDecimal")
fun Iterable<BigDecimal>.prod(): BigDecimal {
    var prod = 1.toBigDecimal()
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
@JvmName("prodOfBigInteger")
fun Array<out BigInteger>.prod(): BigInteger {
    var prod = 1.toBigInteger()
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * @return the prod of all elements in the array.
 */
@JvmName("prodOfBigDecimal")
fun Array<out BigDecimal>.prod(): BigDecimal {
    var prod = 1.toBigDecimal()
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
@Deprecated("We won't remove this function. But it is very likely return unexpected value. Because this function possibly store a negative value into UInt.")
fun UByteArray.prod(): UInt = this.prodOf { it.toUInt() }

/**
 * @return the prod of all elements in the array.
 * @see Iterable.prodOf prodOf(selector: (T) -> UInt)
 */
@OptIn(ExperimentalUnsignedTypes::class)
@Deprecated("We won't remove this function. But it is very likely return unexpected value. Because this function possibly store a negative value into UInt.")
fun UShortArray.prod(): UInt = this.prodOf { it.toUInt() }

/**
 * @return the prod of all elements in the array.
 * @see Iterable.prodOf prodOf(selector: (T) -> UInt)
 */
@OptIn(ExperimentalUnsignedTypes::class)
@Deprecated("We won't remove this function. But it is very likely return unexpected value. Because this function possibly store a negative value into UInt.")
fun UIntArray.prod(): UInt = this.prodOf { it }


/**
 * @return the prod of all elements in the array.
 * @see Iterable.prodOf prodOf(selector: (T) -> ULong)
 */
@OptIn(ExperimentalUnsignedTypes::class)
@Deprecated("We won't remove this function. But it is very likely return unexpected value. Because this function possibly store a negative value into ULong.")
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
@JvmName("prodOfFloat")
inline fun <T> Stream<T>.prodOf(selector: (T) -> Float): Float {
    var prod = 1.0f
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
@Deprecated("We won't remove this function. But it is very likely return unexpected value. Because this function possibly store a negative value into UInt.")
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
@Deprecated("We won't remove this function. But it is very likely return unexpected value. Because this function possibly store a negative value into ULong.")
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
