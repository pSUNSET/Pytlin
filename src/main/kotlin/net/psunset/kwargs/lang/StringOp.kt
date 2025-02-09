package net.psunset.kwargs.lang

import com.sun.jdi.CharType
import net.psunset.kwargs.collections.KWArgs
import net.psunset.kwargs.collections.times
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.typeOf
import org.jetbrains.annotations.NotNull

/**
 * @return A string made with `n` raw string.
 */
operator fun String.times(n: Int): String {
    if (n <= 0) return ""
    if (n == 1) return this

    val newStr = StringBuilder(this.length * n)
    for (i in 1 until n) {
        newStr.append(this)
    }
    return newStr.toString()
}

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
 * @param kwargs Please ensure both "n" and "separator" are [NotNull]. Other args are all used by [String.times] function.
 * @see String.times times(n: Int, separator: CharSequence, prefix: CharSequence, postfix: CharSequence, limit: Int, truncated: CharSequence, transform: ((String) -> CharSequence)?)
 */
operator fun String.times(kwargs: KWArgs): String {
    val n = kwargs / ("n" to  Int::class)
    val sep = kwargs / ("separator" to (kwargs / ("sep" to CharSequence::class))) // Accept using either "separator" or "sep" simply.
    val prefix = kwargs / ("prefix" to "")
    val postfix = kwargs / ("postfix" to "")
    val limit = kwargs / ("limit" to -1)
    val truncated = kwargs / ("truncated" to "...")
    @Suppress("UNCHECKED_CAST")
    val transform = (kwargs / ("transform" to Function::class)) as ((String) -> CharSequence)? // Can't mark what the class transform should be, so use `as` to cast it here.

    if (n == null || sep == null) {
        throw IllegalArgumentException("The \"n\" key and \"sep\" key should exist in kwargs. And the values got by them should be present and non-null.")
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
    separator: CharSequence,
    prefix: CharSequence = "",
    postfix: CharSequence = "",
    limit: Int = -1,
    truncated: CharSequence = "...",
    transform: ((String) -> CharSequence)? = null
): String {
    if (n <= 0) return ""
    if (n == 1) return this

    val strList = listOf(this) * n
    return strList.joinToString(separator, prefix, postfix, limit, truncated, transform)
}