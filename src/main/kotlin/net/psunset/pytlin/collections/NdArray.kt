package net.psunset.pytlin.collections


abstract class NdArray<E>(
    data: Collection<E>,
    val size: Int,
    val shape: IntArray,
) {
    val ndim = shape.size
    val data: ArrayList<E>

    init {
        require(size >= 0) { "The size should not be negative." }
        require(shape.all { it >= 0 }) { "All numbers of shape should not be negative." }
        require(shape.prod() == size) { "The product of the shape should equals to the size." }
        this.data = ArrayList(data)
        require(data.size == size) { "The data is invalid because of its wrong size." }
    }

    override fun toString(): String = this.data.toString()
}


/**
 * Only access 1D [Double] data.
 */
class DoubleNdarray1D(
    data: Collection<Double>
) : NdArray<Double>(data, data.size, intArrayOf(data.size)) {

    operator fun get(index: Int): Double {
        return this.data[index]
    }

    operator fun set(index: Int, value: Double) {
        this.data[index] = value
    }

    operator fun plus(scalar: Double): DoubleNdarray1D = this.apply { plusAssign(scalar) }

    operator fun minus(scalar: Double): DoubleNdarray1D = this.apply { minusAssign(scalar) }

    operator fun times(scalar: Double): DoubleNdarray1D = this.apply { timesAssign(scalar) }

    operator fun div(scalar: Double): DoubleNdarray1D = this.apply{ divAssign(scalar) }

    operator fun rem(scalar: Double): DoubleNdarray1D = this.apply { remAssign(scalar) }

    operator fun plusAssign(scalar: Double) {
        for (i in 0..<this.size) {
            this[i] = this[i] + scalar
        }
    }

    operator fun minusAssign(scalar: Double) {
        for (i in 0..<this.size) {
            this[i] = this[i] - scalar
        }
    }

    operator fun timesAssign(scalar: Double) {
        for (i in 0..<this.size) {
            this[i] = this[i] * scalar
        }
    }

    operator fun divAssign(scalar: Double) {
        for (i in 0..<this.size) {
            this[i] = this[i] / scalar
        }
    }

    operator fun remAssign(scalar: Double) {
        for (i in 0..<this.size) {
            this[i] = this[i] % scalar
        }
    }

    operator fun plus(scalars: Array<out Double>): DoubleNdarray1D = this.apply { plusAssign(scalars) }

    operator fun plus(scalars: Iterable<Double>): DoubleNdarray1D = this.apply { plusAssign(scalars) }

    operator fun plus(scalars: Sequence<Double>): DoubleNdarray1D = this.apply { plusAssign(scalars) }

    operator fun minus(scalars: Array<out Double>): DoubleNdarray1D = this.apply { minusAssign(scalars) }

    operator fun minus(scalars: Iterable<Double>): DoubleNdarray1D = this.apply { minusAssign(scalars) }

    operator fun minus(scalars: Sequence<Double>): DoubleNdarray1D = this.apply { minusAssign(scalars) }

    operator fun times(scalars: Array<out Double>): DoubleNdarray1D = this.apply { timesAssign(scalars) }

    operator fun times(scalars: Iterable<Double>): DoubleNdarray1D = this.apply { timesAssign(scalars) }

    operator fun times(scalars: Sequence<Double>): DoubleNdarray1D = this.apply { timesAssign(scalars) }

    operator fun div(scalars: Array<out Double>): DoubleNdarray1D = this.apply{ divAssign(scalars) }

    operator fun div(scalars: Iterable<Double>): DoubleNdarray1D = this.apply{ divAssign(scalars) }

    operator fun div(scalars: Sequence<Double>): DoubleNdarray1D = this.apply{ divAssign(scalars) }

    operator fun rem(scalars: Array<out Double>): DoubleNdarray1D = this.apply { remAssign(scalars) }

    operator fun rem(scalars: Iterable<Double>): DoubleNdarray1D = this.apply { remAssign(scalars) }

    operator fun rem(scalars: Sequence<Double>): DoubleNdarray1D = this.apply { remAssign(scalars) }

    operator fun plusAssign(vector: Array<out Double>) {
        for (i in 0..<this.size) {
            this[i] = this[i] + vector[i]
        }
    }

    operator fun plusAssign(scalars: Iterable<Double>) {
        val a = scalars.toList()
        for (i in 0..<this.size) {
            this[i] = this[i] + a[i]
        }
    }

    operator fun plusAssign(scalars: Sequence<Double>) {
        val a = scalars.toList()
        for (i in 0..<this.size) {
            this[i] = this[i] + a[i]
        }
    }

    operator fun minusAssign(scalars: Array<out Double>) {
        for (i in 0..<this.size) {
            this[i] = this[i] - scalars[i]
        }
    }

    operator fun minusAssign(scalars: Iterable<Double>) {
        val a = scalars.toList()
        for (i in 0..<this.size) {
            this[i] = this[i] - a[i]
        }
    }

    operator fun minusAssign(scalars: Sequence<Double>) {
        val a = scalars.toList()
        for (i in 0..<this.size) {
            this[i] = this[i] - a[i]
        }
    }

    operator fun timesAssign(scalars: Array<out Double>) {
        for (i in 0..<this.size) {
            this[i] = this[i] * scalars[i]
        }
    }

    operator fun timesAssign(scalars: Iterable<Double>) {
        val a = scalars.toList()
        for (i in 0..<this.size) {
            this[i] = this[i] * a[i]
        }
    }

    operator fun timesAssign(scalars: Sequence<Double>) {
        val a = scalars.toList()
        for (i in 0..<this.size) {
            this[i] = this[i] * a[i]
        }
    }

    operator fun divAssign(scalars: Array<out Double>) {
        val a = scalars.toList()
        for (i in 0..<this.size) {
            this[i] = this[i] / a[i]
        }
    }

    operator fun divAssign(scalars: Iterable<Double>) {
        val a = scalars.toList()
        for (i in 0..<this.size) {
            this[i] = this[i] / a[i]
        }
    }

    operator fun divAssign(scalars: Sequence<Double>) {
        val a = scalars.toList()
        for (i in 0..<this.size) {
            this[i] = this[i] / a[i]
        }
    }

    operator fun remAssign(scalars: Array<out Double>) {
        for (i in 0..<this.size) {
            this[i] = this[i] % scalars[i]
        }
    }

    operator fun remAssign(scalars: Iterable<Double>) {
        val a = scalars.toList()
        for (i in 0..<this.size) {
            this[i] = this[i] % a[i]
        }
    }

    operator fun remAssign(scalars: Sequence<Double>) {
        val a = scalars.toList()
        for (i in 0..<this.size) {
            this[i] = this[i] % a[i]
        }
    }
}

fun doubleNdarray1DOf(data: Collection<Double>): DoubleNdarray1D = DoubleNdarray1D(data)
