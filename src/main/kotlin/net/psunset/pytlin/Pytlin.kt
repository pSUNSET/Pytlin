package net.psunset.pytlin

import net.psunset.pytlin.collections.*

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
}
