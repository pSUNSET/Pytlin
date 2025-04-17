@file:JvmName("StringUtil")

package net.psunset.pytlin.lang

import net.psunset.pytlin.Py
import net.psunset.pytlin.collections.Kwargs
import net.psunset.pytlin.collections.times

/**
 * Calls [CharSequence.repeat]
 * @return a string containing this char sequence repeated [n] times.
 * @see CharSequence.repeat
 */
operator fun CharSequence.times(n: Int): String = this.repeat(n.let { if (it <= 0) 0 else it })

/**
 * Calls [CharSequence.repeat] function which is below this function.
 * For example:
 * ```
 * val hi = "hello"
 * val hi3 = hi * kwargsOf(
 *     "n" to 3,
 *     "sep" to " "
 * )
 * println(hi3) // Result: hello hello hello
 * ```
 * ```
 * val hi = "hello"
 * val hi3 = hi * kwargsOf(
 *     "n" to 3,
 *     "sep" to " ",
 *     "postfix" to "!",
 *     "transform" to { s: String -> "$s world" }
 * )
 * println(hi3) // Result: hello world hello world hello world!
 * ```
 * @param kwargs Please ensure both "n" and "separator" are not `null`. Other args are all used by [CharSequence.repeat] function.
 * @see CharSequence.repeat times(n: Int, separator: CharSequence, prefix: CharSequence, postfix: CharSequence, limit: Int, truncated: CharSequence, transform: ((String) -> CharSequence)?)
 */
operator fun CharSequence.times(kwargs: Kwargs): String {
    val n = kwargs["n", Int::class]
    val sep =
        kwargs["separator", kwargs["sep", ""]] // Allow using either "separator" or "sep" as the key word
    val prefix = kwargs["prefix", ""]
    val postfix = kwargs["postfix", ""]
    val limit = kwargs["limit", -1]
    val truncated = kwargs["truncated", "..."]

    @Suppress("UNCHECKED_CAST")
    val transform =
        (kwargs["transform", Function1::class]) as ((CharSequence) -> CharSequence)? // Can't mark what the class transform should be, so use `as` to cast it here.

    if (n == null) {
        throw IllegalArgumentException("The \"n\" key should exist in kwargs. And the the value got by it should be present and non-null.")
    }

    return this.repeat(n, sep, prefix, postfix, limit, truncated, transform)
}

/**
 * In fact, this function just creates a list whose length is `n`.
 * And all elements in the list are `this`.
 * After that, call [Iterable.joinToString] function to build the result.
 *
 * The optional parameters are used by [Iterable.joinToString].
 *
 * @return A string made with `n` raw string and other fixes.
 */
fun CharSequence.repeat(
    n: Int,
    separator: CharSequence,
    prefix: CharSequence = "",
    postfix: CharSequence = "",
    limit: Int = -1,
    truncated: CharSequence = "...",
    transform: ((CharSequence) -> CharSequence)? = null
): String {
    if (n <= 0) return ""
    if (n == 1) return this.toString()
    if (this.isEmpty()) return ""

    val strList = listOf(this) * n
    return strList.joinToString(separator, prefix, postfix, limit, truncated, transform)
}


/**
 * Calls [Char.repeat]
 * @return a string containing this char repeated [n] times.
 * @see Char.repeat
 */
operator fun Char.times(n: Int): String = this.repeat(n.let { if (it <= 0) 0 else it })

/**
 * @return a string containing this char repeated [n] times.
 * @throws [IllegalArgumentException] when n < 0.
 */
fun Char.repeat(n: Int): String {
    require(n >= 0) { "Count 'n' must be non-negative, but was $n." }
    if (n == 0) return ""
    return String(CharArray(n) { this })
}

/**
 * @return `true` if the chars inside all are digits, `false` otherwise.
 */
fun CharSequence.isDigit(): Boolean = this.all { it.isDigit() }

/**
 * Calls [CharSequence.slice]
 */
operator fun CharSequence.get(indies: IntRange): CharSequence = this.slice(indies)

/**
 * Calls [CharSequence.slice]
 */
operator fun CharSequence.get(indies: Iterable<Int>): CharSequence = this.slice(indies)

/**
 * Calls [String.slice]
 */
operator fun String.get(indies: IntRange): String = this.slice(indies)

/**
 * Calls [String.slice]
 */
operator fun String.get(indies: Iterable<Int>): String = this.slice(indies)

/**
 * Parses the slice pattern and calls another overload get function.
 */
operator fun CharSequence.get(pattern: String): CharSequence {
    val slice = Py.slice(pattern)
    if (slice.isNumber) return String(CharArray(1) { this[slice.asNumber(this.length)] })
    if (slice.isClone) return this
    if (slice.isReverse) return this.reversed()
    if (slice.isRange) return this[slice.asRange(this.length)]
    return this[slice.asProgression(this.length)]
}

/**
 * Parses the slice pattern and calls another overload get function.
 */
operator fun String.get(pattern: String): String {
    val slice = Py.slice(pattern)
    if (slice.isNumber) return String(CharArray(1) { this[slice.asNumber(this.length)] })
    if (slice.isClone) return this
    if (slice.isReverse) return this.reversed()
    if (slice.isRange) return this[slice.asRange(this.length)]
    return this[slice.asProgression(this.length)]
}