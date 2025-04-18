package net.psunset.pytlin.collections

import net.psunset.pytlin.lang.times
import java.math.BigDecimal
import java.math.BigInteger

abstract class Tensor2D<E : Number>(
    data: Array<out Tensor1D<E>>
) : Tensor_D<E>(data, Tensors.space(data.size, data[0].size)) {

    @Suppress("UNCHECKED_CAST")
    override val data = super.data as Array<Tensor1D<E>>
    inline val rows: Int get() = this.shape[0]
    inline val cols: Int get() = this.shape[1]

    init {
        require(data.all { it.numel == this.cols }) { "All 1D tensors must have same size to convert into 2D tensor." }
    }

    operator fun get(r: Int, c: Int): E = this[Tensors.index(r, c)]
    override fun get(index: TensorIndex): E {
        require(isValidIndex(index)) { "The index is invalid in this tensor." }
        return this.data[index[0]].data[index[1]]
    }

    operator fun set(r: Int, c: Int, value: E) = this.set(Tensors.index(r, c), value)
    override fun set(index: TensorIndex, value: E) {
        require(isValidIndex(index)) { "The index is invalid in this tensor." }
        this.data[index[0]].data[index[1]] = value
    }

    operator fun plus(scalar: E): Tensor2D<E> = this.clone().apply { this.plusAssign(scalar) }
    operator fun minus(scalar: E): Tensor2D<E> = this.clone().apply { this.minusAssign(scalar) }
    operator fun times(scalar: E): Tensor2D<E> = this.clone().apply { this.timesAssign(scalar) }
    operator fun div(scalar: E): Tensor2D<E> = this.clone().apply { this.divAssign(scalar) }
    operator fun rem(scalar: E): Tensor2D<E> = this.clone().apply { this.remAssign(scalar) }

    operator fun plusAssign(scalar: E) {
        for (i in 0..<this.rows) {
            this.data[i].plusAssign(scalar)
        }
    }

    operator fun minusAssign(scalar: E) {
        for (i in 0..<this.rows) {
            this.data[i].minusAssign(scalar)
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

    operator fun plus(vector: Tensor1D<E>): Tensor2D<E> = this.clone().apply { this.plusAssign(vector) }
    operator fun minus(vector: Tensor1D<E>): Tensor2D<E> = this.clone().apply { this.minusAssign(vector) }
    operator fun times(vector: Tensor1D<E>): Tensor2D<E> = this.clone().apply { this.timesAssign(vector) }
    operator fun div(vector: Tensor1D<E>): Tensor2D<E> = this.clone().apply { this.divAssign(vector) }
    operator fun rem(vector: Tensor1D<E>): Tensor2D<E> = this.clone().apply { this.remAssign(vector) }

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

    operator fun plus(matrix: Tensor2D<E>): Tensor2D<E> = this.clone().apply { this.plusAssign(matrix) }
    operator fun minus(matrix: Tensor2D<E>): Tensor2D<E> = this.clone().apply { this.minusAssign(matrix) }
    operator fun times(matrix: Tensor2D<E>): Tensor2D<E> = this.clone().apply { this.timesAssign(matrix) }
    operator fun div(matrix: Tensor2D<E>): Tensor2D<E> = this.clone().apply { this.divAssign(matrix) }
    operator fun rem(matrix: Tensor2D<E>): Tensor2D<E> = this.clone().apply { this.remAssign(matrix) }

    operator fun plusAssign(matrix: Tensor2D<E>) {
        requireSameDim1Size(matrix)
        for (i in 0..<this.rows) {
            this.data[i].plusAssign(matrix.data[i])
        }
    }

    operator fun minusAssign(matrix: Tensor2D<E>) {
        requireSameDim1Size(matrix)
        for (i in 0..<this.rows) {
            this.data[i].minusAssign(matrix.data[i])
        }
    }

    operator fun timesAssign(matrix: Tensor2D<E>) {
        requireSameDim1Size(matrix)
        for (i in 0..<this.rows) {
            this.data[i].timesAssign(matrix.data[i])
        }
    }

    operator fun divAssign(matrix: Tensor2D<E>) {
        requireSameDim1Size(matrix)
        for (i in 0..<this.rows) {
            this.data[i].divAssign(matrix.data[i])
        }
    }

    operator fun remAssign(matrix: Tensor2D<E>) {
        requireSameDim1Size(matrix)
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
        val matrixT = matrix.transpose()
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

    fun transpose(): Tensor2D<E> {
        val thisT = MutableList<MutableList<E>>(cols) { ArrayList(rows) }

        for (ii in 0..<cols) {
            for (i in 0..<rows) {
                thisT[ii].add(this[i, ii]) // Calculate the correct index
            }
        }

        return newOne(thisT)
    }

    @JvmName("powOfInt")
    infix fun pow(scalar: Int): Tensor2D<E> = this.clone().apply { this.pow_(scalar) }
    infix fun <N : Number> pow(scalar: N): Tensor2D<E> = this.clone().apply { this.pow_(scalar) }

    @JvmName("powOfInt")
    infix fun pow(vector: Tensor1D<Int>): Tensor2D<E> = this.clone().apply { this.pow_(vector) }
    infix fun <N : Number> pow(vector: Tensor1D<N>): Tensor2D<E> = this.clone().apply { this.pow_(vector) }

    @JvmName("powOfInt")
    infix fun pow(matrix: Tensor2D<Int>): Tensor2D<E> = this.clone().apply { this.pow_(matrix) }
    infix fun <N : Number> pow(matrix: Tensor2D<N>): Tensor2D<E> = this.clone().apply { this.pow_(matrix) }

    @JvmName("pow_OfInt")
    fun pow_(scalar: Int) {
        for (i in 0..<this.rows) {
            this.data[i].pow_(scalar)
        }
    }

    fun <N : Number> pow_(scalar: N) {
        for (i in 0..<this.rows) {
            this.data[i].pow_(scalar)
        }
    }

    @JvmName("pow_OfInt")
    fun pow_(vector: Tensor1D<Int>) {
        for (i in 0..<this.rows) {
            this.data[i].pow_(vector)
        }
    }

    fun <N : Number> pow_(vector: Tensor1D<N>) {
        for (i in 0..<this.rows) {
            this.data[i].pow_(vector)
        }
    }

    @JvmName("pow_OfInt")
    fun pow_(matrix: Tensor2D<Int>) {
        requireSameDim1Size(matrix)
        for (i in 0..<this.rows) {
            this.data[i].pow_(matrix.data[i])
        }
    }

    fun <N : Number> pow_(matrix: Tensor2D<N>) {
        requireSameDim1Size(matrix)
        for (i in 0..<this.rows) {
            this.data[i].pow_(matrix.data[i])
        }
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
     * val tensorWithDouble = tensorOf(arrayOf(53.9, 854.3, 264.7, 964.4))
     * // val tensorWithBigDecimal = tensorWithDouble.toBigDecimalTensor() // With bad scale
     * val tensorWithBigDecimal = tensorWithDouble.toBigDecimalTensor { it.toBigDecimal(?) }
     * // Add this line and replace `?` with expected min scale
     * ```
     * @see toBigDecimalTensor toBigDecimalTensor(factory: (E) -> BigDecimal)
     */
    open fun toBigDecimalTensor(): Tensor2D<BigDecimal> =
        BigDecimalTensor2D(this.data.map { it.toBigDecimalTensor() }.toTypedArray())

    fun toIntTensor(factory: (E) -> Int): Tensor2D<Int> =
        IntTensor2D(this.data.map { it.toIntTensor(factory) }.toTypedArray())

    fun toLongTensor(factory: (E) -> Long): Tensor2D<Long> =
        LongTensor2D(this.data.map { it.toLongTensor(factory) }.toTypedArray())

    fun toFloatTensor(factory: (E) -> Float): Tensor2D<Float> =
        FloatTensor2D(this.data.map { it.toFloatTensor(factory) }.toTypedArray())

    fun toDoubleTensor(factory: (E) -> Double): Tensor2D<Double> =
        DoubleTensor2D(this.data.map { it.toDoubleTensor(factory) }.toTypedArray())

    fun toBigIntegerTensor(factory: (E) -> BigInteger): Tensor2D<BigInteger> =
        BigIntegerTensor2D(this.data.map { it.toBigIntegerTensor(factory) }.toTypedArray())

    fun toBigDecimalTensor(factory: (E) -> BigDecimal): Tensor2D<BigDecimal> =
        BigDecimalTensor2D(this.data.map { it.toBigDecimalTensor(factory) }.toTypedArray())

    abstract override fun clone(): Tensor2D<E>

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
        result.append(']')
        return result.toString()
    }
}

class IntTensor2D(
    data: Array<out Tensor1D<Int>>
) : Tensor2D<Int>(data), WithDtype.DInt {
    override fun newOne(l: List<List<Int>>): Tensor2D<Int> =
        IntTensor2D(l.map { tensorOf(it) }.toTypedArray())

    override fun clone(): Tensor2D<Int> = this.toIntTensor()
}

class LongTensor2D(
    data: Array<out Tensor1D<Long>>
) : Tensor2D<Long>(data), WithDtype.DLong {
    override fun newOne(l: List<List<Long>>): Tensor2D<Long> =
        LongTensor2D(l.map { tensorOf(it) }.toTypedArray())

    override fun clone(): Tensor2D<Long> = this.toLongTensor()
}

class FloatTensor2D(
    data: Array<out Tensor1D<Float>>
) : Tensor2D<Float>(data), WithDtype.DFloat {
    override fun newOne(l: List<List<Float>>): Tensor2D<Float> =
        FloatTensor2D(l.map { tensorOf(it) }.toTypedArray())

    override fun clone(): Tensor2D<Float> = this.toFloatTensor()
}

class DoubleTensor2D(
    data: Array<out Tensor1D<Double>>
) : Tensor2D<Double>(data), WithDtype.DDouble {
    override fun newOne(l: List<List<Double>>): Tensor2D<Double> =
        DoubleTensor2D(l.map { tensorOf(it) }.toTypedArray())

    override fun clone(): Tensor2D<Double> = this.toDoubleTensor()
}

class BigIntegerTensor2D(
    data: Array<out Tensor1D<BigInteger>>
) : Tensor2D<BigInteger>(data), WithDtype.DBigInteger {
    override fun newOne(l: List<List<BigInteger>>): Tensor2D<BigInteger> =
        BigIntegerTensor2D(l.map { tensorOf(it) }.toTypedArray())

    override fun clone(): Tensor2D<BigInteger> = this.toBigIntegerTensor()
}

class BigDecimalTensor2D(
    data: Array<out Tensor1D<BigDecimal>>
) : Tensor2D<BigDecimal>(data), WithDtype.DBigDecimal {
    override fun newOne(l: List<List<BigDecimal>>): Tensor2D<BigDecimal> =
        BigDecimalTensor2D(l.map { tensorOf(it) }.toTypedArray())

    override fun clone(): Tensor2D<BigDecimal> = this.toBigDecimalTensor()
}

@JvmName("tensor2DOfList_Tensor1D")
inline fun <reified E : Number> tensorOf(data: List<Tensor1D<out E>>): Tensor2D<E> = data.toTensor()

@JvmName("tensor2DOfList_List")
inline fun <reified E : Number> tensorOf(data: List<List<E>>): Tensor2D<E> = data.toTensor()

@JvmName("tensor2DOfList_Array")
inline fun <reified E : Number> tensorOf(data: List<Array<out E>>): Tensor2D<E> = data.toTensor()

@JvmName("tensor2DOfArray_Tensor1D")
inline fun <reified E : Number> tensorOf(data: Array<out Tensor1D<out E>>): Tensor2D<E> = data.toTensor()

@JvmName("tensor2DOfArray_List")
inline fun <reified E : Number> tensorOf(data: Array<out List<E>>): Tensor2D<E> = data.toTensor()

@JvmName("tensor2DOfArray_Array")
inline fun <reified E : Number> tensorOf(data: Array<out Array<out E>>): Tensor2D<E> = data.toTensor()

@JvmName("tensor2DOfVararg_Tensor1D")
inline fun <reified E : Number> tensorOf(vararg data: Tensor1D<out E>): Tensor2D<E> = data.toTensor()

@JvmName("tensor2DOfVararg_List")
inline fun <reified E : Number> tensorOf(vararg data: List<E>): Tensor2D<E> = data.toTensor()

@JvmName("tensor2DOfVararg_Array")
inline fun <reified E : Number> tensorOf(vararg data: Array<out E>): Tensor2D<E> = data.toTensor()

@JvmName("list_tensor1DToTensor2D")
inline fun <reified E : Number> List<Tensor1D<out E>>.toTensor(): Tensor2D<E> = Tensors.of2D(this.toTypedArray())

@JvmName("list_listToTensor2D")
inline fun <reified E : Number> List<List<E>>.toTensor(): Tensor2D<E> = tensorOf(this.map { it.toTensor() })

@JvmName("list_arrayToTensor2D")
inline fun <reified E : Number> List<Array<out E>>.toTensor(): Tensor2D<E> = tensorOf(this.map { it.toTensor() })

inline fun <reified E : Number> Array<out Tensor1D<out E>>.toTensor(): Tensor2D<E> = Tensors.of2D(this)
inline fun <reified E : Number> Array<out List<E>>.toTensor(): Tensor2D<E> = tensorOf(this.map { it.toTensor() })
inline fun <reified E : Number> Array<out Array<out E>>.toTensor(): Tensor2D<E> = tensorOf(this.map { it.toTensor() })
