package net.psunset.pytlin

import net.psunset.pytlin.collections.PySlice

typealias Py = Pytlin

/**
 * The object containing the built-in functions in python.
 */
object Pytlin {

    /** Parse the slice pattern with a string */
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