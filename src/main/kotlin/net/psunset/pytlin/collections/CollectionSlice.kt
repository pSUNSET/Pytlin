package net.psunset.pytlin.collections

import net.psunset.pytlin.Py

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

/**
 * Parses the pattern and calls another overload get function.
 *
 * It's too convenient to use slice in a python list.
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
    val slice = Py.slice(pattern)
    if (slice.isNumber) return listOf(this[slice.asNumber(this.size)])
    if (slice.isClone) return this.toList()
    if (slice.isReverse) return this.reversed()
    if (slice.isRange) return this[slice.asRange(this.size)]
    return this[Py.slice(pattern).asProgression(this.size)]
}

/**
 * Parses the pattern and calls another overload get function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see List.get get(pattern: String) which is preceding this function
 */
operator fun <T> Array<out T>.get(pattern: String): List<T> {
    val slice = Py.slice(pattern)
    if (slice.isNumber) return listOf(this[slice.asNumber(this.size)])
    if (slice.isClone) return this.toList()
    if (slice.isReverse) return this.reversed()
    if (slice.isRange) return this[slice.asRange(this.size)]
    return this[Py.slice(pattern).asProgression(this.size)]
}

/**
 * Parses the pattern and calls another overload get function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see List.get get(pattern: String)
 */
operator fun ByteArray.get(pattern: String): List<Byte> {
    val slice = Py.slice(pattern)
    if (slice.isNumber) return listOf(this[slice.asNumber(this.size)])
    if (slice.isClone) return this.toList()
    if (slice.isReverse) return this.reversed()
    if (slice.isRange) return this[slice.asRange(this.size)]
    return this[Py.slice(pattern).asProgression(this.size)]
}

/**
 * Parses the pattern and calls another overload get function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see List.get get(pattern: String)
 */
operator fun CharArray.get(pattern: String): List<Char> {
    val slice = Py.slice(pattern)
    if (slice.isNumber) return listOf(this[slice.asNumber(this.size)])
    if (slice.isClone) return this.toList()
    if (slice.isReverse) return this.reversed()
    if (slice.isRange) return this[slice.asRange(this.size)]
    return this[Py.slice(pattern).asProgression(this.size)]
}


/**
 * Parses the pattern and calls another overload get function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see List.get get(pattern: String)
 */
operator fun ShortArray.get(pattern: String): List<Short> {
    val slice = Py.slice(pattern)
    if (slice.isNumber) return listOf(this[slice.asNumber(this.size)])
    if (slice.isClone) return this.toList()
    if (slice.isReverse) return this.reversed()
    if (slice.isRange) return this[slice.asRange(this.size)]
    return this[Py.slice(pattern).asProgression(this.size)]
}

/**
 * Parses the pattern and calls another overload get function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see List.get get(pattern: String)
 */
operator fun IntArray.get(pattern: String): List<Int> {
    val slice = Py.slice(pattern)
    if (slice.isNumber) return listOf(this[slice.asNumber(this.size)])
    if (slice.isClone) return this.toList()
    if (slice.isReverse) return this.reversed()
    if (slice.isRange) return this[slice.asRange(this.size)]
    return this[Py.slice(pattern).asProgression(this.size)]
}

/**
 * Parses the pattern and calls another overload get function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see List.get get(pattern: String)
 */
operator fun LongArray.get(pattern: String): List<Long> {
    val slice = Py.slice(pattern)
    if (slice.isNumber) return listOf(this[slice.asNumber(this.size)])
    if (slice.isClone) return this.toList()
    if (slice.isReverse) return this.reversed()
    if (slice.isRange) return this[slice.asRange(this.size)]
    return this[Py.slice(pattern).asProgression(this.size)]
}

/**
 * Parses the pattern and calls another overload get function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see List.get get(pattern: String)
 */
operator fun FloatArray.get(pattern: String): List<Float> {
    val slice = Py.slice(pattern)
    if (slice.isNumber) return listOf(this[slice.asNumber(this.size)])
    if (slice.isClone) return this.toList()
    if (slice.isReverse) return this.reversed()
    if (slice.isRange) return this[slice.asRange(this.size)]
    return this[Py.slice(pattern).asProgression(this.size)]
}

/**
 * Parses the pattern and calls another overload get function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see List.get get(pattern: String)
 */
operator fun DoubleArray.get(pattern: String): List<Double> {
    val slice = Py.slice(pattern)
    if (slice.isNumber) return listOf(this[slice.asNumber(this.size)])
    if (slice.isClone) return this.toList()
    if (slice.isReverse) return this.reversed()
    if (slice.isRange) return this[slice.asRange(this.size)]
    return this[Py.slice(pattern).asProgression(this.size)]
}

