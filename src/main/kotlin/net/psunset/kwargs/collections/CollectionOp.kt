package net.psunset.kwargs.collections

/**
 * Idea from python.
 *
 * For example:
 * ```
 * val arr = mutableListOf(0, 1, 2)
 * val l = arr * 2
 * println(arr) // Result: [0, 1, 2]
 * // It stands for arr isn't changed though you use this function or operator.
 * println(l) // Result: [0, 1, 2, 0, 1, 2]
 * // It is successful!
 * ```
 * @return [EmptyList] if `t` is less than 1; otherwise, A new list which has `t` times elements in the raw collection.
 */
operator fun <T: Any> Iterable<T>.times(t: Int): List<T> {
    if (t <= 0) {
        return emptyList()
    }

    if (t == 1) {
        return this.toList()
    }

    val newList = this.toMutableList()
    for (i in 1 until t) {
        newList.addAll(this)
    }
    return newList
}

/**
 * Idea from python.
 * If `t` is less than 1, this mutable list object will be empty and convert into a [List];
 * otherwise, this list will be stretch and the data inside is duplicated.
 * For example:
 * ```
 * val arr = mutableListOf(0, 1, 2)
 * arr *= 3
 * println(arr) // Result: [0, 1, 2, 0, 1, 2, 0, 1, 2]
 * arr *= 0
 * println(arr) // Result: []
 * ```
 * In the example, I use `val` instead `var`, but it's still working after I call `*=` operator.
 * It's because that we make the list directly update the elements without changing the list's pointer.
 *
 * But If you're using an immutable list, the compiler will crash.
 * Because, in fact, it calls [Iterable.times] first, and then update its pointer.
 * ```
 * val arr = listOf(0, 1, 2)
 * arr *= 3 // Error: val cannot be reassigned
 * ```
 * There are two solutions: one is using `var` instead, the other is make the list a mutable list by [toMutableList]
 * ```
 * var arr = listOf(0, 1, 2)
 * // or
 * val arr = listOf(0, 1, 2).toMutableList()
 * ```
 */
operator fun <T: Any> MutableList<T>.timesAssign(t: Int) {
    if (t <= 0) {
        this.removeAll { true }
        return
    }

    if (t == 1) {
        return
    }

    val elements = this.toList()
    for (i in 1 until t) {
        this.addAll(elements)
    }
}

