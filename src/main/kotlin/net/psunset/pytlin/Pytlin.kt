package net.psunset.pytlin

import net.psunset.pytlin.collections.PySlice

typealias Py = Pytlin

object Pytlin {
    /** Parse the slice pattern with a collection */
    fun <E> slice(pattern: String, c: Collection<E>): PySlice = PySlice(pattern, c.size)

    /** Parse the slice pattern with a collection */
    fun <E> slice(pattern: String, i: Iterable<E>): PySlice = PySlice(pattern, i.count())

    /** Parse the slice pattern with a collection */
    fun <E> slice(pattern: String, i: Sequence<E>): PySlice = PySlice(pattern, i.count())

    /** Parse the slice pattern with an array */
    fun <E> slice(pattern: String, a: Array<out E>): PySlice = PySlice(pattern, a.size)

    /** Parse the slice pattern with an array */
    fun slice(pattern: String, a: ByteArray): PySlice = PySlice(pattern, a.size)

    /** Parse the slice pattern with an array */
    fun slice(pattern: String, a: CharArray): PySlice = PySlice(pattern, a.size)

    /** Parse the slice pattern with an array */
    fun slice(pattern: String, a: ShortArray): PySlice = PySlice(pattern, a.size)

    /** Parse the slice pattern with an array */
    fun slice(pattern: String, a: IntArray): PySlice = PySlice(pattern, a.size)

    /** Parse the slice pattern with an array */
    fun slice(pattern: String, a: LongArray): PySlice = PySlice(pattern, a.size)

    /** Parse the slice pattern with an array */
    fun slice(pattern: String, a: FloatArray): PySlice = PySlice(pattern, a.size)

    /** Parse the slice pattern with an array */
    fun slice(pattern: String, a: DoubleArray): PySlice = PySlice(pattern, a.size)

    /** Parse the slice pattern with an array */
    fun slice(pattern: String, a: BooleanArray): PySlice = PySlice(pattern, a.size)

    /** Parse the slice pattern with an array */
    @OptIn(ExperimentalUnsignedTypes::class)
    fun slice(pattern: String, a: UByteArray): PySlice = PySlice(pattern, a.size)

    /** Parse the slice pattern with an array */
    @OptIn(ExperimentalUnsignedTypes::class)
    fun slice(pattern: String, a: UShortArray): PySlice = PySlice(pattern, a.size)

    /** Parse the slice pattern with an array */
    @OptIn(ExperimentalUnsignedTypes::class)
    fun slice(pattern: String, a: UIntArray): PySlice = PySlice(pattern, a.size)

    /** Parse the slice pattern with an array */
    @OptIn(ExperimentalUnsignedTypes::class)
    fun slice(pattern: String, a: ULongArray): PySlice = PySlice(pattern, a.size)

    /** Parse the slice pattern with a string */
    fun slice(pattern: String, s: CharSequence): PySlice = PySlice(pattern, s.length)

    /**
     * The function is similar to the `range` function in python.
     */
    fun range(start: Int, endExclusive: Int, step: Int): IntProgression =
        IntProgression.fromClosedRange(start, endExclusive.let { it + if (step < 0) 1 else -1 }, step)

    /**
     * The function is similar to the `range` function in python.
     */
    fun range(start: Int, endExclusive: Int): IntRange = IntRange(start, endExclusive - 1)

    /**
     * The function is similar to the `range` function in python.
     */
    fun range(endExclusive: Int): IntRange = IntRange(0, endExclusive - 1)
}