/**
 * Parses the pattern and calls another overload get function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see List.get get(pattern: String)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.get(pattern: String): List<UByte> {
    val slice = Py.slice(pattern)
    if (slice.isNumber) return listOf(this[slice.asNumber(this.size)])
    if (slice.isClone) return this.toList()
    if (slice.isReverse) return this.reversed()
    if (slice.isRange) return this[slice.asRange(this.size)]
    return this[Py.slice(pattern).asProgression(this.size)]
}

/**
 * Parses the pattern and calls another overload get function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see List.get get(pattern: String)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.get(pattern: String): List<UShort> {
    val slice = Py.slice(pattern)
    if (slice.isNumber) return listOf(this[slice.asNumber(this.size)])
    if (slice.isClone) return this.toList()
    if (slice.isReverse) return this.reversed()
    if (slice.isRange) return this[slice.asRange(this.size)]
    return this[Py.slice(pattern).asProgression(this.size)]
}

/**
 * Parses the pattern and calls another overload get function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see List.get get(pattern: String)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.get(pattern: String): List<UInt> {
    val slice = Py.slice(pattern)
    if (slice.isNumber) return listOf(this[slice.asNumber(this.size)])
    if (slice.isClone) return this.toList()
    if (slice.isReverse) return this.reversed()
    if (slice.isRange) return this[slice.asRange(this.size)]
    return this[Py.slice(pattern).asProgression(this.size)]
}

/**
 * Parses the pattern and calls another overload get function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see List.get get(pattern: String)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.get(pattern: String): List<ULong> {
    val slice = Py.slice(pattern)
    if (slice.isNumber) return listOf(this[slice.asNumber(this.size)])
    if (slice.isClone) return this.toList()
    if (slice.isReverse) return this.reversed()
    if (slice.isRange) return this[slice.asRange(this.size)]
    return this[Py.slice(pattern).asProgression(this.size)]
}

/**
 * Parses the pattern and calls another overload get function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @return The slices of array if the slice pattern is valid, an empty list otherwise.
 * @see List.get get(pattern: String)
 */
