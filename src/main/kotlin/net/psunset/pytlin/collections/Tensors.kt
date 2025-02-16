package net.psunset.pytlin.collections

import java.math.BigDecimal
import java.math.BigInteger

object Tensors {

    inline fun <reified E : Number> of1D(data: List<E>): Tensor1D<E> {
        return of1D(data.toTypedArray())
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified E : Number> of1D(data: Array<out E>): Tensor1D<E> {
        return when (E::class) {
            Byte::class -> { // Byte precision is invalid here, using Int instead
                val newData = mutableListOf<Int>()
                for (element in data) {
                    newData.add(element.toInt())
                }
                return IntTensor1D(newData.toTypedArray()) as Tensor1D<E>
            }

            Short::class -> { // Short precision is invalid here, using Int instead
                val newData = mutableListOf<Int>()
                for (element in data) {
                    newData.add(element.toInt())
                }
                return IntTensor1D(newData.toTypedArray()) as Tensor1D<E>
            }

            Int::class -> IntTensor1D(data as Array<Int>) as Tensor1D<E>
            Long::class -> LongTensor1D(data as Array<Long>) as Tensor1D<E>
            Float::class -> FloatTensor1D(data as Array<Float>) as Tensor1D<E>
            Double::class -> DoubleTensor1D(data as Array<Double>) as Tensor1D<E>
            BigInteger::class -> BigIntegerTensor1D(data as Array<BigInteger>) as Tensor1D<E>
            BigDecimal::class -> BigDecimalTensor1D(data as Array<BigDecimal>) as Tensor1D<E>
            else -> { // Default to use Double
                val newData = mutableListOf<Double>()
                for (element in data) {
                    newData.add(element.toDouble())
                }
                return DoubleTensor1D(newData.toTypedArray()) as Tensor1D<E>
            }
        }
    }

    inline fun <reified E : Number> of2D(data: List<Tensor1D<E>>): Tensor2D<E> {
        return of2D(data.toTypedArray())
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified E : Number> of2D(data: Array<out Tensor1D<E>>): Tensor2D<E> {
        return when (E::class) {
            Byte::class -> { // Byte precision is invalid here, using Int instead
                val newData = mutableListOf<Tensor1D<Int>>()
                for (element in data) {
                    newData.add(element.toIntTensor())
                }
                return IntTensor2D(newData.toTypedArray()) as Tensor2D<E>
            }

            Short::class -> { // Short precision is invalid here, using Int instead
                val newData = mutableListOf<Tensor1D<Int>>()
                for (element in data) {
                    newData.add(element.toIntTensor())
                }
                return IntTensor2D(newData.toTypedArray()) as Tensor2D<E>
            }

            Int::class -> IntTensor2D(data as Array<Tensor1D<Int>>) as Tensor2D<E>
            Long::class -> LongTensor2D(data as Array<Tensor1D<Long>>) as Tensor2D<E>
            Float::class -> FloatTensor2D(data as Array<Tensor1D<Float>>) as Tensor2D<E>
            Double::class -> DoubleTensor2D(data as Array<Tensor1D<Double>>) as Tensor2D<E>
            BigInteger::class -> BigIntegerTensor2D(data as Array<Tensor1D<BigInteger>>) as Tensor2D<E>
            BigDecimal::class -> BigDecimalTensor2D(data as Array<Tensor1D<BigDecimal>>) as Tensor2D<E>
            else -> { // Default to use Double
                val newData = mutableListOf<Tensor1D<Double>>()
                for (element in data) {
                    newData.add(element.toDoubleTensor())
                }
                return DoubleTensor2D(newData.toTypedArray()) as Tensor2D<E>
            }
        }
    }

    fun space(vararg shape: Int) = TensorSpace(shape)
    fun space(shape: Array<out Int>) = TensorSpace(shape.toIntArray())
    fun space(shape: List<Int>) = TensorSpace(shape.toIntArray())

    /** Alias for [space] */
    fun shape(vararg shape: Int) = TensorSpace(shape)

    /** Alias for [space] */
    fun shape(shape: Array<out Int>) = TensorSpace(shape.toIntArray())

    /** Alias for [space] */
    fun shape(shape: List<Int>) = TensorSpace(shape.toIntArray())

    fun index(vararg dims: Int) = TensorIndex(dims)
    fun index(dims: Array<out Int>) = TensorIndex(dims.toIntArray())
    fun index(dims: List<Int>) = TensorIndex(dims.toIntArray())
}

abstract class Tensor<E : Number>(
    data: Array<*>,
    val space: TensorSpace
) : WithDtype {

    open val data = data
    val shape = this.space.shape
    val ndim = this.space.ndim
    val numel = this.space.numel

    abstract operator fun get(index: TensorIndex): E
    abstract operator fun set(index: TensorIndex, value: E)

    infix fun shapeEq(other: Tensor<*>): Boolean = this.space == other.space
    protected fun requireSameShape(other: Tensor<*>) =
        require(this shapeEq other) { "The tensors must have same shape" }

    protected fun requireSameFirstDimSize(other: Tensor<*>) =
        require(this.shape[0] == other.shape[0]) { "The tensors must have same shape" }

    fun isValidIndex(index: TensorIndex): Boolean = index in this.space

    fun to1DTensor(): Tensor1D<E> {
        require(this.ndim == 1) { "Only use this function when the ndim equals to 1." }
        return this as Tensor1D<E>
    }

    fun to2DTensor(): Tensor2D<E> {
        require(this.ndim == 2) { "Only use this function when the ndim equals to 1." }
        return this as Tensor2D<E>
    }

    abstract operator fun iterator(): Iterator<*>

    final override fun toString(): String =
        StringBuilder()
            .append("tensor(")
            .append(this.contentDeepToString(this.ndim))
            .append(", dtype=")
            .append(this.dtype.qualifiedName)
            .append(')')
            .toString()

    abstract fun contentDeepToString(highestDim: Int): String

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is Tensor<*>) return false
        return this.data.contentDeepEquals(other.data)
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

class TensorSpace(
    val shape: IntArray
) {

    init {
        require(shape.any { it > 0 }) { "The size of any tensor dimension should not be negative." }
    }

    val ndim = this.shape.size

    /**
     * total size of the tensor
     */
    val numel = this.shape.prod()

    operator fun get(dim: Int): Int = this.shape[dim]

    operator fun set(dim: Int, value: Int) {
        this.shape[dim] = value
    }

    operator fun iterator(): IntIterator = this.shape.iterator()

    override fun toString(): String = "shape($shape)"

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is TensorSpace) return false
        return this.shape.contentEquals(other.shape)
    }

    operator fun contains(index: TensorIndex): Boolean {
        if (index.ndim != this.ndim) return false
        for (i in 0..<this.ndim) {
            if (index[i] >= this[i]) return false
        }
        return true
    }

    operator fun contains(inner: TensorSpace): Boolean {
        val thisSize = this.shape.size
        val innerSize = inner.shape.size
        val truncated = this.shape.sliceArray((thisSize - innerSize)..<thisSize)
        return truncated.contentEquals(inner.shape)
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

class TensorIndex(
    val dims: IntArray
) {

    init {
        require(dims.any { it >= 0 }) { "The index of any dimension should not be negative." }
    }

    val ndim = this.dims.size

    operator fun get(dim: Int): Int = this.dims[dim]

    operator fun set(dim: Int, value: Int) {
        this.dims[dim] = value
    }

    operator fun iterator(): IntIterator = this.dims.iterator()

    override fun toString(): String = "index($dims)"

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is TensorIndex) return false
        return this.dims.contentEquals(other.dims)
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
