package net.psunset.pytlin.collections

import java.math.BigDecimal
import java.math.BigInteger

abstract class Tensor1D<E : Number> private constructor(
    data: Array<E>, space: TensorSpace
) : Tensor<E>(data, space) {

    @Suppress("UNCHECKED_CAST")
    override val data = super.data as Array<E>

    constructor(data: Array<E>) : this(data, Tensors.space(data.size))

    operator fun get(index: Int): E = this.data[index]
    override fun get(index: TensorIndex): E {
        require(isValidIndex(index)) { "The index is invalid in this tensor." }
        return this[index[0]]
    }

    operator fun set(index: Int, value: E) = this.data.set(index, value)
    override fun set(index: TensorIndex, value: E) {
        require(isValidIndex(index)) { "The index is invalid in this tensor." }
        this[index[0]] = value
    }

    operator fun plus(scalar: E): Tensor1D<E> = this.apply { this.plusAssign(scalar) }
    operator fun minus(scalar: E): Tensor1D<E> = this.apply { this.minusAssign(scalar) }
    operator fun times(scalar: E): Tensor1D<E> = this.apply { this.timesAssign(scalar) }
    operator fun div(scalar: E): Tensor1D<E> = this.apply { this.divAssign(scalar) }
    operator fun rem(scalar: E): Tensor1D<E> = this.apply { this.remAssign(scalar) }

    operator fun plusAssign(scalar: E) {
        for (i in 0..<this.numel) {
            this[i] = doAdd(this[i], scalar)
        }
    }

    operator fun minusAssign(scalar: E) {
        for (i in 0..<this.numel) {
            this[i] = doSub(this[i], scalar)
        }
    }

    operator fun timesAssign(scalar: E) {
        for (i in 0..<this.numel) {
            this[i] = doMul(this[i], scalar)
        }
    }

    operator fun divAssign(scalar: E) {
        for (i in 0..<this.numel) {
            this[i] = doDiv(this[i], scalar)
        }
    }

    operator fun remAssign(scalar: E) {
        for (i in 0..<this.numel) {
            this[i] = doMod(this[i], scalar)
        }
    }

    operator fun plus(vector: Tensor1D<E>): Tensor1D<E> = this.apply { this.plusAssign(vector) }
    operator fun minus(vector: Tensor1D<E>): Tensor1D<E> = this.apply { this.minusAssign(vector) }
    operator fun times(vector: Tensor1D<E>): Tensor1D<E> = this.apply { this.timesAssign(vector) }
    operator fun div(vector: Tensor1D<E>): Tensor1D<E> = this.apply { this.divAssign(vector) }
    operator fun rem(vector: Tensor1D<E>): Tensor1D<E> = this.apply { this.remAssign(vector) }

    operator fun plusAssign(vector: Tensor1D<E>) {
        for (i in 0..<this.numel) {
            this[i] = doAdd(this[i], vector[i])
        }
    }

    operator fun minusAssign(vector: Tensor1D<E>) {
        for (i in 0..<this.numel) {
            this[i] = doSub(this[i], vector[i])
        }
    }

    operator fun timesAssign(vector: Tensor1D<E>) {
        for (i in 0..<this.numel) {
            this[i] = doMul(this[i], vector[i])
        }
    }

    operator fun divAssign(vector: Tensor1D<E>) {
        for (i in 0..<this.numel) {
            this[i] = doDiv(this[i], vector[i])
        }
    }

    operator fun remAssign(vector: Tensor1D<E>) {
        for (i in 0..<this.numel) {
            this[i] = doMod(this[i], vector[i])
        }
    }

    infix fun dot(vector: Tensor1D<E>): E =
        this.data.zip(vector.data).map { (a, b) -> doMul(a, b) }.reduce { a, b -> doAdd(a, b) }

    infix fun cross(vector: Tensor1D<E>): Tensor1D<E> {
        val dimension = this.numel
        when (dimension) {
            2 -> return another(listOf(doSub(doMul(data[0], vector.data[1]), doMul(data[1], vector.data[0]))))
            3 -> {
                val a1 = data[0]
                val a2 = data[1]
                val a3 = data[2]
                val b1 = vector.data[0]
                val b2 = vector.data[1]
                val b3 = vector.data[2]

                return another(
                    listOf(
                        doSub(doMul(a2, b3), doMul(a3, b2)),
                        doSub(doMul(a3, b1), doMul(a1, b3)),
                        doSub(doMul(a1, b2), doMul(a2, b1))
                    )
                )
            }

            else -> throw IllegalArgumentException("Vectors must have dimension 2 or 3 to calculate the cross product.")
        }
    }

    protected abstract fun doAdd(a: E, b: E): E
    protected abstract fun doSub(a: E, b: E): E
    protected abstract fun doMul(a: E, b: E): E
    protected abstract fun doDiv(a: E, b: E): E
    protected abstract fun doMod(a: E, b: E): E
    protected abstract fun another(l: List<E>): Tensor1D<E>

    override fun toString(): String = "tensor(${this.data.contentToString()})"
}

inline fun <reified E : Number> tensorOf1D(data: List<E>): Tensor1D<E> =
    Tensors.of1D(data)

inline fun <reified E : Number> tensorOf1D(data: Array<E>): Tensor1D<E> =
    Tensors.of1D(data)

/**
 * Only access 1D [Int] data.
 */
