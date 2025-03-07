package net.psunset.pytlin.collections

/**
 * Calls [List.slice]
 */
operator fun <T> List<T>.get(indices: IntRange): List<T> =
    this.slice(indices)

/**
 * Calls [List.slice]
 */
operator fun <T> List<T>.get(indices: Iterable<Int>): List<T> =
    this.slice(indices)

/**
 * Calls [Array.slice]
 */
operator fun <T> Array<out T>.get(indices: IntRange): List<T> =
    this.slice(indices)

/**
 * Calls [Array.slice]
 */
operator fun <T> Array<out T>.get(indices: Iterable<Int>): List<T> =
    this.slice(indices)

/**
 * Calls [ByteArray.slice]
 */
operator fun ByteArray.get(indices: IntRange): List<Byte> =
    this.slice(indices)

/**
 * Calls [ByteArray.slice]
 */
operator fun ByteArray.get(indices: Iterable<Int>): List<Byte> =
    this.slice(indices)

/**
 * Calls [CharArray.slice]
 */
operator fun CharArray.get(indices: IntRange): List<Char> =
    this.slice(indices)

/**
 * Calls [CharArray.slice]
 */
operator fun CharArray.get(indices: Iterable<Int>): List<Char> =
    this.slice(indices)

/**
 * Calls [ShortArray.slice]
 */
operator fun ShortArray.get(indices: IntRange): List<Short> =
    this.slice(indices)

/**
 * Calls [ShortArray.slice]
 */
operator fun ShortArray.get(indices: Iterable<Int>): List<Short> =
    this.slice(indices)

/**
 * Calls [IntArray.slice]
 */
operator fun IntArray.get(indices: IntRange): List<Int> =
    this.slice(indices)

/**
 * Calls [IntArray.slice]
 */
operator fun IntArray.get(indices: Iterable<Int>): List<Int> =
    this.slice(indices)

/**
 * Calls [LongArray.slice]
 */
operator fun LongArray.get(indices: IntRange): List<Long> =
    this.slice(indices)

/**
 * Calls [LongArray.slice]
 */
operator fun LongArray.get(indices: Iterable<Int>): List<Long> =
    this.slice(indices)

/**
 * Calls [FloatArray.slice]
 */
operator fun FloatArray.get(indices: IntRange): List<Float> =
    this.slice(indices)

/**
 * Calls [FloatArray.slice]
 */
operator fun FloatArray.get(indices: Iterable<Int>): List<Float> =
    this.slice(indices)

/**
 * Calls [DoubleArray.slice]
 */
operator fun DoubleArray.get(indices: IntRange): List<Double> =
    this.slice(indices)

/**
 * Calls [DoubleArray.slice]
 */
operator fun DoubleArray.get(indices: Iterable<Int>): List<Double> =
    this.slice(indices)

/**
 * Calls [BooleanArray.slice]
 */
operator fun BooleanArray.get(indices: IntRange): List<Boolean> =
    this.slice(indices)

/**
 * Calls [BooleanArray.slice]
 */
operator fun BooleanArray.get(indices: Iterable<Int>): List<Boolean> =
    this.slice(indices)

