package net.psunset.pytlin.ranges

/**
 * The function is similar in `range` function in python.
 * @param end which is exclusive
 */
fun range(start: Int, end: Int, step: Int): IntProgression = (start..<end).step(step)

/**
 * The function is similar in `range` function in python.
 * @param end which is exclusive
 */
fun range(start: Int, end: Int): IntProgression = start..<end

/**
 * The function is similar in `range` function in python.
 * @param end which is exclusive
 */
fun range(end: Int): IntProgression = 0..<end