package net.psunset.pytlin.collections

import net.psunset.pytlin.lang.times
import net.psunset.pytlin.utils.COMING_SOON
import java.math.BigDecimal
import java.math.BigInteger

abstract class Tensor3D<E : Number>(
    data: Array<out Tensor2D<E>>
) : Tensor_D<E>(data, Tensors.space(data.size, data[0].rows, data[0].cols)) {

    @Suppress("UNCHECKED_CAST")
    override val data = super.data as Array<Tensor2D<E>>

    init {
        require(data.all { it.space in this.space }) { "All 2D tensors must have same size to convert into 3D tensor." }
    }

    operator fun get(i: Int, ii: Int, iii: Int) = this[Tensors.index(i, ii, iii)]
    override fun get(index: TensorIndex): E {
        require(isValidIndex(index)) { "The index is invalid in this tensor." }
        return this.data[index[0]].data[index[1]].data[index[2]]
    }

    operator fun set(r: Int, c: Int, value: E) = this.set(Tensors.index(r, c), value)
    override fun set(index: TensorIndex, value: E) {
        require(isValidIndex(index)) { "The index is invalid in this tensor." }
        this.data[index[0]].data[index[1]].data[index[2]] = value
    }

    operator fun plus(scalar: E): Tensor3D<E> = this.clone().apply { this.plusAssign(scalar) }
    operator fun minus(scalar: E): Tensor3D<E> = this.clone().apply { this.minusAssign(scalar) }
    operator fun times(scalar: E): Tensor3D<E> = this.clone().apply { this.timesAssign(scalar) }
    operator fun div(scalar: E): Tensor3D<E> = this.clone().apply { this.divAssign(scalar) }
    operator fun rem(scalar: E): Tensor3D<E> = this.clone().apply { this.remAssign(scalar) }

    operator fun plusAssign(scalar: E) {
        for (i in 0..<this.shape[0]) {
            this.data[i].plusAssign(scalar)
        }
    }

    operator fun minusAssign(scalar: E) {
        for (i in 0..<this.shape[0]) {
            this.data[i].minusAssign(scalar)
        }
    }

    operator fun timesAssign(scalar: E) {
        for (i in 0..<this.shape[0]) {
            this.data[i].timesAssign(scalar)
        }
    }

    operator fun divAssign(scalar: E) {
        for (i in 0..<this.shape[0]) {
            this.data[i].divAssign(scalar)
        }
    }

    operator fun remAssign(scalar: E) {
        for (i in 0..<this.shape[0]) {
            this.data[i].remAssign(scalar)
        }
    }


    operator fun plus(vector: Tensor1D<E>): Tensor3D<E> = this.clone().apply { this.plusAssign(vector) }
    operator fun minus(vector: Tensor1D<E>): Tensor3D<E> = this.clone().apply { this.minusAssign(vector) }
    operator fun times(vector: Tensor1D<E>): Tensor3D<E> = this.clone().apply { this.timesAssign(vector) }
    operator fun div(vector: Tensor1D<E>): Tensor3D<E> = this.clone().apply { this.divAssign(vector) }
    operator fun rem(vector: Tensor1D<E>): Tensor3D<E> = this.clone().apply { this.remAssign(vector) }

    operator fun plusAssign(vector: Tensor1D<E>) {
        for (i in 0..<this.shape[0]) {
            this.data[i].plusAssign(vector)
        }
    }

    operator fun minusAssign(vector: Tensor1D<E>) {
        for (i in 0..<this.shape[0]) {
            this.data[i].minusAssign(vector)
        }
    }

    operator fun timesAssign(vector: Tensor1D<E>) {
        for (i in 0..<this.shape[0]) {
            this.data[i].timesAssign(vector)
        }
    }

    operator fun divAssign(vector: Tensor1D<E>) {
        for (i in 0..<this.shape[0]) {
            this.data[i].divAssign(vector)
        }
    }

    operator fun remAssign(vector: Tensor1D<E>) {
        for (i in 0..<this.shape[0]) {
            this.data[i].remAssign(vector)
        }
    }

    operator fun plus(matrix: Tensor2D<E>): Tensor3D<E> = this.clone().apply { this.plusAssign(matrix) }
    operator fun minus(matrix: Tensor2D<E>): Tensor3D<E> = this.clone().apply { this.minusAssign(matrix) }
    operator fun times(matrix: Tensor2D<E>): Tensor3D<E> = this.clone().apply { this.timesAssign(matrix) }
    operator fun div(matrix: Tensor2D<E>): Tensor3D<E> = this.clone().apply { this.divAssign(matrix) }
    operator fun rem(matrix: Tensor2D<E>): Tensor3D<E> = this.clone().apply { this.remAssign(matrix) }


    operator fun plusAssign(matrix: Tensor2D<E>) {
        for (i in 0..<this.shape[0]) {
            this.data[i].plusAssign(matrix)
        }
    }

    operator fun minusAssign(matrix: Tensor2D<E>) {
        for (i in 0..<this.shape[0]) {
            this.data[i].minusAssign(matrix)
        }
    }

    operator fun timesAssign(matrix: Tensor2D<E>) {
        for (i in 0..<this.shape[0]) {
            this.data[i].timesAssign(matrix)
        }
    }

    operator fun divAssign(matrix: Tensor2D<E>) {
        for (i in 0..<this.shape[0]) {
            this.data[i].divAssign(matrix)
        }
    }

    operator fun remAssign(matrix: Tensor2D<E>) {
        for (i in 0..<this.shape[0]) {
            this.data[i].remAssign(matrix)
        }
    }

    operator fun plus(matrix: Tensor3D<E>): Tensor3D<E> = this.clone().apply { this.plusAssign(matrix) }
    operator fun minus(matrix: Tensor3D<E>): Tensor3D<E> = this.clone().apply { this.minusAssign(matrix) }
    operator fun times(matrix: Tensor3D<E>): Tensor3D<E> = this.clone().apply { this.timesAssign(matrix) }
    operator fun div(matrix: Tensor3D<E>): Tensor3D<E> = this.clone().apply { this.divAssign(matrix) }
    operator fun rem(matrix: Tensor3D<E>): Tensor3D<E> = this.clone().apply { this.remAssign(matrix) }

    operator fun plusAssign(tensor3d: Tensor3D<E>) {
        requireSameDim1Size(tensor3d)
        for (i in 0..<this.shape[0]) {
            this.data[i].plusAssign(tensor3d.data[i])
        }
    }

    operator fun minusAssign(tensor3d: Tensor3D<E>) {
        requireSameDim1Size(tensor3d)
        for (i in 0..<this.shape[0]) {
            this.data[i].minusAssign(tensor3d.data[i])
        }
    }

    operator fun timesAssign(tensor3d: Tensor3D<E>) {
        requireSameDim1Size(tensor3d)
        for (i in 0..<this.shape[0]) {
            this.data[i].timesAssign(tensor3d.data[i])
        }
    }

    operator fun divAssign(tensor3d: Tensor3D<E>) {
        requireSameDim1Size(tensor3d)
        for (i in 0..<this.shape[0]) {
            this.data[i].divAssign(tensor3d.data[i])
        }
    }

    operator fun remAssign(tensor3d: Tensor3D<E>) {
        requireSameDim1Size(tensor3d)
        for (i in 0..<this.shape[0]) {
            this.data[i].remAssign(tensor3d.data[i])
        }
    }

    @Deprecated("COMING SOON!", level = DeprecationLevel.ERROR)
    fun transpose(dim0: Int, dim1: Int): Tensor3D<E> {
        COMING_SOON()
    }

    @JvmName("powOfInt")
    infix fun pow(scalar: Int): Tensor3D<E> = this.clone().apply { this.pow_(scalar) }
    infix fun <N: Number> pow(scalar: N): Tensor3D<E> = this.clone().apply { this.pow_(scalar) }
    @JvmName("powOfInt")
    infix fun pow(vector: Tensor1D<Int>): Tensor3D<E> = this.clone().apply { this.pow_(vector) }
    infix fun <N: Number> pow(vector: Tensor1D<N>): Tensor3D<E> = this.clone().apply { this.pow_(vector) }
    @JvmName("powOfInt")
    infix fun pow(matrix: Tensor2D<Int>): Tensor3D<E> = this.clone().apply { this.pow_(matrix) }
    infix fun <N: Number> pow(matrix: Tensor2D<N>): Tensor3D<E> = this.clone().apply { this.pow_(matrix) }
    @JvmName("powOfInt")
    infix fun pow(tensor3d: Tensor3D<Int>): Tensor3D<E> = this.clone().apply { this.pow_(tensor3d) }
    infix fun <N: Number> pow(tensor3d: Tensor3D<N>): Tensor3D<E> = this.clone().apply { this.pow_(tensor3d) }

    @JvmName("pow_OfInt")
    fun pow_(scalar: Int) {
        for (i in 0..<this.shape[0]) {
            this.data[i].pow_(scalar)
        }
    }

    fun <N: Number> pow_(scalar: N) {
        for (i in 0..<this.shape[0]) {
            this.data[i].pow_(scalar)
        }
    }

    @JvmName("pow_OfInt")
    fun pow_(vector: Tensor1D<Int>) {
        for (i in 0..<this.shape[0]) {
            this.data[i].pow_(vector)
        }
    }

    fun <N: Number> pow_(vector: Tensor1D<N>) {
        for (i in 0..<this.shape[0]) {
            this.data[i].pow_(vector)
        }
    }

    @JvmName("pow_OfInt")
    fun pow_(matrix: Tensor2D<Int>) {
        for (i in 0..<this.shape[0]) {
            this.data[i].pow_(matrix)
        }
    }

    fun <N: Number> pow_(matrix: Tensor2D<N>) {
        for (i in 0..<this.shape[0]) {
            this.data[i].pow_(matrix)
        }
    }

    @JvmName("pow_OfInt")
    fun pow_(tensor3d: Tensor3D<Int>) {
        requireSameDim1Size(tensor3d)
        for (i in 0..<this.shape[0]) {
            this.data[i].pow_(tensor3d.data[i])
        }
    }

    fun <N: Number> pow_(tensor3d: Tensor3D<N>) {
        requireSameDim1Size(tensor3d)
        for (i in 0..<this.shape[0]) {
            this.data[i].pow_(tensor3d.data[i])
        }
    }

    open fun toIntTensor(): Tensor3D<Int> = IntTensor3D(this.data.map { it.toIntTensor() }.toTypedArray())
    open fun toLongTensor(): Tensor3D<Long> = LongTensor3D(this.data.map { it.toLongTensor() }.toTypedArray())
    open fun toFloatTensor(): Tensor3D<Float> = FloatTensor3D(this.data.map { it.toFloatTensor() }.toTypedArray())
    open fun toDoubleTensor(): Tensor3D<Double> = DoubleTensor3D(this.data.map { it.toDoubleTensor() }.toTypedArray())

    open fun toBigIntegerTensor(): Tensor3D<BigInteger> =
        BigIntegerTensor3D(this.data.map { it.toBigIntegerTensor() }.toTypedArray())

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
    open fun toBigDecimalTensor(): Tensor3D<BigDecimal> =
        BigDecimalTensor3D(this.data.map { it.toBigDecimalTensor() }.toTypedArray())

    fun toIntTensor(factory: (E) -> Int): Tensor3D<Int> = IntTensor3D(this.data.map { it.toIntTensor(factory) }.toTypedArray())
    fun toLongTensor(factory: (E) -> Long): Tensor3D<Long> = LongTensor3D(this.data.map { it.toLongTensor(factory) }.toTypedArray())
    fun toFloatTensor(factory: (E) -> Float): Tensor3D<Float> = FloatTensor3D(this.data.map { it.toFloatTensor(factory) }.toTypedArray())
    fun toDoubleTensor(factory: (E) -> Double): Tensor3D<Double> = DoubleTensor3D(this.data.map { it.toDoubleTensor(factory) }.toTypedArray())

    fun toBigIntegerTensor(factory: (E) -> BigInteger): Tensor3D<BigInteger> =
        BigIntegerTensor3D(this.data.map { it.toBigIntegerTensor(factory) }.toTypedArray())

    fun toBigDecimalTensor(factory: (E) -> BigDecimal): Tensor3D<BigDecimal> =
        BigDecimalTensor3D(this.data.map { it.toBigDecimalTensor(factory) }.toTypedArray())

    abstract override fun clone(): Tensor3D<E> 

    override fun iterator(): Iterator<Tensor2D<E>> = this.data.iterator()

    override fun contentDeepToString(highestDim: Int): String {
        val result = StringBuilder()
        result.append('[')
        var isFirst = true
        for (element in this) {
            if (isFirst) isFirst = false
            else result.append(',').append('\n').append('\n').append(' ' * (6 + highestDim))
            result.append(element.contentDeepToString(highestDim))
        }
        result.append(']')
        return result.toString()
    }
}

