package net.psunset.kwargs.tools

/**
 * Returns the prod of all values produced by [selector] function applied to each element in the collection.
 */
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
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
 * Returns the prod of all values produced by [selector] function applied to each element in the collection.
 */
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
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
 * Returns the prod of all values produced by [selector] function applied to each element in the collection.
 */
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
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
 * Returns the prod of all values produced by [selector] function applied to each element in the collection.
 */
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("prodOfUInt")
inline fun <T> Iterable<T>.prodOf(selector: (T) -> UInt): UInt {
    var prod: UInt = 1.toUInt()
    for (element in this) {
        prod *= selector(element)
    }
    return prod
}

/**
 * Returns the prod of all values produced by [selector] function applied to each element in the collection.
 */
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("prodOfULong")
inline fun <T> Iterable<T>.prodOf(selector: (T) -> ULong): ULong {
    var prod: ULong = 1.toULong()
    for (element in this) {
        prod *= selector(element)
    }
    return prod
}

/**
 * Returns the prod of all elements in the collection.
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
 * Returns the prod of all elements in the collection.
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
 * Returns the prod of all elements in the collection.
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
 * Returns the prod of all elements in the collection.
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
 * Returns the prod of all elements in the collection.
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
 * Returns the prod of all elements in the collection.
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
 * Returns the prod of all elements in the array.
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
 * Returns the prod of all elements in the array.
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
 * Returns the prod of all elements in the array.
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
 * Returns the prod of all elements in the array.
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
 * Returns the prod of all elements in the array.
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
 * Returns the prod of all elements in the array.
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
 * Returns the prod of all elements in the array.
 */
fun ByteArray.prod(): Int {
    var prod = 1
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * Returns the prod of all elements in the array.
 */
fun ShortArray.prod(): Int {
    var prod = 1
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * Returns the prod of all elements in the array.
 */
fun IntArray.prod(): Int {
    var prod = 1
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * Returns the prod of all elements in the array.
 */
fun LongArray.prod(): Long {
    var prod = 1L
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * Returns the prod of all elements in the array.
 */
fun FloatArray.prod(): Float {
    var prod = 1.0f
    for (element in this) {
        prod *= element
    }
    return prod
}

/**
 * Returns the prod of all elements in the array.
 */
fun DoubleArray.prod(): Double {
    var prod = 1.0
    for (element in this) {
        prod *= element
    }
    return prod
}
