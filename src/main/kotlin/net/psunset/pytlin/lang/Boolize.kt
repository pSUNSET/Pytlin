/*
 * Boolean-ize
 */

package net.psunset.pytlin.lang

import net.psunset.pytlin.collections.Tensor_D
import java.util.*

/**
 * Basic `toBool()` function
 * All `this.toBool()` can be replaced with `!!this`.
 */
inline fun Any?.toBool(): Boolean = this != null

/**
 * All `this.not()` and `!this` in this file stand for `!this.toBool()`.
 */
inline operator fun Any?.not(): Boolean = this == null

/**
 * @return `true` when the character sequence isn't empty, otherwise `false`.
 */
inline fun CharSequence?.toBool(): Boolean = !this.isNullOrEmpty()
inline operator fun CharSequence?.not(): Boolean = this.isNullOrEmpty()

/**
 * @return self
 */
inline fun Boolean?.toBool(): Boolean = this == true
inline operator fun Boolean?.not(): Boolean = this != true

/**
 * @return `true` when the number isn't zero, otherwise `false`.
 */
inline fun Byte?.toBool(): Boolean = this != null && this != 0.toByte()
inline operator fun Byte?.not(): Boolean = this == null || this == 0.toByte()

/**
 * @return `true` when the number isn't zero, otherwise `false`.
 */
inline fun Short?.toBool(): Boolean = this != null && this != 0.toShort()
inline operator fun Short?.not(): Boolean = this == null || this == 0.toShort()

/**
 * @return `true` when the number isn't zero, otherwise `false`.
 */
inline fun Int?.toBool(): Boolean = this != null && this != 0
inline operator fun Int?.not(): Boolean = this == null || this == 0

/**
 * @return `true` when the number isn't zero, otherwise `false`.
 */
inline fun Long?.toBool(): Boolean = this != null && this != 0L
inline operator fun Long?.not(): Boolean = this == null || this == 0L

/**
 * @return `true` when the number isn't zero, otherwise `false`.
 */
inline fun Float?.toBool(): Boolean = this != null && this != 0.0f
inline operator fun Float?.not(): Boolean = this == null || this == 0.0f

/**
 * @return `true` when the number isn't zero, otherwise `false`.
 */
inline fun Double?.toBool(): Boolean = this != null && this != 0.0
inline operator fun Double?.not(): Boolean = this == null || this == 0.0

/**
 * @return `true` when the number isn't zero, otherwise `false`.
 */
inline fun UByte?.toBool(): Boolean = this != null && this != 0.toUByte()
inline operator fun UByte?.not(): Boolean = this == null || this == 0.toUByte()

/**
 * @return `true` when the number isn't zero, otherwise `false`.
 */
inline fun UShort?.toBool(): Boolean = this != null && this != 0.toUShort()
inline operator fun UShort?.not(): Boolean = this == null || this == 0.toUShort()

/**
 * @return `true` when the number isn't zero, otherwise `false`.
 */
inline fun UInt?.toBool(): Boolean = this != null && this != 0.toUInt()
inline operator fun UInt?.not(): Boolean = this == null || this == 0.toUInt()

/**
 * @return `true` when the number isn't zero, otherwise `false`.
 */
inline fun ULong?.toBool(): Boolean = this != null && this != 0.toULong()
inline operator fun ULong?.not(): Boolean = this == null || this == 0.toULong()

/**
 * @return `true` when the number isn't zero, otherwise `false`.
 */
inline fun Number?.toBool(): Boolean = this != null && !(this valEq 0)
inline operator fun Number?.not(): Boolean = this == null || this valEq 0

/**
 * @return `true` when the collection isn't empty, otherwise `false`.
 */
inline fun <T> Iterable<T>?.toBool(): Boolean = this != null && this.count() != 0
inline operator fun <T> Iterable<T>?.not(): Boolean = this == null || this.count() == 0

/**
 * @return `true` when the collection isn't empty, otherwise `false`.
 */
inline fun <T> Sequence<T>?.toBool(): Boolean = this != null && this.count() != 0
inline operator fun <T> Sequence<T>?.not(): Boolean = this == null || this.count() == 0

/**
 * @return `true` when the collection isn't empty, otherwise `false`.
 */
inline fun <T> Collection<T>?.toBool(): Boolean = !this.isNullOrEmpty()
inline operator fun <T> Collection<T>?.not(): Boolean = this.isNullOrEmpty()

