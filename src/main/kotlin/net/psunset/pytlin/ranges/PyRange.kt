package net.psunset.pytlin.ranges

/**
 * The function is similar in `range` function in python.
 * @param end which is exclusive
 */
fun range(start: Int, end: Int, step: Int): IntProgression = IntProgression.fromClosedRange(start, end - 1, step)

/**
 * The function is similar in `range` function in python.
 * @param end which is exclusive
 */
fun range(start: Int, end: Int): IntRange = IntRange(start, end - 1)

/**
 * The function is similar in `range` function in python.
 * @param end which is exclusive
 */
fun range(end: Int): IntRange = IntRange(0, end - 1)