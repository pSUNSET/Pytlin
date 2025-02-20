package net.psunset.pytlin.collections

/**
 * For example:
 * ```
 * val list = mutableListOf(0, 1, 2)
 * val list2 = list * 2
 * println(list) // Result: [0, 1, 2]
 * // It stands for list isn't changed though you use this function or operator.
 * println(list2) // Result: [0, 1, 2, 0, 1, 2]
 * // It is successful!
 * ```
 * @return [EmptyList] if `n` is less than 1; otherwise, A new list which has `n` repeat elements in the raw collection.
 */
operator fun <T> List<T>.times(n: Int): List<T> {
    if (n <= 0) return emptyList()
    if (n == 1) return this.toList()

    val newList = this.toMutableList()
    for (i in 1..<n) {
        newList += this
    }
    return newList
}

/**
 * The feature is same as [List.times] function which is preceding this function.
 * But make it available in an array.
 */
inline operator fun <reified T> Array<out T>.times(n: Int): Array<out T> {
    if (n <= 0) throw IllegalArgumentException("`n` should be a positive value because the length of an array should be greater than 0.")
    if (n == 1) return this.clone()

    val newList = this.toMutableList()
    for (i in 1..<n) {
        newList += this
    }
    return newList.toTypedArray()
}

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [ByteArray].
 */
operator fun ByteArray.times(n: Int): ByteArray = (this.toTypedArray() * n).toByteArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [CharArray].
 */
operator fun CharArray.times(n: Int): CharArray = (this.toTypedArray() * n).toCharArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [ShortArray].
 */
operator fun ShortArray.times(n: Int): ShortArray = (this.toTypedArray() * n).toShortArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [IntArray].
 */
operator fun IntArray.times(n: Int): IntArray = (this.toTypedArray() * n).toIntArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [LongArray].
 */
operator fun LongArray.times(n: Int): LongArray = (this.toTypedArray() * n).toLongArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [FloatArray].
 */
operator fun FloatArray.times(n: Int): FloatArray = (this.toTypedArray() * n).toFloatArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [DoubleArray].
 */
operator fun DoubleArray.times(n: Int): DoubleArray = (this.toTypedArray() * n).toDoubleArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [BooleanArray].
 */
operator fun BooleanArray.times(n: Int): BooleanArray = (this.toTypedArray() * n).toBooleanArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [UByteArray].
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.times(n: Int): UByteArray = (this.toTypedArray() * n).toUByteArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [UShortArray].
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.times(n: Int): UShortArray = (this.toTypedArray() * n).toUShortArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [UIntArray].
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.times(n: Int): UIntArray = (this.toTypedArray() * n).toUIntArray()

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [ULongArray].
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.times(n: Int): ULongArray = (this.toTypedArray() * n).toULongArray()

/**
 * If `n` is less than 1, this mutable list object will be empty;
 * otherwise, this list will be stretched with repeat elements.
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
 * Because, in fact, it calls [List.times] first, and then update its pointer.
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
operator fun <T> MutableList<T>.timesAssign(n: Int) {
    if (n <= 0) {
        this.removeAll { true }
        return
    }
    if (n == 1) return

    val elements = this.toList()
    for (i in 1..<n) {
        this.addAll(elements)
    }
}