class IntTensor3D(
    data: Array<out Tensor2D<Int>>
) : Tensor3D<Int>(data), IntAsDtype {
    override fun clone(): Tensor3D<Int> = this.toIntTensor()
}

class LongTensor3D(
    data: Array<out Tensor2D<Long>>
) : Tensor3D<Long>(data), LongAsDtype {
    override fun clone(): Tensor3D<Long> = this.toLongTensor()
}

class FloatTensor3D(
    data: Array<out Tensor2D<Float>>
) : Tensor3D<Float>(data), FloatAsDtype {
    override fun clone(): Tensor3D<Float> = this.toFloatTensor()
}

class DoubleTensor3D(
    data: Array<out Tensor2D<Double>>
) : Tensor3D<Double>(data), DoubleAsDtype {
    override fun clone(): Tensor3D<Double> = this.toDoubleTensor()
}

class BigIntegerTensor3D(
    data: Array<out Tensor2D<BigInteger>>
) : Tensor3D<BigInteger>(data), BigIntegerAsDtype {
    override fun clone(): Tensor3D<BigInteger> = this.toBigIntegerTensor()
}

class BigDecimalTensor3D(
    data: Array<out Tensor2D<BigDecimal>>
) : Tensor3D<BigDecimal>(data), BigDecimalAsDtype {
    override fun clone(): Tensor3D<BigDecimal> = this.toBigDecimalTensor()
}


