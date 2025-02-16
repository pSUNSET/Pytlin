package net.psunset.pytlin.collections

import net.psunset.pytlin.lang.times
import net.psunset.pytlin.lang.toBigDecimal
import net.psunset.pytlin.lang.toBigInteger
import java.math.BigDecimal
import java.math.BigInteger

abstract class Tensor2D<E : Number> private constructor(
    data: Array<Tensor1D<E>>, space: TensorSpace
) : Tensor<E>(data, space) {

    @Suppress("UNCHECKED_CAST")
    override val data = super.data as Array<Tensor1D<E>>
    inline val rows: Int get() = this.shape[0]
    inline val cols: Int get() = this.shape[1]

    constructor(data: Array<Tensor1D<E>>) : this(data, Tensors.space(data.size, data[0].numel))

    init {
        val c = space[1]
        require(data.all { it.numel == c }) { "All 1d tensors must have same size to convert into 2d tensor." }
    }

    operator fun get(i: Int, ii: Int): E = this[Tensors.index(i, ii)]
    override fun get(index: TensorIndex): E {
        require(isValidIndex(index)) { "The index is invalid in this tensor." }
        return this.data[index[0]][index[1]]
    }

    operator fun set(i: Int, ii: Int, value: E) = this.set(Tensors.index(i, ii), value)
    override fun set(index: TensorIndex, value: E) {
        require(isValidIndex(index)) { "The index is invalid in this tensor." }
        this.data[index[0]][index[1]] = value
    }

    operator fun plus(scalar: E): Tensor2D<E> = this.apply { this.plusAssign(scalar) }
    operator fun minus(scalar: E): Tensor2D<E> = this.apply { this.minusAssign(scalar) }
    operator fun times(scalar: E): Tensor2D<E> = this.apply { this.timesAssign(scalar) }
    operator fun div(scalar: E): Tensor2D<E> = this.apply { this.divAssign(scalar) }
    operator fun rem(scalar: E): Tensor2D<E> = this.apply { this.remAssign(scalar) }

    operator fun plusAssign(scalar: E) {
        for (i in 0..<this.rows) {
            this.data[i].plusAssign(scalar)
        }
    }

    operator fun minusAssign(scalar: E) {
        for (i in 0..<this.rows) {
            this.data[i].minus(scalar)
        }
    }

    operator fun timesAssign(scalar: E) {
        for (i in 0..<this.rows) {
            this.data[i].timesAssign(scalar)
        }
    }

    operator fun divAssign(scalar: E) {
        for (i in 0..<this.rows) {
            this.data[i].divAssign(scalar)
        }
    }

    operator fun remAssign(scalar: E) {
        for (i in 0..<this.rows) {
            this.data[i].remAssign(scalar)
        }
    }

    operator fun plus(vector: Tensor1D<E>): Tensor2D<E> = this.apply { this.plusAssign(vector) }
    operator fun minus(vector: Tensor1D<E>): Tensor2D<E> = this.apply { this.minusAssign(vector) }
    operator fun times(vector: Tensor1D<E>): Tensor2D<E> = this.apply { this.timesAssign(vector) }
    operator fun div(vector: Tensor1D<E>): Tensor2D<E> = this.apply { this.divAssign(vector) }
    operator fun rem(vector: Tensor1D<E>): Tensor2D<E> = this.apply { this.remAssign(vector) }

    operator fun plusAssign(vector: Tensor1D<E>) {
        for (i in 0..<this.rows) {
            this.data[i].plusAssign(vector)
        }
    }

    operator fun minusAssign(vector: Tensor1D<E>) {
        for (i in 0..<this.rows) {
            this.data[i].minusAssign(vector)
        }
    }

    operator fun timesAssign(vector: Tensor1D<E>) {
        for (i in 0..<this.rows) {
            this.data[i].timesAssign(vector)
        }
    }

    operator fun divAssign(vector: Tensor1D<E>) {
        for (i in 0..<this.rows) {
            this.data[i].divAssign(vector)
        }
    }

    operator fun remAssign(vector: Tensor1D<E>) {
        for (i in 0..<this.rows) {
            this.data[i].remAssign(vector)
        }
    }

    operator fun plus(matrix: Tensor2D<E>): Tensor2D<E> = this.apply { this.plusAssign(matrix) }
    operator fun minus(matrix: Tensor2D<E>): Tensor2D<E> = this.apply { this.minusAssign(matrix) }
    operator fun times(matrix: Tensor2D<E>): Tensor2D<E> = this.apply { this.timesAssign(matrix) }
    operator fun div(matrix: Tensor2D<E>): Tensor2D<E> = this.apply { this.divAssign(matrix) }
    operator fun rem(matrix: Tensor2D<E>): Tensor2D<E> = this.apply { this.remAssign(matrix) }

    operator fun plusAssign(matrix: Tensor2D<E>) {
        requireSameFirstDimSize(matrix)
        for (i in 0..<this.rows) {
            this.data[i].plusAssign(matrix.data[i])
        }
    }

    operator fun minusAssign(matrix: Tensor2D<E>) {
        requireSameFirstDimSize(matrix)
        for (i in 0..<this.rows) {
            this.data[i].minusAssign(matrix.data[i])
        }
    }

    operator fun timesAssign(matrix: Tensor2D<E>) {
        requireSameFirstDimSize(matrix)
        for (i in 0..<this.rows) {
            this.data[i].timesAssign(matrix.data[i])
        }
    }

    operator fun divAssign(matrix: Tensor2D<E>) {
        requireSameFirstDimSize(matrix)
        for (i in 0..<this.rows) {
            this.data[i].divAssign(matrix.data[i])
        }
    }

    operator fun remAssign(matrix: Tensor2D<E>) {
        requireSameFirstDimSize(matrix)
        for (i in 0..<this.rows) {
            this.data[i].remAssign(matrix.data[i])
        }
    }

    /**
     * Stands for Matrix Multiplication
     */
    infix fun matmul(matrix: Tensor2D<E>): Tensor2D<E> {
        require(this.cols == matrix.rows) { "The cols of this tensor must equals to the rows of another tensor!" }
        val mm = MutableList<MutableList<E>>(this.rows) { ArrayList(matrix.cols) }
        val matrixT = matrix.transposed()
        for (i in 0..<this.rows) {
            for (ii in 0..<matrix.cols) {
                mm[i].add(this.data[i] dot matrixT.data[ii])
            }
        }
        return newOne(mm)
    }

    /**
     * Alias for [matmul] function.
     * Because `a x b` looks really like a multiplied by b.
     */
    inline infix fun x(matrix: Tensor2D<E>): Tensor2D<E> = matmul(matrix)

    fun transposed(): Tensor2D<E> {
        val thisT = MutableList<MutableList<E>>(cols) { ArrayList(rows) }

        for (ii in 0..<cols) {
            for (i in 0..<rows) {
                thisT[ii].add(this[i, ii]) // Calculate the correct index
            }
        }

        return newOne(thisT)
    }

    protected abstract fun newOne(l: List<List<E>>): Tensor2D<E>

    open fun toIntTensor(): Tensor2D<Int> = IntTensor2D(this.data.map { it.toIntTensor() }.toTypedArray())
    open fun toLongTensor(): Tensor2D<Long> = LongTensor2D(this.data.map { it.toLongTensor() }.toTypedArray())
    open fun toFloatTensor(): Tensor2D<Float> = FloatTensor2D(this.data.map { it.toFloatTensor() }.toTypedArray())
    open fun toDoubleTensor(): Tensor2D<Double> = DoubleTensor2D(this.data.map { it.toDoubleTensor() }.toTypedArray())

    open fun toBigIntegerTensor(): Tensor2D<BigInteger> =
        BigIntegerTensor2D(this.data.map { it.toBigIntegerTensor() }.toTypedArray())

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
    open fun toBigDecimalTensor(): Tensor2D<BigDecimal> =
        BigDecimalTensor2D(this.data.map { it.toBigDecimalTensor() }.toTypedArray())

    fun toIntTensor(factory: (E) -> Int): Tensor2D<Int> = IntTensor2D(this.data.map { it.toIntTensor(factory) }.toTypedArray())
    fun toLongTensor(factory: (E) -> Long): Tensor2D<Long> = LongTensor2D(this.data.map { it.toLongTensor(factory) }.toTypedArray())
    fun toFloatTensor(factory: (E) -> Float): Tensor2D<Float> = FloatTensor2D(this.data.map { it.toFloatTensor(factory) }.toTypedArray())
    fun toDoubleTensor(factory: (E) -> Double): Tensor2D<Double> = DoubleTensor2D(this.data.map { it.toDoubleTensor(factory) }.toTypedArray())

    fun toBigIntegerTensor(factory: (E) -> BigInteger): Tensor2D<BigInteger> =
        BigIntegerTensor2D(this.data.map { it.toBigIntegerTensor(factory) }.toTypedArray())

    fun toBigDecimalTensor(factory: (E) -> BigDecimal): Tensor2D<BigDecimal> =
        BigDecimalTensor2D(this.data.map { it.toBigDecimalTensor(factory) }.toTypedArray())

    override fun iterator(): Iterator<Tensor1D<E>> = this.data.iterator()

    override fun contentDeepToString(highestDim: Int): String {
        val result = StringBuilder()
        result.append('[')
        var isFirst = true
        for (element in this) {
            if (isFirst) isFirst = false
            else result.append(',').append('\n').append(' ' * (7 + highestDim))
            result.append(element.contentDeepToString(highestDim))
        }
        return result.toString()
    }
}