/**
 * Calls [UByteArray.slice]
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.get(indices: IntRange): List<UByte> =
    this.slice(indices)

/**
 * Calls [UByteArray.slice]
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.get(indices: Iterable<Int>): List<UByte> =
    this.slice(indices)

/**
 * Calls [UShortArray.slice]
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.get(indices: IntRange): List<UShort> =
    this.slice(indices)

/**
 * Calls [UShortArray.slice]
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.get(indices: Iterable<Int>): List<UShort> =
    this.slice(indices)

/**
 * Calls [UIntArray.slice]
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.get(indices: IntRange): List<UInt> =
    this.slice(indices)

/**
 * Calls [UIntArray.slice]
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.get(indices: Iterable<Int>): List<UInt> =
    this.slice(indices)

/**
 * Calls [ULongArray.slice]
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.get(indices: IntRange): List<ULong> =
    this.slice(indices)

/**
 * Calls [ULongArray.slice]
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.get(indices: Iterable<Int>): List<ULong> =
    this.slice(indices)

fun String.toIndex(size: Int) = this.toInt().let { if (it >= 0) it else size + it }

/**
 * It's too convenient to use slice in a python array.
 * Though Kotlin provide [slice] function, too.
 * It can't write with a format as clear as python one.
 *
 * But now, it's available in kotlin by this function.
 * All the difference between this function and the python array slice is that
 * Kotlin doesn't allow us to define the usage of colon signs.
 * So you need to make the slices in python a string here.
 *
 * Automatically turning negative index into expected one is also available.
 *
 * For example:
 * ```
 * val a = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
 *
 * println(a["-3"]) // Result: [8]
 * println(a["1:-3"]) // Result: [2, 3, 4, 5, 6, 7]
 * println(a["7:2:-2"]) // Result: [8, 6, 4]
 * println(a["1:-1:3"]) // Result: [2, 5, 8]
 * ```
 *
 * Also, there are some special cases that are also available in python.
 * ```
 * println(a[":"]) // Result: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
 * // `a[":"]` stands for `a.clone()`
 * println(a["::-1"]) // Result: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
 * // `a["::-1"]` stands for `a.reversed()`
 * ```
 *
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of list if the slice pattern is valid, an empty list otherwise.
 */
operator fun <T> List<T>.get(pattern: String): List<T> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }

    val p = pattern.replace(" ", "") // Remove all whitespace
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }

    if (':' !in p) return listOf(this[p.toIndex(this.size)]) // pattern is a normal int
    if (p.all { it == ':' }) return this.toList() // returns a clone if pattern equals ":" or "::"

    // Define the default value for slice
    var start: Int = 0
    var end: Int = this.size // exclusive
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
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)

    end += if (step >= 0) -1 else 1 // Make the end an inclusive index

    if (step == 1) return this[start..end] // Directly returns an IntRange

    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see List.get get(pattern: String) which is preceding this function
 */
operator fun <T> Array<out T>.get(pattern: String): List<T> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) return listOf(this[p.toIndex(this.size)])
    if (p.all { it == ':' }) return this.toList()
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see Array.get get(pattern: String)
 */
operator fun ByteArray.get(pattern: String): List<Byte> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) return listOf(this[p.toIndex(this.size)])
    if (p.all { it == ':' }) return this.toList()
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see Array.get get(pattern: String)
 */
operator fun CharArray.get(pattern: String): List<Char> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) return listOf(this[p.toIndex(this.size)])
    if (p.all { it == ':' }) return this.toList()
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}


/**
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see Array.get get(pattern: String)
 */
operator fun ShortArray.get(pattern: String): List<Short> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) return listOf(this[p.toIndex(this.size)])
    if (p.all { it == ':' }) return this.toList()
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see Array.get get(pattern: String)
 */
operator fun IntArray.get(pattern: String): List<Int> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) return listOf(this[p.toIndex(this.size)])
    if (p.all { it == ':' }) return this.toList()
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see Array.get get(pattern: String)
 */
operator fun LongArray.get(pattern: String): List<Long> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) return listOf(this[p.toIndex(this.size)])
    if (p.all { it == ':' }) return this.toList()
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see Array.get get(pattern: String)
 */
operator fun FloatArray.get(pattern: String): List<Float> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) return listOf(this[p.toIndex(this.size)])
    if (p.all { it == ':' }) return this.toList()
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see Array.get get(pattern: String)
 */
operator fun DoubleArray.get(pattern: String): List<Double> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) return listOf(this[p.toIndex(this.size)])
    if (p.all { it == ':' }) return this.toList()
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see Array.get get(pattern: String)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.get(pattern: String): List<UByte> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) return listOf(this[p.toIndex(this.size)])
    if (p.all { it == ':' }) return this.toList()
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see Array.get get(pattern: String)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.get(pattern: String): List<UShort> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) return listOf(this[p.toIndex(this.size)])
    if (p.all { it == ':' }) return this.toList()
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see Array.get get(pattern: String)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.get(pattern: String): List<UInt> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) return listOf(this[p.toIndex(this.size)])
    if (p.all { it == ':' }) return this.toList()
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see Array.get get(pattern: String)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.get(pattern: String): List<ULong> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) return listOf(this[p.toIndex(this.size)])
    if (p.all { it == ':' }) return this.toList()
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see Array.get get(pattern: String)
 */