operator fun BooleanArray.get(pattern: String): List<Boolean> {
    val slice = Py.slice(pattern)
    if (slice.isNumber) return listOf(this[slice.asNumber(this.size)])
    if (slice.isClone) return this.toList()
    if (slice.isReverse) return this.reversed()
    if (slice.isRange) return this[slice.asRange(this.size)]
    return this[Py.slice(pattern).asProgression(this.size)]
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 * Also, because the size of this list is mutable,
 * the sizes of indices to be removed and new elements can be different when the indices step equals to 1.
 */
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

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 * Also, because the size of this list is mutable,
 * the sizes of indices to be removed and new elements can be different.
 */
operator fun <T> MutableList<T>.set(indices: IntRange, elements: Array<out T>) {
    if (indices.count() == elements.size) {
        indices.forEachIndexed { index, i -> this[i] = elements[index] }
    } else {
        val f = indices.first
        indices.forEach { _ -> this.removeAt(f) }
        this.addAll(f, elements.asList())
    }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 * Also, because the size of this list is mutable,
 * the sizes of indices to be removed and new elements can be different when the indices step equals to 1.
 */
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

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 * Also, because the size of this list is mutable,
 * the sizes of indices to be removed and new elements can be different.
 */
operator fun <T> MutableList<T>.set(indices: IntRange, elements: List<T>) {
    if (indices.count() == elements.size) {
        indices.forEachIndexed { index, i -> this[i] = elements[index] }
    } else {
        val f = indices.first
        indices.forEach { _ -> this.removeAt(f) }
        this.addAll(f, elements)
    }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 * If the indices parameter is an [IntProgression],
 * the sizes of indices to be removed and new elements can be different.
 */
operator fun <T> MutableList<T>.set(indices: Iterable<Int>, elements: List<T>) {
    if (indices is IntProgression) {
        this[indices] = elements
    } else {
        require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
        indices.forEachIndexed { index, i -> this[i] = elements[index] }
    }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 * If the indices parameter is an [IntProgression],
 * the sizes of indices to be removed and new elements can be different.
 */
operator fun <T> MutableList<T>.set(indices: Iterable<Int>, elements: Array<out T>) {
    if (indices is IntProgression) {
        this[indices] = elements
    } else {
        require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
        indices.forEachIndexed { index, i -> this[i] = elements[index] }
    }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun <T> Array<T>.set(indices: Iterable<Int>, elements: List<T>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun <T> Array<T>.set(indices: Iterable<Int>, elements: Array<out T>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun ByteArray.set(indices: Iterable<Int>, elements: List<Byte>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun ByteArray.set(indices: Iterable<Int>, elements: Array<out Byte>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun ByteArray.set(indices: Iterable<Int>, elements: ByteArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun CharArray.set(indices: Iterable<Int>, elements: List<Char>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun CharArray.set(indices: Iterable<Int>, elements: Array<out Char>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun CharArray.set(indices: Iterable<Int>, elements: CharArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun ShortArray.set(indices: Iterable<Int>, elements: List<Short>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun ShortArray.set(indices: Iterable<Int>, elements: Array<out Short>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun ShortArray.set(indices: Iterable<Int>, elements: ShortArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun IntArray.set(indices: Iterable<Int>, elements: List<Int>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun IntArray.set(indices: Iterable<Int>, elements: Array<out Int>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun IntArray.set(indices: Iterable<Int>, elements: IntArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun LongArray.set(indices: Iterable<Int>, elements: List<Long>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun LongArray.set(indices: Iterable<Int>, elements: Array<out Long>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun LongArray.set(indices: Iterable<Int>, elements: LongArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun FloatArray.set(indices: Iterable<Int>, elements: List<Float>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun FloatArray.set(indices: Iterable<Int>, elements: Array<out Float>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun FloatArray.set(indices: Iterable<Int>, elements: FloatArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun DoubleArray.set(indices: Iterable<Int>, elements: List<Double>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun DoubleArray.set(indices: Iterable<Int>, elements: Array<out Double>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun DoubleArray.set(indices: Iterable<Int>, elements: DoubleArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun BooleanArray.set(indices: Iterable<Int>, elements: List<Boolean>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun BooleanArray.set(indices: Iterable<Int>, elements: Array<out Boolean>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
operator fun BooleanArray.set(indices: Iterable<Int>, elements: BooleanArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.set(indices: Iterable<Int>, elements: List<UByte>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.set(indices: Iterable<Int>, elements: Array<out UByte>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.set(indices: Iterable<Int>, elements: UByteArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.set(indices: Iterable<Int>, elements: List<UShort>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.set(indices: Iterable<Int>, elements: Array<out UShort>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.set(indices: Iterable<Int>, elements: UShortArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.set(indices: Iterable<Int>, elements: List<UInt>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.set(indices: Iterable<Int>, elements: Array<out UInt>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.set(indices: Iterable<Int>, elements: UIntArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.set(indices: Iterable<Int>, elements: List<ULong>) {
    require(indices.count() == elements.size) { "Attempt to assign list of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.set(indices: Iterable<Int>, elements: Array<out ULong>) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Replaces the elements at the specified positions in this list with the specified elements.
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.set(indices: Iterable<Int>, elements: ULongArray) {
    require(indices.count() == elements.size) { "Attempt to assign array of size ${elements.size} to extended slice of size ${indices.count()}" }
    indices.forEachIndexed { index, i -> this[i] = elements[index] }
}

/**
 * Parses the pattern and calls another overload set function.
 *
 * In addition to getting the elements with a slice pattern,
 * setting the elements with that is also available.
 *
 * For example:
 * ```
 * val a = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
 * a["1:3"] = listOf(12, 13)
 * println(a) // Result: [1, 12, 13, 4, 5, 6, 7, 8, 9, 10]
 *
 * val b = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
 * b["2:-1:2"] = listOf(13, 15, 17, 19)
 * println(b) // Result: [1, 2, 13, 4, 15, 6, 17, 8, 19, 10]
 * ```
 *
 * @param pattern The string which contains slices whose format is similar to python one.
 */
operator fun <T> MutableList<T>.set(pattern: String, elements: List<T>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}

/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun <T> MutableList<T>.set(pattern: String, elements: Array<out T>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}

/**
 * Parses the pattern and calls another overload set function.
 *
 * The best feature of this function is the negative numbers are available.
 * Using negative number as an index which counting from the end of the list.
 * If the number is positive, simply using [MutableList.set]`(index: Int, element: E)` function instead.
 *
 * For example:
 * ```
 * val a = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
 * a["-3"] = -1
 * println(a) // Result: [1, 2, 3, 4, 5, 6, 7, -1, 9, 10]
 * ```
 *
 * @param pattern The string which contains slices whose format is similar to python one.
 */
operator fun <T> MutableList<T>.set(pattern: String, element: T) {
    this[Py.slice(pattern).asNumber(this.size)] = element
}

/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun <T> Array<T>.set(pattern: String, elements: List<T>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun <T> Array<T>.set(pattern: String, elements: Array<out T>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}

/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, element: T)
 */
operator fun <T> Array<T>.set(pattern: String, element: T) {
    this[Py.slice(pattern).asNumber(this.size)] = element
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun ByteArray.set(pattern: String, elements: List<Byte>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun ByteArray.set(pattern: String, elements: Array<out Byte>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun ByteArray.set(pattern: String, elements: ByteArray) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}

/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, element: T)
 */
operator fun ByteArray.set(pattern: String, element: Byte) {
    this[Py.slice(pattern).asNumber(this.size)] = element
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun CharArray.set(pattern: String, elements: List<Char>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun CharArray.set(pattern: String, elements: Array<out Char>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun CharArray.set(pattern: String, elements: CharArray) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}

/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, element: T)
 */
operator fun CharArray.set(pattern: String, element: Char) {
    this[Py.slice(pattern).asNumber(this.size)] = element
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun ShortArray.set(pattern: String, elements: List<Short>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun ShortArray.set(pattern: String, elements: Array<out Short>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun ShortArray.set(pattern: String, elements: ShortArray) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}

/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, element: T)
 */
operator fun ShortArray.set(pattern: String, element: Short) {
    this[Py.slice(pattern).asNumber(this.size)] = element
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun IntArray.set(pattern: String, elements: List<Int>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun IntArray.set(pattern: String, elements: Array<out Int>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun IntArray.set(pattern: String, elements: IntArray) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}

/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, element: T)
 */
operator fun IntArray.set(pattern: String, element: Int) {
    this[Py.slice(pattern).asNumber(this.size)] = element
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun LongArray.set(pattern: String, elements: List<Long>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun LongArray.set(pattern: String, elements: Array<out Long>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun LongArray.set(pattern: String, elements: LongArray) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}

/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, element: T)
 */
operator fun LongArray.set(pattern: String, element: Long) {
    this[Py.slice(pattern).asNumber(this.size)] = element
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun FloatArray.set(pattern: String, elements: List<Float>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun FloatArray.set(pattern: String, elements: Array<out Float>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun FloatArray.set(pattern: String, elements: FloatArray) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}

/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, element: T)
 */
operator fun FloatArray.set(pattern: String, element: Float) {
    this[Py.slice(pattern).asNumber(this.size)] = element
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun DoubleArray.set(pattern: String, elements: List<Double>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun DoubleArray.set(pattern: String, elements: Array<out Double>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun DoubleArray.set(pattern: String, elements: DoubleArray) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}

/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, element: T)
 */
operator fun DoubleArray.set(pattern: String, element: Double) {
    this[Py.slice(pattern).asNumber(this.size)] = element
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun BooleanArray.set(pattern: String, elements: List<Boolean>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun BooleanArray.set(pattern: String, elements: Array<out Boolean>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
operator fun BooleanArray.set(pattern: String, elements: BooleanArray) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}

/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, element: T)
 */
operator fun BooleanArray.set(pattern: String, element: Boolean) {
    this[Py.slice(pattern).asNumber(this.size)] = element
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.set(pattern: String, elements: List<UByte>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.set(pattern: String, elements: Array<out UByte>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.set(pattern: String, elements: UByteArray) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, element: T)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UByteArray.set(pattern: String, element: UByte) {
    this[Py.slice(pattern).asNumber(this.size)] = element
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.set(pattern: String, elements: List<UShort>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.set(pattern: String, elements: Array<out UShort>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.set(pattern: String, elements: UShortArray) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}

/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, element: T)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UShortArray.set(pattern: String, element: UShort) {
    this[Py.slice(pattern).asNumber(this.size)] = element
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.set(pattern: String, elements: List<UInt>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.set(pattern: String, elements: Array<out UInt>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.set(pattern: String, elements: UIntArray) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}

/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, element: T)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun UIntArray.set(pattern: String, element: UInt) {
    this[Py.slice(pattern).asNumber(this.size)] = element
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.set(pattern: String, elements: List<ULong>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.set(pattern: String, elements: Array<out ULong>) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}


/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, elements: List<T>)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.set(pattern: String, elements: ULongArray) {
    val slice = Py.slice(pattern)
    if (slice.isNumber) {
        throw IllegalArgumentException("Kotlin is unlike python! The type of a list can't be changed.")
    } else if (slice.isClone) {
        this.indices.forEach { this[it] = elements[it] }
    } else if (slice.isReverse) {
        this.indices.forEach { this[it] = elements.reversed()[it] }
    } else if (slice.isRange) {
        this[slice.asRange(this.size)] = elements
    } else {
        this[slice.asProgression(this.size)] = elements
    }
}

/**
 * Parses the pattern and calls another overload set function.
 * @param pattern The string which contains slices whose format is similar to python one.
 * @see MutableList.set set(pattern: String, element: T)
 */
@OptIn(ExperimentalUnsignedTypes::class)
operator fun ULongArray.set(pattern: String, element: ULong) {
    this[Py.slice(pattern).asNumber(this.size)] = element
}