class IntTensor2D(
    data: Array<Tensor1D<Int>>
) : Tensor2D<Int>(data), IntAsDtype {
    override fun newOne(l: List<List<Int>>): Tensor2D<Int> =
        IntTensor2D(l.map { Tensors.of1D(it) }.toTypedArray())
}

class LongTensor2D(
    data: Array<Tensor1D<Long>>
) : Tensor2D<Long>(data), LongAsDtype {
    override fun newOne(l: List<List<Long>>): Tensor2D<Long> =
        LongTensor2D(l.map { Tensors.of1D(it) }.toTypedArray())
}

class FloatTensor2D(
    data: Array<Tensor1D<Float>>
) : Tensor2D<Float>(data), FloatAsDtype {
    override fun newOne(l: List<List<Float>>): Tensor2D<Float> =
        FloatTensor2D(l.map { Tensors.of1D(it) }.toTypedArray())
}

class DoubleTensor2D(
    data: Array<Tensor1D<Double>>
) : Tensor2D<Double>(data), DoubleAsDtype {
    override fun newOne(l: List<List<Double>>): Tensor2D<Double> =
        DoubleTensor2D(l.map { Tensors.of1D(it) }.toTypedArray())
}

class BigIntegerTensor2D(
    data: Array<Tensor1D<BigInteger>>
) : Tensor2D<BigInteger>(data), BigIntegerAsDtype {
    override fun newOne(l: List<List<BigInteger>>): Tensor2D<BigInteger> =
        BigIntegerTensor2D(l.map { Tensors.of1D(it) }.toTypedArray())
}

class BigDecimalTensor2D(
    data: Array<Tensor1D<BigDecimal>>
) : Tensor2D<BigDecimal>(data), BigDecimalAsDtype {
    override fun newOne(l: List<List<BigDecimal>>): Tensor2D<BigDecimal> =
        BigDecimalTensor2D(l.map { Tensors.of1D(it) }.toTypedArray())
}

inline fun <reified E : Number> tensorOf2D(data: List<Tensor1D<E>>): Tensor2D<E> =
    Tensors.of2D(data)

inline fun <reified E : Number> tensorOf2D(vararg data: Tensor1D<E>): Tensor2D<E> =
    Tensors.of2D(data)