operator fun BooleanArray.get(pattern: String): List<Boolean> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) return listOf(this[p.toIndex(this.size)])
    if (p.all { it == ':' }) return this.toList()
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

operator fun <T> MutableList<T>.set(indices: Iterable<Int>, elements: List<T>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun <T> MutableList<T>.set(indices: IntProgression, elements: List<T>) {
    if (indices.count() == elements.size) {
        indices.forEachIndexed { index, i -> this[i] = elements[index] }
    } else if (indices.step == 1) {
        val f = indices.first
        indices.forEach { _ -> this.removeAt(f) }
        this.addAll(f, elements)
    } else {
        throw IllegalArgumentException("Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()} or the slice step equals to 1")
    }
}

operator fun <T> MutableList<T>.set(indices: IntRange, elements: List<T>) {
    if (indices.count() == elements.size) {
        indices.forEachIndexed { index, i -> this[i] = elements[index] }
    } else {
        val f = indices.first
        indices.forEach { _ -> this.removeAt(f) }
        this.addAll(f, elements)
    }
}

operator fun <T> MutableList<T>.set(indices: Iterable<Int>, elements: Array<out T>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun <T> MutableList<T>.set(indices: IntProgression, elements: Array<out T>) {
    if (indices.count() == elements.size) {
        indices.forEachIndexed { index, i -> this[i] = elements[index] }
    } else if (indices.step == 1) {
        val f = indices.first
        indices.forEach { _ -> this.removeAt(f) }
        this.addAll(f, elements.asList())
    } else {
        throw IllegalArgumentException("Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()} or the slice step equals to 1")
    }
}

operator fun <T> MutableList<T>.set(indices: IntRange, elements: Array<out T>) {
    if (indices.count() == elements.size) {
        indices.forEachIndexed { index, i -> this[i] = elements[index] }
    } else {
        val f = indices.first
        indices.forEach { _ -> this.removeAt(f) }
        this.addAll(f, elements.asList())
    }
}

operator fun <T> Array<T>.set(indices: Iterable<Int>, elements: List<T>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun <T> Array<T>.set(indices: Iterable<Int>, elements: Array<out T>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun ByteArray.set(indices: Iterable<Int>, elements: List<Byte>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun ByteArray.set(indices: Iterable<Int>, elements: Array<out Byte>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun ByteArray.set(indices: Iterable<Int>, elements: ByteArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun CharArray.set(indices: Iterable<Int>, elements: List<Char>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun CharArray.set(indices: Iterable<Int>, elements: Array<out Char>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun CharArray.set(indices: Iterable<Int>, elements: CharArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun ShortArray.set(indices: Iterable<Int>, elements: List<Short>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun ShortArray.set(indices: Iterable<Int>, elements: Array<out Short>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun ShortArray.set(indices: Iterable<Int>, elements: ShortArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun IntArray.set(indices: Iterable<Int>, elements: List<Int>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun IntArray.set(indices: Iterable<Int>, elements: Array<out Int>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun IntArray.set(indices: Iterable<Int>, elements: IntArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun LongArray.set(indices: Iterable<Int>, elements: List<Long>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun LongArray.set(indices: Iterable<Int>, elements: Array<out Long>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun LongArray.set(indices: Iterable<Int>, elements: LongArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun FloatArray.set(indices: Iterable<Int>, elements: List<Float>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun FloatArray.set(indices: Iterable<Int>, elements: Array<out Float>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun FloatArray.set(indices: Iterable<Int>, elements: FloatArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun DoubleArray.set(indices: Iterable<Int>, elements: List<Double>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun DoubleArray.set(indices: Iterable<Int>, elements: Array<out Double>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun DoubleArray.set(indices: Iterable<Int>, elements: DoubleArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun BooleanArray.set(indices: Iterable<Int>, elements: List<Boolean>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun BooleanArray.set(indices: Iterable<Int>, elements: Array<out Boolean>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun BooleanArray.set(indices: Iterable<Int>, elements: BooleanArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.set(indices: Iterable<Int>, elements: List<UByte>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.set(indices: Iterable<Int>, elements: Array<out UByte>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.set(indices: Iterable<Int>, elements: UByteArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.set(indices: Iterable<Int>, elements: List<UShort>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.set(indices: Iterable<Int>, elements: Array<out UShort>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.set(indices: Iterable<Int>, elements: UShortArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.set(indices: Iterable<Int>, elements: List<UInt>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.set(indices: Iterable<Int>, elements: Array<out UInt>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.set(indices: Iterable<Int>, elements: UIntArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.set(indices: Iterable<Int>, elements: List<ULong>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.set(indices: Iterable<Int>, elements: Array<out ULong>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.set(indices: Iterable<Int>, elements: ULongArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

operator fun <T> MutableList<T>.set(pattern: String, elements: List<T>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun <T> MutableList<T>.set(pattern: String, elements: Array<out T>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun <T> MutableList<T>.set(pattern: String, element: T) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    if (':' in p) {
        throw IllegalArgumentException("Slices should be without any colon when there is only an element joins in.")
    }
    this[p.toIndex(this.size)] = element
}

operator fun <T> Array<T>.set(pattern: String, elements: List<T>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    }
    if (p.all { it == ':' }) {
        require(this.size == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${this.size}" }
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun <T> Array<T>.set(pattern: String, elements: Array<out T>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun <T> Array<T>.set(pattern: String, element: T) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    if (':' in p) {
        throw IllegalArgumentException("Slices should be without any colon when there is only an element joins in.")
    }
    this[p.toIndex(this.size)] = element
}

operator fun ByteArray.set(pattern: String, elements: List<Byte>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    }
    if (p.all { it == ':' }) {
        require(this.size == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${this.size}" }
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun ByteArray.set(pattern: String, elements: Array<out Byte>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun ByteArray.set(pattern: String, elements: ByteArray) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun ByteArray.set(pattern: String, element: Byte) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    if (':' in p) {
        throw IllegalArgumentException("Slices should be without any colon when there is only an element joins in.")
    }
    this[p.toIndex(this.size)] = element
}

operator fun CharArray.set(pattern: String, elements: List<Char>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    }
    if (p.all { it == ':' }) {
        require(this.size == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${this.size}" }
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun CharArray.set(pattern: String, elements: Array<out Char>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun CharArray.set(pattern: String, elements: CharArray) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun CharArray.set(pattern: String, element: Char) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    if (':' in p) {
        throw IllegalArgumentException("Slices should be without any colon when there is only an element joins in.")
    }
    this[p.toIndex(this.size)] = element
}

operator fun ShortArray.set(pattern: String, elements: List<Short>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    }
    if (p.all { it == ':' }) {
        require(this.size == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${this.size}" }
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun ShortArray.set(pattern: String, elements: Array<out Short>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun ShortArray.set(pattern: String, elements: ShortArray) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun ShortArray.set(pattern: String, element: Short) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    if (':' in p) {
        throw IllegalArgumentException("Slices should be without any colon when there is only an element joins in.")
    }
    this[p.toIndex(this.size)] = element
}

operator fun IntArray.set(pattern: String, elements: List<Int>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    }
    if (p.all { it == ':' }) {
        require(this.size == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${this.size}" }
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun IntArray.set(pattern: String, elements: Array<out Int>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun IntArray.set(pattern: String, elements: IntArray) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun IntArray.set(pattern: String, element: Int) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    if (':' in p) {
        throw IllegalArgumentException("Slices should be without any colon when there is only an element joins in.")
    }
    this[p.toIndex(this.size)] = element
}

operator fun LongArray.set(pattern: String, elements: List<Long>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    }
    if (p.all { it == ':' }) {
        require(this.size == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${this.size}" }
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun LongArray.set(pattern: String, elements: Array<out Long>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun LongArray.set(pattern: String, elements: LongArray) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun LongArray.set(pattern: String, element: Long) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    if (':' in p) {
        throw IllegalArgumentException("Slices should be without any colon when there is only an element joins in.")
    }
    this[p.toIndex(this.size)] = element
}

operator fun FloatArray.set(pattern: String, elements: List<Float>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    }
    if (p.all { it == ':' }) {
        require(this.size == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${this.size}" }
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun FloatArray.set(pattern: String, elements: Array<out Float>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun FloatArray.set(pattern: String, elements: FloatArray) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun FloatArray.set(pattern: String, element: Float) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    if (':' in p) {
        throw IllegalArgumentException("Slices should be without any colon when there is only an element joins in.")
    }
    this[p.toIndex(this.size)] = element
}

operator fun DoubleArray.set(pattern: String, elements: List<Double>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    }
    if (p.all { it == ':' }) {
        require(this.size == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${this.size}" }
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun DoubleArray.set(pattern: String, elements: Array<out Double>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun DoubleArray.set(pattern: String, elements: DoubleArray) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun DoubleArray.set(pattern: String, element: Double) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    if (':' in p) {
        throw IllegalArgumentException("Slices should be without any colon when there is only an element joins in.")
    }
    this[p.toIndex(this.size)] = element
}

operator fun BooleanArray.set(pattern: String, elements: List<Boolean>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    }
    if (p.all { it == ':' }) {
        require(this.size == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${this.size}" }
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun BooleanArray.set(pattern: String, elements: Array<out Boolean>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun BooleanArray.set(pattern: String, elements: BooleanArray) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

operator fun BooleanArray.set(pattern: String, element: Boolean) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    if (':' in p) {
        throw IllegalArgumentException("Slices should be without any colon when there is only an element joins in.")
    }
    this[p.toIndex(this.size)] = element
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.set(pattern: String, elements: List<UByte>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    }
    if (p.all { it == ':' }) {
        require(this.size == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${this.size}" }
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.set(pattern: String, elements: Array<out UByte>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.set(pattern: String, elements: UByteArray) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.set(pattern: String, element: UByte) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    if (':' in p) {
        throw IllegalArgumentException("Slices should be without any colon when there is only an element joins in.")
    }
    this[p.toIndex(this.size)] = element
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.set(pattern: String, elements: List<UShort>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    }
    if (p.all { it == ':' }) {
        require(this.size == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${this.size}" }
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.set(pattern: String, elements: Array<out UShort>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.set(pattern: String, elements: UShortArray) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.set(pattern: String, element: UShort) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    if (':' in p) {
        throw IllegalArgumentException("Slices should be without any colon when there is only an element joins in.")
    }
    this[p.toIndex(this.size)] = element
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.set(pattern: String, elements: List<UInt>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    }
    if (p.all { it == ':' }) {
        require(this.size == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${this.size}" }
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.set(pattern: String, elements: Array<out UInt>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.set(pattern: String, elements: UIntArray) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.set(pattern: String, element: UInt) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    if (':' in p) {
        throw IllegalArgumentException("Slices should be without any colon when there is only an element joins in.")
    }
    this[p.toIndex(this.size)] = element
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.set(pattern: String, elements: List<ULong>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    }
    if (p.all { it == ':' }) {
        require(this.size == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${this.size}" }
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.set(pattern: String, elements: Array<out ULong>) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.set(pattern: String, elements: ULongArray) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    if (':' !in p) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of an array can't be changed.")
    }
    if (p.all { it == ':' }) {
        this.indices.forEach { this[it] = elements[it] }
        return
    }
    var start: Int = 0
    var end: Int = this.size
    var step: Int = 1
    if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
        step = pSplit[2].toInt()
        if (step < 0) {
            start = this.lastIndex
            end = -1
        }
    }
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(this.size)
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex(this.size)
    end += if (step >= 0) -1 else 1
    if (step == 1) {
        this[start..end] = elements
        return
    }
    this[IntProgression.fromClosedRange(start, end, step)] = elements
}

@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.set(pattern: String, element: ULong) {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    if (':' in p) {
        throw IllegalArgumentException("Slices should be without any colon when there is only an element joins in.")
    }
    this[p.toIndex(this.size)] = element
}
