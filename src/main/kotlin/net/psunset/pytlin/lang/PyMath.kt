package net.psunset.pytlin.lang

import kotlin.math.floor

/**
 * To put some operation functions that are similar in python.
 */
object PyMath {

    /**
     * Python's raw `div` function.
     * @see [Math.floorDiv]
     */
    @JvmStatic
    fun floorDiv(a: Double, b: Double): Double {
        return floor(a / b)
    }

    /**
     * Python's raw `div` function.
     * @see [Math.floorMod]
     */
    @JvmStatic
    fun floorMod(a: Double, b: Double): Double {
        val mod = a % b
        return if (mod == 0.0) 0.0 else {
            if ((b > 0 && mod < 0) || (b < 0 && mod > 0)) mod + b else mod
        }
    }

    /**
     * Python's raw `div` function.
     * @see [Math.floorDiv]
     */
    @JvmStatic
    fun floorDiv(a: Float, b: Float): Float {
        return floor(a / b)
    }

    /**
     * Python's raw `div` function.
     * @see [Math.floorMod]
     */
    @JvmStatic
    fun floorMod(a: Float, b: Float): Float {
        val mod = a % b
        return if (mod == 0f) 0f else {
            if ((b > 0 && mod < 0) || (b < 0 && mod > 0)) mod + b else mod
        }
    }
}