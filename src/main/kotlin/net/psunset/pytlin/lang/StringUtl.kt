@file:JvmName("StringUtil")

package net.psunset.pytlin.lang

import net.psunset.pytlin.collections.Kwargs
import net.psunset.pytlin.collections.get
import net.psunset.pytlin.collections.times

/**
 * Call [CharSequence.repeat]
 * @return A string made with `n` repeat raw string.
 * @see CharSequence.repeat
 */
operator fun CharSequence.times(n: Int): String = this.repeat(n)

/**
 * Call [CharSequence.repeat] function which is below this function.
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
        kwargs["separator", kwargs["sep", ""]] // Accept using either "separator" or "sep" simply.
    val prefix = kwargs["prefix", ""]
    val postfix = kwargs ["postfix", ""]
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
 * Parses the slice pattern and calls [CharSequence.slice]
 */
operator fun CharSequence.get(pattern: String): CharSequence{
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }

    val p = pattern.replace(" ", "") // Remove all whitespace
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }

    // Define a function make the negative index the expected one
    fun String.toIndex() = this.toInt().let { if (it >= 0) it else this@get.length + it }

    if (':' !in p) return this[p.toIndex()].toString() // pattern is a normal int
    if (p.all { it == ':' }) return this // returns a clone if pattern equals ":" or "::"

    // Define the default value for slice
    var start: Int = 0
    var end: Int = this.length // exclusive
    var step: Int = 1

    // Edit the slice properties if user provides
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            // The Default value is different when the step is negative.
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex()
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex()

    end += if (step >= 0) -1 else 1 // Make the end an inclusive index

    if (step == 1) return this[start..end] // Directly returns an IntRange

    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * Parses the slice pattern and calls [String.slice]
 */
operator fun String.get(pattern: String): String{
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }

    val p = pattern.replace(" ", "") // Remove all whitespace
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }

    // Define a function make the negative index the expected one
    fun String.toIndex() = this.toInt().let { if (it >= 0) it else this@get.length + it }

    if (':' !in p) return this[p.toIndex()].toString() // pattern is a normal int
    if (p.all { it == ':' }) return this // returns a clone if pattern equals ":" or "::"

    // Define the default value for slice
    var start: Int = 0
    var end: Int = this.length // exclusive
    var step: Int = 1

    // Edit the slice properties if user provides
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            // The Default value is different when the step is negative.
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex()
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex()

    end += if (step >= 0) -1 else 1 // Make the end an inclusive index

    if (step == 1) return this.slice(start..end) // Directly returns an IntRange

    return this[IntProgression.fromClosedRange(start, end, step)]
}