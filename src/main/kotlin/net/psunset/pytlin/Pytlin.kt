package net.psunset.pytlin

import net.psunset.pytlin.collections.PySlice
import net.psunset.pytlin.collections.prod
import net.psunset.pytlin.collections.sum
import net.psunset.pytlin.lang.PyMath
import net.psunset.pytlin.lang.toBool

typealias Py = Pytlin

/**
 * The object containing the built-in functions in python.
 */
object Pytlin {

    /**
     * A static function which is like `sum(iterable)` in python.
     * But, in fact, using `iter.sum()` function is a better way.
     * Also, without knowing what precise user needs,
     * directly cast all elements into [Double] which causes that the return type is [Double] as well.
     * @return The sum of the all elements in the `iter` as [Double].
     */
    fun <N : Number> sum(iter: Iterable<N>): Double = iter.sum()

    /**
     * A static function which is like `sum(iterable)` in python.
     * But, in fact, using `iter.prod()` function is a better way.
     * Also, without knowing what precise user needs,
     * directly cast all elements into [Double] which causes that the return type is [Double] as well.
     * @return The prod of the all elements in the `iter` as [Double].
     */
    fun <N : Number> prod(iter: Iterable<N>): Double = iter.prod()

    /**
     * Parse the slice pattern with a string
     */
    fun slice(pattern: String): PySlice = PySlice(pattern)

    /**
     * The function is similar to the `range` function in python.
     */
    fun range(start: Int, endExclusive: Int, step: Int): IntProgression =
        IntProgression.fromClosedRange(start, endExclusive.let { it + if (step < 0) 1 else -1 }, step)

    /**
     * The function is similar to the `range` function in python.
     */
    fun range(start: Int, endExclusive: Int): IntRange = IntRange(start, endExclusive - 1)

    /**
     * The function is similar to the `range` function in python.
     */
    fun range(endExclusive: Int): IntRange = IntRange(0, endExclusive - 1)

    /**
     * Returns `value.toBool()`
     */
    fun bool(value: Any?): Boolean = value.toBool()

    /**
     * Returns `true` if `x.toBool()` is `true` for all values x in the iterable.
     *
     * If the iterable is empty, return `true`.
     */
    fun <T> all(iterable: Iterable<T>): Boolean {
        return iterable.all { it.toBool() }
    }

    /**
     * Returns `true` if `x.toBool()` is `true` for any x in the iterable.
     *
     * If the iterable is empty, return `false`.
     */
    fun <T> any(iterable: Iterable<T>): Boolean {
        return iterable.any { it.toBool() }
    }

    /**
     * Returns the pair (x/y, x%y).
     * Invariant: div*y + mod == x.
     * As a side note, the `/` and `%` in this function are using [Math.floorDiv] and [Math.floorMod].
     */
    fun divmod(x: Int, y: Int): Pair<Int, Int> {
        if (y == 0) throw ArithmeticException("division or modulo by zero")
        return Pair(Math.floorDiv(x, y), Math.floorMod(x, y))
    }

    /**
     * Returns the pair (x/y, x%y).
     * Invariant: div*y + mod == x.
     * As a side note, the `/` and `%` in this function are using [Math.floorDiv] and [Math.floorMod].
     */
    fun divmod(x: Long, y: Long): Pair<Long, Long> {
        if (y == 0L) throw ArithmeticException("division or modulo by zero")
        return Pair(Math.floorDiv(x, y), Math.floorMod(x, y))
    }

    /**
     * Returns the pair (x/y, x%y).
     * Invariant: div*y + mod == x.
     * As a side note, the `/` and `%` in this function are using [Math.floorDiv] and [Math.floorMod].
     */
    fun divmod(x: Float, y: Float): Pair<Float, Float> {
        if (y == 0f) throw ArithmeticException("division or modulo by zero")
        return Pair(PyMath.floorDiv(x, y), PyMath.floorMod(x, y))
    }

    /**
     * Returns the pair (x/y, x%y).
     * Invariant: div*y + mod == x.
     * As a side note, the `/` and `%` in this function are using [Math.floorDiv] and [Math.floorMod].
     */
    fun divmod(x: Double, y: Double): Pair<Double, Double> {
        if (y == 0.0) throw ArithmeticException("division or modulo by zero")
        return Pair(PyMath.floorDiv(x, y), PyMath.floorMod(x, y))
    }
}