@JvmName("tensorOf3DList_Tensor2D")
inline fun <reified E : Number> tensorOf(data: List<Tensor2D<out E>>): Tensor3D<E> = data.toTensor()
@JvmName("tensorOf3DList_List_List")
inline fun <reified E : Number> tensorOf(data: List<List<List<E>>>): Tensor3D<E> = data.toTensor()
@JvmName("tensorOf3DArray_Tensor2D")
inline fun <reified E : Number> tensorOf(data: Array<out Tensor2D<out E>>): Tensor3D<E> = data.toTensor()
@JvmName("tensorOf3DArray_Array_Array")
inline fun <reified E : Number> tensorOf(data: Array<out Array<out Array<out E>>>): Tensor3D<E> = data.toTensor()
@JvmName("tensorOf3DVararg_Tensor2D")
inline fun <reified E : Number> tensorOf(vararg data: Tensor2D<out E>): Tensor3D<E> = data.toTensor()

@JvmName("list_tensor2DToTensor3D")
inline fun <reified E : Number> List<Tensor2D<out E>>.toTensor(): Tensor3D<E> = Tensors.of3D(this.toTypedArray())
@JvmName("list_list_listToTensor3D")
inline fun <reified E : Number> List<List<List<E>>>.toTensor(): Tensor3D<E> =
    Tensors.of3D(this.map { _2d -> _2d.map { _1d -> _1d.toTensor() }.toTensor() }.toTypedArray())

inline fun <reified E : Number> Array<out Tensor2D<out E>>.toTensor(): Tensor3D<E> = Tensors.of3D(this)

inline fun <reified E : Number> Array<out Array<out Array<out E>>>.toTensor(): Tensor3D<E> =
    Tensors.of3D(this.map { _2d -> _2d.map { _1d -> _1d.toTensor() }.toTensor() }.toTypedArray())