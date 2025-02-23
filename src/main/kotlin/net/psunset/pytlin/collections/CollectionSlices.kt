package net.psunset.pytlin.collections

/**
 * Calls [List.slice]
 */
operator fun <T> List<T>.get(indies: IntRange): List<T> =
    this.slice(indies)

/**
 * Calls [List.slice]
 */
operator fun <T> List<T>.get(indies: Iterable<Int>): List<T> =
    this.slice(indies)

/**
 * Calls [Array.slice]
 */
operator fun <T> Array<out T>.get(indies: IntRange): List<T> =
    this.slice(indies)

/**
 * Calls [Array.slice]
 */
operator fun <T> Array<out T>.get(indies: Iterable<Int>): List<T> =
    this.slice(indies)

/**
 * Calls [ByteArray.slice]
 */
operator fun ByteArray.get(indies: IntRange): List<Byte> =
    this.slice(indies)

/**
 * Calls [ByteArray.slice]
 */
operator fun ByteArray.get(indies: Iterable<Int>): List<Byte> =
    this.slice(indies)

/**
 * Calls [CharArray.slice]
 */
operator fun CharArray.get(indies: IntRange): List<Char> =
    this.slice(indies)

/**
 * Calls [CharArray.slice]
 */
operator fun CharArray.get(indies: Iterable<Int>): List<Char> =
    this.slice(indies)

/**
 * Calls [ShortArray.slice]
 */
operator fun ShortArray.get(indies: IntRange): List<Short> =
    this.slice(indies)

/**
 * Calls [ShortArray.slice]
 */
operator fun ShortArray.get(indies: Iterable<Int>): List<Short> =
    this.slice(indies)

/**
 * Calls [IntArray.slice]
 */
operator fun IntArray.get(indies: IntRange): List<Int> =
    this.slice(indies)

/**
 * Calls [IntArray.slice]
 */
operator fun IntArray.get(indies: Iterable<Int>): List<Int> =
    this.slice(indies)

/**
 * Calls [LongArray.slice]
 */
operator fun LongArray.get(indies: IntRange): List<Long> =
    this.slice(indies)

/**
 * Calls [LongArray.slice]
 */
operator fun LongArray.get(indies: Iterable<Int>): List<Long> =
    this.slice(indies)

/**
 * Calls [FloatArray.slice]
 */
operator fun FloatArray.get(indies: IntRange): List<Float> =
    this.slice(indies)

/**
 * Calls [FloatArray.slice]
 */
operator fun FloatArray.get(indies: Iterable<Int>): List<Float> =
    this.slice(indies)

/**
 * Calls [DoubleArray.slice]
 */
operator fun DoubleArray.get(indies: IntRange): List<Double> =
    this.slice(indies)

/**
 * Calls [DoubleArray.slice]
 */
operator fun DoubleArray.get(indies: Iterable<Int>): List<Double> =
    this.slice(indies)

/**
 * Calls [BooleanArray.slice]
 */
operator fun BooleanArray.get(indies: IntRange): List<Boolean> =
    this.slice(indies)

/**
 * Calls [BooleanArray.slice]
 */
operator fun BooleanArray.get(indies: Iterable<Int>): List<Boolean> =
    this.slice(indies)

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
 * @param pattern The string which contains slices whose format is similar as python one.
 * @return The slices of list if the slice pattern is valid; otherwise an empty list
 */
operator fun <T> List<T>.get(pattern: String): List<T> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }

    val p = pattern.replace(" ", "") // Remove all whitespace
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }

    // Define a function make the negative index the expected one
    fun String.toIndex() = this.toInt().let { if (it >= 0) it else this@get.size + it }

    if (':' !in p) return listOf(this[p.toIndex()]) // pattern is a normal int
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
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex()
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex()

    end += if (step >= 0) -1 else 1 // Make the end an inclusive index

    if (step == 1) return this[start..end] // Directly returns an IntRange

    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar as python one.
 * @return The slices of array if the slice pattern is valid; otherwise an empty list
 * @see List.get get(pattern: String) which is preceding this function
 */
