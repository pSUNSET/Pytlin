package net.psunset.kwargs.lang

import kotlin.math.floor

/**
 * Determines if the number has a non-zero fractional part.
 * In other words, it checks if the number is a whole number or if it has a decimal component
 * that's not equal to zero.
 *
 * @return `true` if the number has a non-zero fractional part; `false` otherwise.
 */
fun Number.hasFractionalPart(): Boolean {
    return this != floor(this.toDouble())
}

//private val classToLevel = mapOf(
//    Byte::class to 0,
//    Short::class to 1,
//    Int::class to 2,
//    Long::class to 3,
//    Float::class to 4,
//    Double::class to 5
//)
//private val levelToCastFunc = mapOf(
//    0 to Number::toByte,
//    1 to Number::toShort,
//    2 to Number::toInt,
//    3 to Number::toLong,
//    4 to Number::toFloat,
//    5 to Number::toDouble
//)

/**
 * Checks if two numbers have the same numerical value, regardless of their data types.
 *
 * @return `true` if the numbers have the same value, `false` otherwise.
 */
infix fun Number.valEq(other: Number): Boolean {
//    val thisLevel = classToLevel[this::class]!!
//    val otherLevel = classToLevel[other::class]!!
//    return if (thisLevel > otherLevel)
//        this == levelToCastFunc[thisLevel]!!(other)
//    else if (thisLevel < otherLevel)
//        levelToCastFunc[otherLevel]!!(this) == other
//    else
//        this == other
    return this.toDouble() == other.toDouble()
}

/**
 * @see Number.valEq
 */
@JvmName("valueEq")
fun valEq(a: Number, b: Number): Boolean = a valEq b

