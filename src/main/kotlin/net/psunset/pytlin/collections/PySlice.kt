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

    val isNumber get() = this.step == 0
    val isRange get() = this.step == 1

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
                    endInclusive = -1 // exclusive
                }
            }
            if (pSplit[0].isNotEmpty()) start = pSplit[0].toInt()
            if (pSplit[1].isNotEmpty()) endInclusive = pSplit[1].toInt()
            endInclusive += if (step >= 0) -1 else 1 // Make the end an inclusive index
        }
    }

    fun asNumber(len: Int): Int {
        require(this.isNumber) { "step does not equals to one!" }
        require(len >= 0) { "len cannot be a negative number!" }

        val start = if (this.start < 0) len + this.start else this.start  // Correct index
        return start
    }

    fun asRange(len: Int): IntRange {
        require(this.isRange) { "step does not equals to one!" }
        require(len >= 0) { "len cannot be a negative number!" }

        val rangeStart = if (this.start < 0) len + this.start else this.start  // Correct index
        val rangeEndInclusive =
            if (this.endInclusive < 0) len + this.endInclusive else this.endInclusive // Correct index
        return IntRange(rangeStart, rangeEndInclusive)
    }

    /**
     * @return a [IntProgression] which this slice stands for
     */
    fun asProgression(len: Int): IntProgression {
        require(len >= 0) { "len cannot be a negative number!" }

        val rangeStart = if (this.start < 0) len + this.start else this.start  // Correct index
        val rangeEndInclusive =
            if (this.endInclusive < 0) len + this.endInclusive else this.endInclusive // Correct index
        return IntProgression.fromClosedRange(rangeStart, rangeEndInclusive, this.step)
    }

    /**
     * @return a [Triple] which contains (start, endExclusive, step)
     */
    fun indices(len: Int): Triple<Int, Int, Int> {
        require(len >= 0) {
            "len cannot be a negative number!"
        }

        val rangeStart = if (this.start < 0) len + this.start else this.start  // Correct index
        val rangeEndExclusive = // Correct index and make it exclusive
            (if (this.endInclusive < 0) len + this.endInclusive else this.endInclusive) + (if (step >= 0) 1 else -1)

        return Triple(rangeStart, rangeEndExclusive, this.step)
    }
}