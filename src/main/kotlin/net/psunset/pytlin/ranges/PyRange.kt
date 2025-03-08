package net.psunset.pytlin.ranges

/**
 * The function is similar in `range` function in python.
 */
fun range(start: Int, endExclusive: Int, step: Int): IntProgression =
    IntProgression.fromClosedRange(start, endExclusive.let { it + if (step < 0) 1 else -1 }, step)

/**
 * The function is similar in `range` function in python.
 */
fun range(start: Int, endExclusive: Int): IntRange = IntRange(start, endExclusive - 1)

/**
 * The function is similar in `range` function in python.
 */
fun range(endExclusive: Int): IntRange = IntRange(0, endExclusive - 1)