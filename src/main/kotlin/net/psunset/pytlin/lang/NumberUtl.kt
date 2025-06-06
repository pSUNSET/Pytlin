@file:JvmName("NumberUtil")

/*
 * Most functions in this file are using `BigDecimal` for better precision.
 */

package net.psunset.pytlin.lang

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import kotlin.math.abs
import kotlin.math.truncate

/**
 * Determines if the number has a zero fractional part.
 * In other words, it checks if the number is a whole number or if it has a decimal component
 * that doesn't equal to zero.
 *
 * @return `true` if the number has a zero fractional part, `false` otherwise.
 */
fun Float.isFractionalPartZero(): Boolean = this.toBigDecimal() valEq this.toBigDecimal().truncate()

/**
 * Determines if the number has a zero fractional part.
 * In other words, it checks if the number is a whole number or if it has a decimal component
 * that doesn't equal to zero.
 *
 * @return `true` if the number has a zero fractional part, `false` otherwise.
 */
fun Double.isFractionalPartZero(): Boolean = this valEq this.truncate()

/**
 * Determines if the number has a zero fractional part.
 * In other words, it checks if the number is a whole number or if it has a decimal component
 * that doesn't equal to zero.
 *
 * @return `true` if the number has a zero fractional part, `false` otherwise.
 */
fun BigDecimal.isFractionalPartZero(): Boolean = this valEq this.truncate()

/**
 * @return fractional part of the number.
 */
fun Float.fractionalPart(): Float = abs(this.toBigDecimal().fractionalPart().toFloat())

/**
 * @return fractional part of the number.
 */
fun Double.fractionalPart(): Double = abs(this.toBigDecimal().fractionalPart().toDouble())

/**
 * @return fractional part of the number.
 */
fun BigDecimal.fractionalPart(): BigDecimal = this.subtract(this.truncate()).abs()

/**
 * @return the number having its fractional part truncated.
 */
inline fun Float.truncate(): Float = truncate(this)

/**
 * @return the number having its fractional part truncated.
 */
inline fun Double.truncate(): Double = truncate(this)

/**
 * @return the number having its fractional part truncated.
 */
fun BigDecimal.truncate(): BigDecimal = this.setScale(0, RoundingMode.DOWN)

/**
 * Checks if two numbers have the same numerical value, regardless of their data types.
 *
 * When needing to compare two numbers in different types which `==` and `!=` operator don't allow,
 * we can use this function.
 *
 * For example:
 * ```
 * val a = 123.456f
 * val b = 123.456
 * println(a == b) // Error: Operator '==' cannot be applied to 'Float' and 'Double'
 * println(a valEq b) // Result: true
 * ```
 * ```
 * val number1 = BigInteger("12345")
 * val number2 = BigDecimal("12345.00")
 * println(number1 == number2) // Result: false
 * // It is because they are in different types.
 * println(number1 valEq number2) // Result: true
 * ```
 *
 * What's more, when we try to compare the value of two [BigDecimal] with `==`, if the precisions of them are different, it returns `false`.
 * But with this function, it returns `true`
 *
 * For example:
 * ```
 * val number1 = BigDecimal("12345.0")
 * val number2 = BigDecimal("12345.00")
 * println(number1 == number2) // Result: false
 * println(number1 valEq number2) // Result: true
 * ```
 *
 * @return `true` if the numbers have the same value, `false` otherwise.
 */
infix fun Number.valEq(other: Number): Boolean =
    this.toBigDecimal().compareTo(other.toBigDecimal()) == 0

/**
 * Calls [valEq] function
 * @see Number.valEq
 */
@JvmName("valueEquals")
fun valEq(a: Number, b: Number): Boolean = a.valEq(b)

inline fun Number.toBigInteger(): BigInteger = BigInteger(this.toString())

inline fun Number.toBigDecimal(): BigDecimal = BigDecimal(this.toString())

/**
 * When [BigDecimal] must have larger scale to save data, use this function instead.
 * @param minScale The expected min scale of this decimal.
 */
fun Number.toBigDecimal(minScale: Int): BigDecimal =
    BigDecimal(this.toString()).let { if (it.scale() < minScale) it.setScale(minScale) else it }


