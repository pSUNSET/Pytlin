/*
 * Boolean-ize
 */

package net.psunset.pytlin.lang

import net.psunset.pytlin.collections.Tensor_D

/**
 * Basic `toBool()` function
 * All `this.toBool()` can be replaced to `!!this`.
 */
inline fun Any?.toBool(): Boolean = this != null

/**
 * All `this.not()` and `!this` in this file stand for `!this.toBool()`.
 */
inline operator fun Any?.not(): Boolean = this == null

/**
 * @return `true` when the character sequence isn't empty; `false` otherwise.
 */
inline fun CharSequence.toBool(): Boolean = this.isNotEmpty()
inline operator fun CharSequence.not(): Boolean = this.isEmpty()

/**
 * @return self
 */
inline fun Boolean.toBool(): Boolean = this
// `operator fun Boolean.not()` exists already

/**
 * @return `true` when the number isn't zero; `false` otherwise.
 */
inline fun Byte.toBool(): Boolean = this != 0.toByte()
inline operator fun Byte.not(): Boolean = this == 0.toByte()

/**
 * @return `true` when the number isn't zero; `false` otherwise.
 */
inline fun Short.toBool(): Boolean = this != 0.toShort()
inline operator fun Short.not(): Boolean = this == 0.toShort()

/**
 * @return `true` when the number isn't zero; `false` otherwise.
 */
inline fun Int.toBool(): Boolean = this != 0
inline operator fun Int.not(): Boolean = this == 0

/**
 * @return `true` when the number isn't zero; `false` otherwise.
 */
inline fun Long.toBool(): Boolean = this != 0L
inline operator fun Long.not(): Boolean = this == 0L

/**
 * @return `true` when the number isn't zero; `false` otherwise.
 */
inline fun Float.toBool(): Boolean = this != 0.0f
inline operator fun Float.not(): Boolean = this == 0.0f

/**
 * @return `true` when the number isn't zero; `false` otherwise.
 */
inline fun Double.toBool(): Boolean = this != 0.0
inline operator fun Double.not(): Boolean = this == 0.0

/**
 * @return `true` when the number isn't zero; `false` otherwise.
 */
inline fun UByte.toBool(): Boolean = this != 0.toUByte()
inline operator fun UByte.not(): Boolean = this == 0.toUByte()

/**
 * @return `true` when the number isn't zero; `false` otherwise.
 */
inline fun UShort.toBool(): Boolean = this != 0.toUShort()
inline operator fun UShort.not(): Boolean = this == 0.toUShort()

/**
 * @return `true` when the number isn't zero; `false` otherwise.
 */
inline fun UInt.toBool(): Boolean = this != 0.toUInt()
inline operator fun UInt.not(): Boolean = this == 0.toUInt()

/**
 * @return `true` when the number isn't zero; `false` otherwise.
 */
inline fun ULong.toBool(): Boolean = this != 0.toULong()
inline operator fun ULong.not(): Boolean = this == 0.toULong()

/**
 * @return `true` when the number isn't zero; `false` otherwise.
 */
inline fun Number.toBool(): Boolean = !(this valEq 0)
inline operator fun Number.not(): Boolean = this valEq 0

/**
 * @return `true` when the collection isn't empty; `false` otherwise.
 */
inline fun <T> Iterable<T>.toBool(): Boolean = this.count() != 0
inline operator fun <T> Iterable<T>.not(): Boolean = this.count() == 0

/**
 * @return `true` when the collection isn't empty; `false` otherwise.
 */
inline fun <T> Collection<T>.toBool(): Boolean = this.isNotEmpty()
inline operator fun <T> Collection<T>.not(): Boolean = this.isEmpty()

/**
 * @return `true` when the array isn't empty; `false` otherwise.
 */
inline fun <T> Array<out T>.toBool(): Boolean = this.isNotEmpty()
inline operator fun <T> Array<out T>.not(): Boolean = this.isEmpty()

/**
 * @return `true` when the array isn't empty; `false` otherwise.
 */
inline fun CharArray.toBool(): Boolean = this.isNotEmpty()
inline operator fun CharArray.not(): Boolean = this.isEmpty()

/**
 * @return `true` when the array isn't empty; `false` otherwise.
 */
inline fun ByteArray.toBool(): Boolean = this.isNotEmpty()
inline operator fun ByteArray.not(): Boolean = this.isEmpty()

/**
 * @return `true` when the array isn't empty; `false` otherwise.
 */
inline fun ShortArray.toBool(): Boolean = this.isNotEmpty()
inline operator fun ShortArray.not(): Boolean = this.isEmpty()

/**
 * @return `true` when the array isn't empty; `false` otherwise.
 */
inline fun IntArray.toBool(): Boolean = this.isNotEmpty()
inline operator fun IntArray.not(): Boolean = this.isEmpty()

/**
 * @return `true` when the array isn't empty; `false` otherwise.
 */
inline fun LongArray.toBool(): Boolean = this.isNotEmpty()
inline operator fun LongArray.not(): Boolean = this.isEmpty()

/**
 * @return `true` when the array isn't empty; `false` otherwise.
 */
inline fun FloatArray.toBool(): Boolean = this.isNotEmpty()
inline operator fun FloatArray.not(): Boolean = this.isEmpty()

/**
 * @return `true` when the array isn't empty; `false` otherwise.
 */
inline fun DoubleArray.toBool(): Boolean = this.isNotEmpty()
inline operator fun DoubleArray.not(): Boolean = this.isEmpty()

/**
 * @return `true` when the array isn't empty; `false` otherwise.
 */
inline fun BooleanArray.toBool(): Boolean = this.isNotEmpty()
inline operator fun BooleanArray.not(): Boolean = this.isEmpty()

/**
 * @return `true` when the array isn't empty; `false` otherwise.
 */
@OptIn(ExperimentalUnsignedTypes::class) inline fun UByteArray.toBool(): Boolean = this.isNotEmpty()
@OptIn(ExperimentalUnsignedTypes::class) inline operator fun UByteArray.not(): Boolean = this.isEmpty()

/**
 * @return `true` when the array isn't empty; `false` otherwise.
 */
@OptIn(ExperimentalUnsignedTypes::class) inline fun UShortArray.toBool(): Boolean = this.isNotEmpty()
@OptIn(ExperimentalUnsignedTypes::class) inline operator fun UShortArray.not(): Boolean = this.isEmpty()

/**
 * @return `true` when the array isn't empty; `false` otherwise.
 */
@OptIn(ExperimentalUnsignedTypes::class) inline fun UIntArray.toBool(): Boolean = this.isNotEmpty()
@OptIn(ExperimentalUnsignedTypes::class) inline operator fun UIntArray.not(): Boolean = this.isEmpty()

/**
 * @return `true` when the array isn't empty; `false` otherwise.
 */
@OptIn(ExperimentalUnsignedTypes::class) inline fun ULongArray.toBool(): Boolean = this.isNotEmpty()
@OptIn(ExperimentalUnsignedTypes::class) inline operator fun ULongArray.not(): Boolean = this.isEmpty()

/**
 * @return `true` when the map isn't empty; `false` otherwise.
 */
inline fun <K, T> Map<K, T>.toBool(): Boolean = this.isNotEmpty()
inline operator fun <K, T> Map<K, T>.not(): Boolean = this.isEmpty()

/**
 * @return `true` when the tensor isn't empty; `false` otherwise.
 */
inline fun <T : Number> Tensor_D<T>.toBool(): Boolean = this.data.isNotEmpty()
inline operator fun <T : Number> Tensor_D<T>.not(): Boolean = this.data.isEmpty()