/**
 * @return `true` when the array isn't empty, otherwise `false`.
 */
inline fun <T> Array<out T>?.toBool(): Boolean = this != null && this.isNotEmpty()
inline operator fun <T> Array<out T>?.not(): Boolean = this == null || this.isEmpty()

/**
 * @return `true` when the array isn't empty, otherwise `false`.
 */
inline fun CharArray?.toBool(): Boolean = this != null && this.isNotEmpty()
inline operator fun CharArray?.not(): Boolean = this == null || this.isEmpty()

/**
 * @return `true` when the array isn't empty, otherwise `false`.
 */
inline fun ByteArray?.toBool(): Boolean = this != null && this.isNotEmpty()
inline operator fun ByteArray?.not(): Boolean = this == null || this.isEmpty()

/**
 * @return `true` when the array isn't empty, otherwise `false`.
 */
inline fun ShortArray?.toBool(): Boolean = this != null && this.isNotEmpty()
inline operator fun ShortArray?.not(): Boolean = this == null || this.isEmpty()

/**
 * @return `true` when the array isn't empty, otherwise `false`.
 */
inline fun IntArray?.toBool(): Boolean = this != null && this.isNotEmpty()
inline operator fun IntArray?.not(): Boolean = this == null || this.isEmpty()

/**
 * @return `true` when the array isn't empty, otherwise `false`.
 */
inline fun LongArray?.toBool(): Boolean = this != null && this.isNotEmpty()
inline operator fun LongArray?.not(): Boolean = this == null || this.isEmpty()

/**
 * @return `true` when the array isn't empty, otherwise `false`.
 */
inline fun FloatArray?.toBool(): Boolean = this != null && this.isNotEmpty()
inline operator fun FloatArray?.not(): Boolean = this == null || this.isEmpty()

/**
 * @return `true` when the array isn't empty, otherwise `false`.
 */
inline fun DoubleArray?.toBool(): Boolean = this != null && this.isNotEmpty()
inline operator fun DoubleArray?.not(): Boolean = this == null || this.isEmpty()

/**
 * @return `true` when the array isn't empty, otherwise `false`.
 */
inline fun BooleanArray?.toBool(): Boolean = this != null && this.isNotEmpty()
inline operator fun BooleanArray?.not(): Boolean = this == null || this.isEmpty()

/**
 * @return `true` when the progression isn't empty, otherwise `false`.
 */
inline fun CharProgression?.toBool(): Boolean = this != null && !this.isEmpty()
inline operator fun CharProgression?.not(): Boolean = this == null || this.isEmpty()

/**
 * @return `true` when the progression isn't empty, otherwise `false`.
 */
inline fun IntProgression?.toBool(): Boolean = this != null && !this.isEmpty()
inline operator fun IntProgression?.not(): Boolean = this == null || this.isEmpty()

/**
 * @return `true` when the progression isn't empty, otherwise `false`.
 */
inline fun LongProgression?.toBool(): Boolean = this != null && !this.isEmpty()
inline operator fun LongProgression?.not(): Boolean = this == null || this.isEmpty()

/**
 * @return `true` when the progression isn't empty, otherwise `false`.
 */
inline fun UIntProgression?.toBool(): Boolean = this != null && !this.isEmpty()
inline operator fun UIntProgression?.not(): Boolean = this == null || this.isEmpty()

/**
 * @return `true` when the progression isn't empty, otherwise `false`.
 */
inline fun ULongProgression?.toBool(): Boolean = this != null && !this.isEmpty()
inline operator fun ULongProgression?.not(): Boolean = this == null || this.isEmpty()

/**
 * @return `true` when the map isn't empty, otherwise `false`.
 */
inline fun <K, T> Map<K, T>?.toBool(): Boolean = !this.isNullOrEmpty()
inline operator fun <K, T> Map<K, T>?.not(): Boolean = this.isNullOrEmpty()

/**
 * @return `true` when the tensor isn't empty, otherwise `false`.
 */
inline fun <T : Number> Tensor_D<T>?.toBool(): Boolean = this != null && this.data.isNotEmpty()
inline operator fun <T : Number> Tensor_D<T>?.not(): Boolean = this == null || this.data.isEmpty()

/**
 * @return `true` when the optional value is present, otherwise `false`.
 */
inline fun <T> Optional<T>?.toBool(): Boolean = this != null && this.isPresent
inline operator fun <T> Optional<T>?.not(): Boolean = this == null || this.isEmpty
