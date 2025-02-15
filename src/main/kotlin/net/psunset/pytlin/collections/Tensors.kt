package net.psunset.pytlin.collections

import net.psunset.pytlin.lang.times
import java.math.BigDecimal
import java.math.BigInteger
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*


object Tensors {

    inline fun <reified E : Number> of1D(data: List<E>): Tensor1D<E> {
        return tensorOf1D(data.toTypedArray())
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified E : Number> of1D(data: Array<E>): Tensor1D<E> {
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
    val ndim = this.space.ndim
    val numel = this.space.numel
    val nelement = this.space.nelement

    init {
        require(data.size == this.numel) { "The data is invalid because of its wrong size." }
    }

    abstract operator fun get(index: TensorIndex): E
    abstract operator fun set(index: TensorIndex, value: E)

    infix fun shapeEq(other: Tensor<*>): Boolean = this.space == other.space
    protected fun requireSameShape(other: Tensor<*>) =
        require(this shapeEq other) { "The tensors must have same shape" }

    fun isValidIndex(index: TensorIndex): Boolean = index in this.space

    fun to1DTensor(): Tensor1D<E> {
        require(this.ndim == 1) { "Only use this function when the ndim equals to 1." }
        return this as Tensor1D<E>
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("tensor(")
        sb.append(this.data.formattedToString())
        sb.append(", dtype=").append(this.dtype.qualifiedName)
        sb.append(')')
        return sb.toString()
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    private fun Array<*>.formattedToString(spaceCounter: Int = 6): String {
        val result = StringBuilder()

        // Not first
        if (spaceCounter > 6) {
            result.append(',').append('\n').append(' ' * spaceCounter)
        }

        result.append('[')
        var isFirst: Boolean = true
        for (element in this) {
            if (!isFirst) {
                result.append(',').append(' ')
            }
            isFirst = false

            when (element) {
                is Array<*> -> result.append(element.formattedToString(spaceCounter + 1))
                is ByteArray -> result.append(element.toTypedArray().formattedToString(spaceCounter + 1))
                is ShortArray -> result.append(element.toTypedArray().formattedToString(spaceCounter + 1))
                is IntArray -> result.append(element.toTypedArray().formattedToString(spaceCounter + 1))
                is LongArray -> result.append(element.toTypedArray().formattedToString(spaceCounter + 1))
                is FloatArray -> result.append(element.toTypedArray().formattedToString(spaceCounter + 1))
                is DoubleArray -> result.append(element.toTypedArray().formattedToString(spaceCounter + 1))
                is CharArray -> result.append(element.toTypedArray().formattedToString(spaceCounter + 1))
                is BooleanArray -> result.append(element.toTypedArray().formattedToString(spaceCounter + 1))

                is UByteArray -> result.append(element.toTypedArray().formattedToString(spaceCounter + 1))
                is UShortArray -> result.append(element.toTypedArray().formattedToString(spaceCounter + 1))
                is UIntArray -> result.append(element.toTypedArray().formattedToString(spaceCounter + 1))
                is ULongArray -> result.append(element.toTypedArray().formattedToString(spaceCounter + 1))

                else -> {
                    val formatter = DecimalFormat("0.0000E0")
                    formatter.minimumFractionDigits = 4
                    formatter.maximumFractionDigits = 4
                    val formatted = formatter.format(element).replace("E-", "e-").replace("E", "e+")
                    result.append(formatted)
                }
            }
        }
        result.append(']')
        return result.toString()
    }

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

    /**
     * alias for [numel]
     */
    val nelement = this.numel

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

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

class TensorIndex(
    val dims: IntArray
) {

    init {
        require(dims.any { it > 0 }) { "The index of any dimension should not be negative." }
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
