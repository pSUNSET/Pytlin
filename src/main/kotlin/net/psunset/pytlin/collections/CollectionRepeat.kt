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
 * @return [EmptyList] if [n] is less than 1; otherwise, A new list containing elements in the raw collection repeated [n] time.
 */
operator fun <T> List<T>.times(n: Int): List<T> {
    if (n <= 0) return emptyList()
    if (n == 1) return this.toList()

    val newList = MutableList(size * n) { this[0] }
    for (i in 1..<n) {
        for (ii in 1..<size) {
            newList[i * size + ii] = this[ii]
        }
    }
    return newList.subList(0, size * n)
}

/**
 * The feature is same as [List.times] function which is preceding this function.
 * But make it available in an array.
 */
inline operator fun <reified T> Array<out T>.times(n: Int): List<T> {
    if (n <= 0) throw IllegalArgumentException("`n` should be a positive value because the length of an array should be greater than 0.")
    if (n == 1) return this.toList()

    val newList = MutableList(size * n) { this[0] }
    for (i in 1..<n) {
        for (ii in 1..<size) {
            newList[i * size + ii] = this[ii]
        }
    }
    return newList
}

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [ByteArray].
 */
operator fun ByteArray.times(n: Int): List<Byte> {
    if (n <= 0) throw IllegalArgumentException("`n` should be a positive value because the length of an array should be greater than 0.")
    if (n == 1) return this.toList()

    val newList = MutableList(size * n) { this[0] }
    for (i in 1..<n) {
        for (ii in 1..<size) {
            newList[i * size + ii] = this[ii]
        }
    }
    return newList
}

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [CharArray].
 */
operator fun CharArray.times(n: Int): List<Char> {
    if (n <= 0) throw IllegalArgumentException("`n` should be a positive value because the length of an array should be greater than 0.")
    if (n == 1) return this.toList()

    val newList = MutableList(size * n) { this[0] }
    for (i in 1..<n) {
        for (ii in 1..<size) {
            newList[i * size + ii] = this[ii]
        }
    }
    return newList
}

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [ShortArray].
 */
operator fun ShortArray.times(n: Int): List<Short> {
    if (n <= 0) throw IllegalArgumentException("`n` should be a positive value because the length of an array should be greater than 0.")
    if (n == 1) return this.toList()

    val newList = MutableList(size * n) { this[0] }
    for (i in 1..<n) {
        for (ii in 1..<size) {
            newList[i * size + ii] = this[ii]
        }
    }
    return newList
}

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [IntArray].
 */
operator fun IntArray.times(n: Int): List<Int> {
    if (n <= 0) throw IllegalArgumentException("`n` should be a positive value because the length of an array should be greater than 0.")
    if (n == 1) return this.toList()

    val newList = MutableList(size * n) { this[0] }
    for (i in 1..<n) {
        for (ii in 1..<size) {
            newList[i * size + ii] = this[ii]
        }
    }
    return newList
}

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [LongArray].
 */
operator fun LongArray.times(n: Int): List<Long> {
    if (n <= 0) throw IllegalArgumentException("`n` should be a positive value because the length of an array should be greater than 0.")
    if (n == 1) return this.toList()

    val newList = MutableList(size * n) { this[0] }
    for (i in 1..<n) {
        for (ii in 1..<size) {
            newList[i * size + ii] = this[ii]
        }
    }
    return newList
}

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [FloatArray].
 */
operator fun FloatArray.times(n: Int): List<Float> {
    if (n <= 0) throw IllegalArgumentException("`n` should be a positive value because the length of an array should be greater than 0.")
    if (n == 1) return this.toList()

    val newList = MutableList(size * n) { this[0] }
    for (i in 1..<n) {
        for (ii in 1..<size) {
            newList[i * size + ii] = this[ii]
        }
    }
    return newList
}

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [DoubleArray].
 */
operator fun DoubleArray.times(n: Int): List<Double> {
    if (n <= 0) throw IllegalArgumentException("`n` should be a positive value because the length of an array should be greater than 0.")
    if (n == 1) return this.toList()

    val newList = MutableList(size * n) { this[0] }
    for (i in 1..<n) {
        for (ii in 1..<size) {
            newList[i * size + ii] = this[ii]
        }
    }
    return newList
}

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [BooleanArray].
 */
operator fun BooleanArray.times(n: Int): List<Boolean> {
    if (n <= 0) throw IllegalArgumentException("`n` should be a positive value because the length of an array should be greater than 0.")
    if (n == 1) return this.toList()

    val newList = MutableList(size * n) { this[0] }
    for (i in 1..<n) {
        for (ii in 1..<size) {
            newList[i * size + ii] = this[ii]
        }
    }
    return newList
}

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [UByteArray].
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.times(n: Int): List<UByte> {
    if (n <= 0) throw IllegalArgumentException("`n` should be a positive value because the length of an array should be greater than 0.")
    if (n == 1) return this.toList()

    val newList = MutableList(size * n) { this[0] }
    for (i in 1..<n) {
        for (ii in 1..<size) {
            newList[i * size + ii] = this[ii]
        }
    }
    return newList
}

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [UShortArray].
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.times(n: Int): List<UShort> {
    if (n <= 0) throw IllegalArgumentException("`n` should be a positive value because the length of an array should be greater than 0.")
    if (n == 1) return this.toList()

    val newList = MutableList(size * n) { this[0] }
    for (i in 1..<n) {
        for (ii in 1..<size) {
            newList[i * size + ii] = this[ii]
        }
    }
    return newList
}

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [UIntArray].
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.times(n: Int): List<UInt> {
    if (n <= 0) throw IllegalArgumentException("`n` should be a positive value because the length of an array should be greater than 0.")
    if (n == 1) return this.toList()

    val newList = MutableList(size * n) { this[0] }
    for (i in 1..<n) {
        for (ii in 1..<size) {
            newList[i * size + ii] = this[ii]
        }
    }
    return newList
}

/**
 * The feature is same as [Array.times] function.
 * But make it available in a [ULongArray].
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.times(n: Int): List<ULong> {
    if (n <= 0) throw IllegalArgumentException("`n` should be a positive value because the length of an array should be greater than 0.")
    if (n == 1) return this.toList()

    val newList = MutableList(size * n) { this[0] }
    for (i in 1..<n) {
        for (ii in 1..<size) {
            newList[i * size + ii] = this[ii]
        }
    }
    return newList
}

/**
 * If [n] is less than 1, clear all elements in this list;
 * otherwise, the elements in this list are going to be repeated [n] time.
 * For example:
 * ```
 * val list = mutableListOf(0, 1, 2)
 * list *= 3
 * println(list) // Result: [0, 1, 2, 0, 1, 2, 0, 1, 2]
 * list *= 0
 * println(list) // Result: []
 * ```
 * In the example, I use `val` instead `var`.
 * It's still working after calling `*=` operator,
 * because making the list directly update the elements without changing the list's pointer.
 *
 * But If you're using an immutable list, the preceding code is going to be on error.
 * Because, it calls [List.times] first, and then update its pointer.
 * ```
 * val list = listOf(0, 1, 2)
 * list *= 3 // Error: val cannot be reassigned
 * ```
 * There are two solutions: one is using `var` instead, the other is make the list mutable by [toMutableList]
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
