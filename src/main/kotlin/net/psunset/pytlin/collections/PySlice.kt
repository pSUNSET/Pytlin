package net.psunset.pytlin.collections

/**
 * Parses a [String] as a python slice.
 * To use this class, using [Py.slice] functions.
 */
class PySlice internal constructor(pattern: String) {

    var start: Int = 0
        private set
    var endInclusive: Int = 0 // exclusive before init
        private set
    var step: Int = 1
        private set
    var len: Int = -1
        get() {
            require(field < 0) {
                "len seems have not be init yet, please use 'postInit' function before get this field."
            }
            return field
        }
        private set

    val isNumber: Boolean get() = step == 0
    val isClone: Boolean get() = start == 0 && endInclusive == len - 1 && step == 1
    val isReverse: Boolean get() = start == 0 && endInclusive == len - 1 && step == -1
    val isRange: Boolean get() = step == 1

    init {
        require(pattern.any { it in ":- " || it.isDigit() }) { "Pattern is invalid! It must only contains digit, minus sign, space and colon." }
        val p = pattern.replace(" ", "")
        val pSplit = p.split(":")
        require(pSplit.size < 4) { "Pattern is invalid! It only contains a maximum of two colons." }
        if (':' !in p) {
            step = 0
            start = p.toInt()
            endInclusive = start
        } else {
            // Edit the slice properties if user provides
            if (pSplit.size == 3 && pSplit[2].isNotEmpty()) {
                step = pSplit[2].toInt()
                require(step != 0) { "Slice step cannot be zero." }
                if (step < 0) {
                    // The Default value is different when the step is negative.
                    start = -1
                    endInclusive = -1 // exclusive before init
                }
            }
            if (pSplit[0].isNotEmpty()) start = pSplit[0].toInt()
            if (pSplit[1].isNotEmpty()) endInclusive = pSplit[1].toInt()
            endInclusive += if (step >= 0) -1 else 1 // Make the end an inclusive index
        }
    }

    private fun applyLen(len: Int) {
        require(len >= 0) {
            "len cannot be a negative number!"
        }
        this.len = len
        if (this.start < 0) this.start = len - this.start  // Correct index
        if (this.endInclusive < 0) this.endInclusive = len - this.endInclusive  // Correct index
    }

    /** Checking out if [isNumber] is true before calling this function */
    fun asNumber(): Int = this.start

    /** Checking out if [isRange] is true before calling this function */
    fun asRange(): IntRange = IntRange(this.start, this.endInclusive)

    fun asProgression(): IntProgression = IntProgression.fromClosedRange(this.start, this.endInclusive, this.step)

    /**
     * @return A [Triple] which contains (start, end, step)
     */
    fun indices(len: Int): Triple<Int, Int, Int> {
        this.applyLen(len)

        return Triple(this.start, this.endInclusive, this.step)
    }
}