package net.psunset.pytlin.collections

import net.psunset.pytlin.lang.toBigDecimal
import net.psunset.pytlin.lang.toBigInteger
import java.math.BigDecimal
import java.math.BigInteger

abstract class Tensor1D<E : Number> private constructor(
    data: Array<E>, space: TensorSpace
) : Tensor_D<E>(data, space) {

    @Suppress("UNCHECKED_CAST")
    override val data = super.data as Array<E>
    inline val size: Int get() = this.numel

    constructor(data: Array<E>) : this(data, Tensors.space(data.size))

    init {
        require(data.size == this.numel) { "The data is invalid because of its wrong size." }
    }

    operator fun get(index: Int): E = this[Tensors.index(index)]
    override fun get(index: TensorIndex): E {
        require(isValidIndex(index)) { "The index is invalid in this tensor." }
        return this.data[index[0]]
    }

    operator fun set(index: Int, value: E) = this.set(Tensors.index(index), value)
    override fun set(index: TensorIndex, value: E) {
        require(isValidIndex(index)) { "The index is invalid in this tensor." }
        this.data[index[0]] = value
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
        requireSameFirstDimSize(vector)
        for (i in 0..<this.numel) {
            this[i] = doAdd(this[i], vector[i])
        }
    }

    operator fun minusAssign(vector: Tensor1D<E>) {
        requireSameFirstDimSize(vector)
        for (i in 0..<this.numel) {
            this[i] = doSub(this[i], vector[i])
        }
    }

    operator fun timesAssign(vector: Tensor1D<E>) {
        requireSameFirstDimSize(vector)
        for (i in 0..<this.numel) {
            this[i] = doMul(this[i], vector[i])
        }
    }

    operator fun divAssign(vector: Tensor1D<E>) {
        requireSameFirstDimSize(vector)
        for (i in 0..<this.numel) {
            this[i] = doDiv(this[i], vector[i])
        }
    }

    operator fun remAssign(vector: Tensor1D<E>) {
        requireSameFirstDimSize(vector)
        for (i in 0..<this.numel) {
            this[i] = doMod(this[i], vector[i])
        }
    }

    infix fun dot(vector: Tensor1D<E>): E {
        requireSameFirstDimSize(vector)
        return this.data.zip(vector.data).map { (a, b) -> doMul(a, b) }.reduce { a, b -> doAdd(a, b) }
    }

    infix fun cross(vector: Tensor1D<E>): Tensor1D<E> {
        requireSameFirstDimSize(vector)
        if (this.numel != 3) {
            throw IllegalArgumentException("Vectors must have dimension 3 to calculate the cross product.")
        }

        val a1 = data[0]
        val a2 = data[1]
        val a3 = data[2]
        val b1 = vector.data[0]
        val b2 = vector.data[1]
        val b3 = vector.data[2]

        return newOne(
            listOf(
                doSub(doMul(a2, b3), doMul(a3, b2)),
                doSub(doMul(a3, b1), doMul(a1, b3)),
                doSub(doMul(a1, b2), doMul(a2, b1))
            )
        )
    }

    /**
     * Alias for [cross] function.
     * Because `a x b` looks really like a cross b.
     */
    inline infix fun x(vector: Tensor1D<E>): Tensor1D<E> = cross(vector)

    protected abstract fun doAdd(a: E, b: E): E
    protected abstract fun doSub(a: E, b: E): E
    protected abstract fun doMul(a: E, b: E): E
    protected abstract fun doDiv(a: E, b: E): E
    protected abstract fun doMod(a: E, b: E): E
    protected abstract fun newOne(l: List<E>): Tensor1D<E>

    open fun toIntTensor(): Tensor1D<Int> = IntTensor1D(this.data.map { it.toInt() }.toTypedArray())
    open fun toLongTensor(): Tensor1D<Long> = LongTensor1D(this.data.map { it.toLong() }.toTypedArray())
    open fun toFloatTensor(): Tensor1D<Float> = FloatTensor1D(this.data.map { it.toFloat() }.toTypedArray())
    open fun toDoubleTensor(): Tensor1D<Double> = DoubleTensor1D(this.data.map { it.toDouble() }.toTypedArray())

    open fun toBigIntegerTensor(): Tensor1D<BigInteger> =
        BigIntegerTensor1D(this.data.map { it.toBigInteger() }.toTypedArray())

    /**
     * After testing, this function may return the decimal with a bad scale.
     * If you want to limit the min scale.
     * Please use the overloading function with `factory` parameter instead.
     * For example:
     * ```
     * val tensorWithDouble = tensorOf1D(arrayOf(53.9, 854.3, 264.7, 964.4))
     * // val tensorWithBigDecimal = tensorWithDouble.toBigDecimalTensor() // With bad scale
     * val tensorWithBigDecimal = tensorWithDouble.toBigDecimalTensor { it.toBigDecimal(?) }
     * // Add this line and replace `?` to expected min scale
     * ```
     * @see toBigDecimalTensor toBigDecimalTensor(factory: (E) -> BigDecimal)
     */
    open fun toBigDecimalTensor(): Tensor1D<BigDecimal> =
        BigDecimalTensor1D(this.data.map { it.toBigDecimal() }.toTypedArray())

    fun toIntTensor(factory: (E) -> Int): Tensor1D<Int> = IntTensor1D(this.data.map(factory).toTypedArray())
    fun toLongTensor(factory: (E) -> Long): Tensor1D<Long> = LongTensor1D(this.data.map(factory).toTypedArray())
    fun toFloatTensor(factory: (E) -> Float): Tensor1D<Float> = FloatTensor1D(this.data.map(factory).toTypedArray())
    fun toDoubleTensor(factory: (E) -> Double): Tensor1D<Double> = DoubleTensor1D(this.data.map(factory).toTypedArray())

    fun toBigIntegerTensor(factory: (E) -> BigInteger): Tensor1D<BigInteger> =
        BigIntegerTensor1D(this.data.map(factory).toTypedArray())

    fun toBigDecimalTensor(factory: (E) -> BigDecimal): Tensor1D<BigDecimal> =
        BigDecimalTensor1D(this.data.map(factory).toTypedArray())

    override fun iterator(): Iterator<E> = this.data.iterator()

    override fun contentDeepToString(highestDim: Int): String =
        StringBuilder()
            .append('[')
            .append(this.data.joinToString())
            .append(']')
            .toString()
}

