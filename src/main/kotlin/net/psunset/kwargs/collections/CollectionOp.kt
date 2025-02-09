package net.psunset.kwargs.collections

/**
 * Idea from python.
 *
 * For example:
 * ```
 * val list = mutableListOf(0, 1, 2)
 * val list2 = list * 2
 * println(list) // Result: [0, 1, 2]
 * // It stands for list isn't changed though you use this function or operator.
 * println(list2) // Result: [0, 1, 2, 0, 1, 2]
 * // It is successful!
 * ```
 * @return [EmptyList] if `n` is less than 1; otherwise, A new list which has `n` elements in the raw collection.
 */
operator fun <T: Any> Iterable<T>.times(n: Int): List<T> {
    if (n <= 0) return emptyList()
    if (n == 1) return this.toList()

    val newList = this.toMutableList()
    for (i in 1 until n) {
        newList += this
    }
    return newList
}

/**
 * The feature is same as [Iterable.times] function which is preceding this function.
 * But make it available in an array.
 */
inline operator fun <reified T: Any> Array<T>.times(n: Int): Array<T> {
    if (n <= 0) throw IllegalArgumentException("`n` should be a positive value because the length of an array should be greater than 0.")
    if (n == 1) return this.clone()

    val newList = this.toMutableList()
    for (i in 1 until n) {
        newList += this
    }
    return newList.toTypedArray()
}

/**
 * Idea from python.
 * If `n` is less than 1, this mutable list object will be empty and convert into a [List];
 * otherwise, this list will be stretch and the data inside is duplicated.
 * For example:
 * ```
 * val list = mutableListOf(0, 1, 2)
 * list *= 3
 * println(list) // Result: [0, 1, 2, 0, 1, 2, 0, 1, 2]
 * list *= 0
 * println(list) // Result: []
 * ```
 * In the example, I use `val` instead `var`, but it's still working after I call `*=` operator.
 * It's because that we make the list directly update the elements without changing the list's pointer.
 *
 * But If you're using an immutable list, the compiler will crash.
 * Because, in fact, it calls [Iterable.times] first, and then update its pointer.
 * ```
 * val list = listOf(0, 1, 2)
 * list *= 3 // Error: val cannot be reassigned
 * ```
 * There are two solutions: one is using `var` instead, the other is make the list a mutable list by [toMutableList]
 * ```
 * var list = listOf(0, 1, 2)
 * // or
 * val list = listOf(0, 1, 2).toMutableList()
 * ```
 */
operator fun <T: Any> MutableList<T>.timesAssign(n: Int) {
    if (n <= 0) {
        this.removeAll { true }
        return
    }
    if (n == 1) return

    val elements = this.toList()
    for (i in 1 until n) {
        this.addAll(elements)
    }
}

