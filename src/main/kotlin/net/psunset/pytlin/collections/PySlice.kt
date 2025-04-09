package net.psunset.pytlin.collections

/**
 * Parses a [String] as a python slice.
 * To use this class, using [Py.slice] functions.
 */
class PySlice internal constructor(pattern: String, val len: Int) {

    var start: Int = 0
    var endInclusive: Int = len - 1
    var step: Int = 1

    val isNumber: Boolean get() = step == 0
    val isClone: Boolean get() = start == 0 && endInclusive == len - 1 && step == 1
    val isReverse: Boolean get() = start == 0 && endInclusive == len - 1 && step == -1
    val isRange: Boolean get() = step == 1

    init {
        require(pattern.any { it == ':' || it == ' ' || it == '-' || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
        val p = pattern.replace(" ", "")
        val pSplit = p.split(":")
        require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
        if (':' !in p) {
            step = 0
            start = p.toIndex(len)
            endInclusive = start
        } else {
            // Edit the slice properties if user provides
            if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
                step = pSplit[2].toInt()
                require(step != 0) { "Slice step cannot be zero." }
                if (step < 0) {
                    // The Default value is different when the step is negative.
                    start = len - 1
                    endInclusive = -1
                }
            }
            if (pSplit[0].isNotEmpty()) start = pSplit[0].toIndex(len)
            if (pSplit[1].isNotEmpty()) endInclusive = pSplit[1].toIndex(len)
            endInclusive += if (step >= 0) -1 else 1 // Make the end an inclusive index
        }
    }

    /** Checking out if [isNumber] is true before calling this function */
    fun asNumber(): Int = this.start

    /** Checking out if [isRange] is true before calling this function */
    fun asRange(): IntRange = IntRange(this.start, this.endInclusive)

    fun asProgression(): IntProgression = IntProgression.fromClosedRange(this.start, this.endInclusive, this.step)

    val indices: Iterable<Int> =
        if (isNumber) listOf(asNumber())
        else if (isRange) this.asRange()
        else this.asProgression()

    operator fun iterator(): Iterator<Int> = indices.iterator()

}