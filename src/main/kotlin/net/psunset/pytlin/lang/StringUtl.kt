@file:JvmName("StringUtil")

package net.psunset.pytlin.lang

import net.psunset.pytlin.collections.Kwargs
import net.psunset.pytlin.collections.times

/**
 * Call [String.repeat]
 * @return A string made with `n` repeat raw string.
 * @see String.repeat
 */
operator fun String.times(n: Int): String = this.repeat(n)

/**
 * Call [String.times] function which is below this function.
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
 * @param kwargs Please ensure both "n" and "separator" are not `null`. Other args are all used by [String.times] function.
 * @see String.times times(n: Int, separator: CharSequence, prefix: CharSequence, postfix: CharSequence, limit: Int, truncated: CharSequence, transform: ((String) -> CharSequence)?)
 */
operator fun String.times(kwargs: Kwargs): String {
    val n = kwargs["n", Int::class]
    val sep =
        kwargs["separator", kwargs["sep", ""]] // Accept using either "separator" or "sep" simply.
    val prefix = kwargs["prefix", ""]
    val postfix = kwargs ["postfix", ""]
    val limit = kwargs["limit", -1]
    val truncated = kwargs["truncated", "..."]

    @Suppress("UNCHECKED_CAST")
    val transform =
        (kwargs["transform", Function1::class]) as ((String) -> CharSequence)? // Can't mark what the class transform should be, so use `as` to cast it here.

    if (n == null) {
        throw IllegalArgumentException("The \"n\" key should exist in kwargs. And the the value got by it should be present and non-null.")
    }

    return this.times(n, sep, prefix, postfix, limit, truncated, transform)
}

/**
 * In fact, this function just creates a list whose length is `n`.
 * And all elements in the list are `this`.
 * After that, just call [Iterable.joinToString] function to build eventual [String].
 *
 * The optional parameters are used by [Iterable.joinToString].
 *
 * @return A string made with `n` raw string and other fixes.
 */
fun String.times(
    n: Int,
    separator: CharSequence = "",
    prefix: CharSequence = "",
    postfix: CharSequence = "",
    limit: Int = -1,
    truncated: CharSequence = "...",
    transform: ((String) -> CharSequence)? = null
): String {
    if (n <= 0) return ""
    if (n == 1) return this
    if (this.isEmpty()) return ""

    val strList = listOf(this) * n
    return strList.joinToString(separator, prefix, postfix, limit, truncated, transform)
}

operator fun Char.times(n: Int): String {
    if (n <= 0) return ""
    if (n == 1) return String(CharArray(n) {this} )

    val result = StringBuilder(n)
    for (i in 1 until n) {
        result.append(this)
    }
    return result.toString()
}

/**
 * @return `true` if the chars inside all are digits; `false` otherwise.
 */
fun CharSequence.isDigit(): Boolean {
    return this.all { it.isDigit() }
}