/**
 * Only access 1D [Int] data.
 */
class IntTensor1D(
    data: Array<Int>
) : Tensor1D<Int>(data), IntAsDtype {
    override fun doAdd(a: Int, b: Int): Int = a + b

    override fun doSub(a: Int, b: Int): Int = a - b

    override fun doMul(a: Int, b: Int): Int = a * b

    override fun doDiv(a: Int, b: Int): Int = a / b

    override fun doMod(a: Int, b: Int): Int = a % b

    override fun newOne(l: List<Int>): Tensor1D<Int> =
        IntTensor1D(l.toTypedArray())

    override fun toIntTensor(): IntTensor1D = IntTensor1D(this.data.clone())
}

/**
 * Only access 1D [Long] data.
 */
class LongTensor1D(
    data: Array<Long>
) : Tensor1D<Long>(data), LongAsDtype {
    override fun doAdd(a: Long, b: Long): Long = a + b

    override fun doSub(a: Long, b: Long): Long = a - b

    override fun doMul(a: Long, b: Long): Long = a * b

    override fun doDiv(a: Long, b: Long): Long = a / b

    override fun doMod(a: Long, b: Long): Long = a % b

    override fun newOne(l: List<Long>): Tensor1D<Long> =
        LongTensor1D(l.toTypedArray())

    override fun toLongTensor(): LongTensor1D = LongTensor1D(this.data.clone())
}

/**
 * Only access 1D [Float] data.
 */
class FloatTensor1D(
    data: Array<Float>
) : Tensor1D<Float>(data), FloatAsDtype {
    override fun doAdd(a: Float, b: Float): Float = a + b

    override fun doSub(a: Float, b: Float): Float = a - b

    override fun doMul(a: Float, b: Float): Float = a * b

    override fun doDiv(a: Float, b: Float): Float = a / b

    override fun doMod(a: Float, b: Float): Float = a % b

    override fun newOne(l: List<Float>): Tensor1D<Float> =
        FloatTensor1D(l.toTypedArray())

    override fun toFloatTensor(): FloatTensor1D = FloatTensor1D(this.data.clone())
}

/**
 * Only access 1D [Double] data.
 */