operator fun <T> Array<out T>.get(pattern: String): List<T> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }

    val p = pattern.replace(" ", "") // Remove all whitespace
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }

    // Define a function make the negative index the expected one
    fun String.toIndex() = this.toInt().let { if (it >= 0) it else this@get.size + it }

    if (':' !in p) return listOf(this[p.toIndex()]) // pattern is a normal int
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
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex()
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex()

    end += if (step >= 0) -1 else 1 // Make the end inclusive

    if (step == 1) return this[start..end]

    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar as python one.
 * @return The slices of array if the slice pattern is valid; otherwise an empty list
 * @see Array.get get(pattern: String)
 */
operator fun ByteArray.get(pattern: String): List<Byte> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    fun String.toIndex() = this.toInt().let { if (it >= 0) it else this@get.size + it }
    if (':' !in p) return listOf(this[p.toIndex()])
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
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex()
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex()
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar as python one.
 * @return The slices of array if the slice pattern is valid; otherwise an empty list
 * @see Array.get get(pattern: String)
 */
operator fun CharArray.get(pattern: String): List<Char> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    fun String.toIndex() = this.toInt().let { if (it >= 0) it else this@get.size + it }
    if (':' !in p) return listOf(this[p.toIndex()])
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
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex()
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex()
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}



/**
 * @param pattern The string which contains slices whose format is similar as python one.
 * @return The slices of array if the slice pattern is valid; otherwise an empty list
 * @see Array.get get(pattern: String)
 */
operator fun ShortArray.get(pattern: String): List<Short> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    fun String.toIndex() = this.toInt().let { if (it >= 0) it else this@get.size + it }
    if (':' !in p) return listOf(this[p.toIndex()])
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
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex()
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex()
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar as python one.
 * @return The slices of array if the slice pattern is valid; otherwise an empty list
 * @see Array.get get(pattern: String)
 */
operator fun IntArray.get(pattern: String): List<Int> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    fun String.toIndex() = this.toInt().let { if (it >= 0) it else this@get.size + it }
    if (':' !in p) return listOf(this[p.toIndex()])
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
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex()
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex()
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar as python one.
 * @return The slices of array if the slice pattern is valid; otherwise an empty list
 * @see Array.get get(pattern: String)
 */
operator fun LongArray.get(pattern: String): List<Long> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    fun String.toIndex() = this.toInt().let { if (it >= 0) it else this@get.size + it }
    if (':' !in p) return listOf(this[p.toIndex()])
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
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex()
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex()
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar as python one.
 * @return The slices of array if the slice pattern is valid; otherwise an empty list
 * @see Array.get get(pattern: String)
 */
operator fun FloatArray.get(pattern: String): List<Float> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    fun String.toIndex() = this.toInt().let { if (it >= 0) it else this@get.size + it }
    if (':' !in p) return listOf(this[p.toIndex()])
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
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex()
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex()
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar as python one.
 * @return The slices of array if the slice pattern is valid; otherwise an empty list
 * @see Array.get get(pattern: String)
 */
operator fun DoubleArray.get(pattern: String): List<Double> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    fun String.toIndex() = this.toInt().let { if (it >= 0) it else this@get.size + it }
    if (':' !in p) return listOf(this[p.toIndex()])
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
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex()
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex()
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}

/**
 * @param pattern The string which contains slices whose format is similar as python one.
 * @return The slices of array if the slice pattern is valid; otherwise an empty list
 * @see Array.get get(pattern: String)
 */
operator fun BooleanArray.get(pattern: String): List<Boolean> {
    require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
    val p = pattern.replace(" ", "")
    val pSplit = p.split(":")
    require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
    fun String.toIndex() = this.toInt().let { if (it >= 0) it else this@get.size + it }
    if (':' !in p) return listOf(this[p.toIndex()])
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
    if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex()
    if (pSplit[1].isNotEmpty()) end = pSplit[1].toIndex()
    end += if (step >= 0) -1 else 1
    if (step == 1) return this[start..end]
    return this[IntProgression.fromClosedRange(start, end, step)]
}