class IntTensor1D(
    data: Array<Int>
) : Tensor1D<Int>(data) {
    override fun doAdd(a: Int, b: Int): Int = a + b

    override fun doSub(a: Int, b: Int): Int = a - b

    override fun doMul(a: Int, b: Int): Int = a * b

    override fun doDiv(a: Int, b: Int): Int = a / b

    override fun doMod(a: Int, b: Int): Int = a % b

    override fun another(l: List<Int>): Tensor1D<Int> =
        IntTensor1D(l.toTypedArray())
}

fun intTensorOf1D(data: List<Int>): IntTensor1D = IntTensor1D(data.toTypedArray())

fun intTensorOf1D(data: Array<Int>): IntTensor1D = IntTensor1D(data)

fun intTensorOf1D(data: IntArray): IntTensor1D = IntTensor1D(data.toTypedArray())

/**
 * Only access 1D [Long] data.
 */
class LongTensor1D(
    data: Array<Long>
) : Tensor1D<Long>(data) {
    override fun doAdd(a: Long, b: Long): Long = a + b

    override fun doSub(a: Long, b: Long): Long = a - b

    override fun doMul(a: Long, b: Long): Long = a * b

    override fun doDiv(a: Long, b: Long): Long = a / b

    override fun doMod(a: Long, b: Long): Long = a % b

    override fun another(l: List<Long>): Tensor1D<Long> =
        LongTensor1D(l.toTypedArray())
}

fun longTensorOf1D(data: List<Long>): LongTensor1D = LongTensor1D(data.toTypedArray())

fun longTensorOf1D(data: Array<Long>): LongTensor1D = LongTensor1D(data)

fun longTensorOf1D(data: LongArray): LongTensor1D = LongTensor1D(data.toTypedArray())

/**
 * Only access 1D [Float] data.
 */
class FloatTensor1D(
    data: Array<Float>
) : Tensor1D<Float>(data) {
    override fun doAdd(a: Float, b: Float): Float = a + b

    override fun doSub(a: Float, b: Float): Float = a - b

    override fun doMul(a: Float, b: Float): Float = a * b

    override fun doDiv(a: Float, b: Float): Float = a / b

    override fun doMod(a: Float, b: Float): Float = a % b

    override fun another(l: List<Float>): Tensor1D<Float> =
        FloatTensor1D(l.toTypedArray())
}

fun floatTensorOf1D(data: List<Float>): FloatTensor1D = FloatTensor1D(data.toTypedArray())

fun floatTensorOf1D(data: Array<Float>): FloatTensor1D = FloatTensor1D(data)

fun floatTensorOf1D(data: FloatArray): FloatTensor1D = FloatTensor1D(data.toTypedArray())

/**
 * Only access 1D [Double] data.
 */
class DoubleTensor1D(
    data: Array<Double>
) : Tensor1D<Double>(data) {
    override fun doAdd(a: Double, b: Double): Double = a + b

    override fun doSub(a: Double, b: Double): Double = a - b

    override fun doMul(a: Double, b: Double): Double = a * b

    override fun doDiv(a: Double, b: Double): Double = a / b

    override fun doMod(a: Double, b: Double): Double = a % b

    override fun another(l: List<Double>): Tensor1D<Double> =
        DoubleTensor1D(l.toTypedArray())
}

fun doubleTensorOf1D(data: List<Double>): DoubleTensor1D = DoubleTensor1D(data.toTypedArray())

fun doubleTensorOf1D(data: Array<Double>): DoubleTensor1D = DoubleTensor1D(data)

fun doubleTensorOf1D(data: DoubleArray): DoubleTensor1D = DoubleTensor1D(data.toTypedArray())

/**
 * Only access 1D [BigInteger] data.
 */
class BigIntegerTensor1D(
    data: Array<BigInteger>
) : Tensor1D<BigInteger>(data) {
    override fun doAdd(a: BigInteger, b: BigInteger): BigInteger = a + b

    override fun doSub(a: BigInteger, b: BigInteger): BigInteger = a - b

    override fun doMul(a: BigInteger, b: BigInteger): BigInteger = a * b

    override fun doDiv(a: BigInteger, b: BigInteger): BigInteger = a / b

    override fun doMod(a: BigInteger, b: BigInteger): BigInteger = a % b

    override fun another(l: List<BigInteger>): Tensor1D<BigInteger> =
        BigIntegerTensor1D(l.toTypedArray())
}

fun bigIntegerTensorOf1D(data: List<BigInteger>): BigIntegerTensor1D = BigIntegerTensor1D(data.toTypedArray())

fun bigIntegerTensorOf1D(data: Array<BigInteger>): BigIntegerTensor1D = BigIntegerTensor1D(data)

/**
 * Only access 1D [BigDecimal] data.
 */
class BigDecimalTensor1D(
    data: Array<BigDecimal>
) : Tensor1D<BigDecimal>(data) {
    override fun doAdd(a: BigDecimal, b: BigDecimal): BigDecimal = a + b

    override fun doSub(a: BigDecimal, b: BigDecimal): BigDecimal = a - b

    override fun doMul(a: BigDecimal, b: BigDecimal): BigDecimal = a * b

    override fun doDiv(a: BigDecimal, b: BigDecimal): BigDecimal = a / b

    override fun doMod(a: BigDecimal, b: BigDecimal): BigDecimal = a % b

    override fun another(l: List<BigDecimal>): Tensor1D<BigDecimal> =
        BigDecimalTensor1D(l.toTypedArray())
}

fun bigDecimalTensorOf1D(data: List<BigDecimal>): BigDecimalTensor1D = BigDecimalTensor1D(data.toTypedArray())

fun bigDecimalTensorOf1D(data: Array<BigDecimal>): BigDecimalTensor1D = BigDecimalTensor1D(data)
