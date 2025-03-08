package net.psunset.pytlin.ranges

class PySlices private constructor(pattern: String, val dataSize: Int) {

    var start: Int = 0
    var endInclusive: Int = dataSize
    var step: Int = 1

    val isNumber: Boolean get() = step == 0
    val isClone: Boolean get() = start == 0 && endInclusive == dataSize && step == 1
    val isReverse: Boolean get() = start == 0 && endInclusive == dataSize && step == -1
    val isRange: Boolean get() = step == 1

    init {
        require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
        val p = pattern.replace(" ", "")
        val pSplit = p.split(":")
        require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
        if (':' !in p) {
            step = 0
            start = p.toIndex(dataSize)
            endInclusive = start
        } else {
            // Edit the slice properties if user provides
            if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
                step = pSplit[2].toInt()
                require(step != 0) { "Slice step cannot be zero." }
                if (step < 0) {
                    // The Default value is different when the step is negative.
                    start = dataSize - 1
                    endInclusive = -1
                }
            }
            if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(dataSize)
            if (pSplit[1].isNotEmpty()) endInclusive = pSplit[1].toIndex(dataSize)
            endInclusive += if (step >= 0) -1 else 1 // Make the end an inclusive index
        }
    }

    fun asNumber(): Int = this.start
    fun asRange(): IntRange = IntRange(this.start, this.endInclusive)
    fun asProgression(): IntProgression = IntProgression.fromClosedRange(this.start, this.endInclusive, this.step)

    companion object {
        fun <E> parse(pattern: String, c: Collection<E>): PySlices = PySlices(pattern, c.size)
        fun <E> parse(pattern: String, i: Iterable<E>): PySlices = PySlices(pattern, i.count())
        fun <E> parse(pattern: String, i: Sequence<E>): PySlices = PySlices(pattern, i.count())
        fun <E> parse(pattern: String, a: Array<out E>): PySlices = PySlices(pattern, a.size)
        fun parse(pattern: String, a: ByteArray): PySlices = PySlices(pattern, a.size)
        fun parse(pattern: String, a: CharArray): PySlices = PySlices(pattern, a.size)
        fun parse(pattern: String, a: ShortArray): PySlices = PySlices(pattern, a.size)
        fun parse(pattern: String, a: IntArray): PySlices = PySlices(pattern, a.size)
        fun parse(pattern: String, a: LongArray): PySlices = PySlices(pattern, a.size)
        fun parse(pattern: String, a: FloatArray): PySlices = PySlices(pattern, a.size)
        fun parse(pattern: String, a: DoubleArray): PySlices = PySlices(pattern, a.size)
        fun parse(pattern: String, a: BooleanArray): PySlices = PySlices(pattern, a.size)

        @OptIn(ExperimentalUnsignedTypes::class)
        fun parse(pattern: String, a: UByteArray): PySlices = PySlices(pattern, a.size)

        @OptIn(ExperimentalUnsignedTypes::class)
        fun parse(pattern: String, a: UShortArray): PySlices = PySlices(pattern, a.size)

        @OptIn(ExperimentalUnsignedTypes::class)
        fun parse(pattern: String, a: UIntArray): PySlices = PySlices(pattern, a.size)

        @OptIn(ExperimentalUnsignedTypes::class)
        fun parse(pattern: String, a: ULongArray): PySlices = PySlices(pattern, a.size)
    }
}

fun String.toIndex(dataSize: Int) = this.toInt().let { if (it >= 0) it else dataSize + it }