class DoubleTensor1D(
    data: Array<Double>
) : Tensor1D<Double>(data), DoubleAsDtype {
    override fun doAdd(a: Double, b: Double): Double = a + b

    override fun doSub(a: Double, b: Double): Double = a - b

    override fun doMul(a: Double, b: Double): Double = a * b

    override fun doDiv(a: Double, b: Double): Double = a / b

    override fun doMod(a: Double, b: Double): Double = a % b

    override fun newOne(l: List<Double>): Tensor1D<Double> =
        DoubleTensor1D(l.toTypedArray())

    override fun toDoubleTensor(): DoubleTensor1D = DoubleTensor1D(this.data.clone())
}

/**
 * Only access 1D [BigInteger] data.
 */
class BigIntegerTensor1D(
    data: Array<BigInteger>
) : Tensor1D<BigInteger>(data), BigIntegerAsDtype {
    override fun doAdd(a: BigInteger, b: BigInteger): BigInteger = a + b

    override fun doSub(a: BigInteger, b: BigInteger): BigInteger = a - b

    override fun doMul(a: BigInteger, b: BigInteger): BigInteger = a * b

    override fun doDiv(a: BigInteger, b: BigInteger): BigInteger = a / b

    override fun doMod(a: BigInteger, b: BigInteger): BigInteger = a % b

    override fun newOne(l: List<BigInteger>): Tensor1D<BigInteger> =
        BigIntegerTensor1D(l.toTypedArray())

    override fun toBigIntegerTensor(): BigIntegerTensor1D = BigIntegerTensor1D(this.data.clone())
}

/**
 * Only access 1D [BigDecimal] data.
 */
class BigDecimalTensor1D(
    data: Array<BigDecimal>
) : Tensor1D<BigDecimal>(data), BigDecimalAsDtype {
    override fun doAdd(a: BigDecimal, b: BigDecimal): BigDecimal = a + b

    override fun doSub(a: BigDecimal, b: BigDecimal): BigDecimal = a - b

    override fun doMul(a: BigDecimal, b: BigDecimal): BigDecimal = a * b

    override fun doDiv(a: BigDecimal, b: BigDecimal): BigDecimal = a / b

    override fun doMod(a: BigDecimal, b: BigDecimal): BigDecimal = a % b

    override fun newOne(l: List<BigDecimal>): Tensor1D<BigDecimal> =
        BigDecimalTensor1D(l.toTypedArray())

    override fun toBigDecimalTensor(): BigDecimalTensor1D = BigDecimalTensor1D(this.data.clone())
}

inline fun <reified E : Number> tensorOf1D(data: List<E>): Tensor1D<E> = Tensors.of1D(data.toTypedArray())
inline fun <reified E : Number> tensorOf1D(data: Array<E>): Tensor1D<E> = Tensors.of1D(data)
@JvmName("tensorOf1DVararg")
inline fun <reified E : Number> tensorOf1D(vararg data: E): Tensor1D<E> = Tensors.of1D(data)

inline fun <reified E : Number> List<E>.toTensor(): Tensor1D<E> = Tensors.of1D(this.toTypedArray())
inline fun <reified E : Number> Array<E>.toTensor(): Tensor1D<E> = Tensors.of1D(this)

fun intTensorOf1D(data: ByteArray): IntTensor1D = IntTensor1D(data.map { it.toInt() }.toTypedArray())
fun intTensorOf1D(data: ShortArray): IntTensor1D = IntTensor1D(data.map { it.toInt() }.toTypedArray())
fun intTensorOf1D(data: IntArray): IntTensor1D = IntTensor1D(data.toTypedArray())
fun longTensorOf1D(data: LongArray): LongTensor1D = LongTensor1D(data.toTypedArray())
fun floatTensorOf1D(data: FloatArray): FloatTensor1D = FloatTensor1D(data.toTypedArray())
fun doubleTensorOf1D(data: DoubleArray): DoubleTensor1D = DoubleTensor1D(data.toTypedArray())

fun ByteArray.toTensor(): IntTensor1D = IntTensor1D(this.map { it.toInt() }.toTypedArray())
fun ShortArray.toTensor(): IntTensor1D = IntTensor1D(this.map { it.toInt() }.toTypedArray())
fun IntArray.toTensor(): IntTensor1D = IntTensor1D(this.toTypedArray())
fun LongArray.toTensor(): LongTensor1D = LongTensor1D(this.toTypedArray())
fun FloatArray.toTensor(): FloatTensor1D = FloatTensor1D(this.toTypedArray())
fun DoubleArray.toTensor(): DoubleTensor1D = DoubleTensor1D(this.toTypedArray())


