package net.psunset.pytlin.collections

import net.psunset.pytlin.util.COMING_SOON

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

    operator fun plus(scalar: E): Tensor3D<E> = this.apply { this.plusAssign(scalar) }
    operator fun minus(scalar: E): Tensor3D<E> = this.apply { this.minusAssign(scalar) }
    operator fun times(scalar: E): Tensor3D<E> = this.apply { this.timesAssign(scalar) }
    operator fun div(scalar: E): Tensor3D<E> = this.apply { this.divAssign(scalar) }
    operator fun rem(scalar: E): Tensor3D<E> = this.apply { this.remAssign(scalar) }

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


    operator fun plus(vector: Tensor1D<E>): Tensor3D<E> = this.apply { this.plusAssign(vector) }
    operator fun minus(vector: Tensor1D<E>): Tensor3D<E> = this.apply { this.minusAssign(vector) }
    operator fun times(vector: Tensor1D<E>): Tensor3D<E> = this.apply { this.timesAssign(vector) }
    operator fun div(vector: Tensor1D<E>): Tensor3D<E> = this.apply { this.divAssign(vector) }
    operator fun rem(vector: Tensor1D<E>): Tensor3D<E> = this.apply { this.remAssign(vector) }

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

    operator fun plus(matrix: Tensor2D<E>): Tensor3D<E> = this.apply { this.plusAssign(matrix) }
    operator fun minus(matrix: Tensor2D<E>): Tensor3D<E> = this.apply { this.minusAssign(matrix) }
    operator fun times(matrix: Tensor2D<E>): Tensor3D<E> = this.apply { this.timesAssign(matrix) }
    operator fun div(matrix: Tensor2D<E>): Tensor3D<E> = this.apply { this.divAssign(matrix) }
    operator fun rem(matrix: Tensor2D<E>): Tensor3D<E> = this.apply { this.remAssign(matrix) }


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

    operator fun plus(matrix: Tensor3D<E>): Tensor3D<E> = this.apply { this.plusAssign(matrix) }
    operator fun minus(matrix: Tensor3D<E>): Tensor3D<E> = this.apply { this.minusAssign(matrix) }
    operator fun times(matrix: Tensor3D<E>): Tensor3D<E> = this.apply { this.timesAssign(matrix) }
    operator fun div(matrix: Tensor3D<E>): Tensor3D<E> = this.apply { this.divAssign(matrix) }
    operator fun rem(matrix: Tensor3D<E>): Tensor3D<E> = this.apply { this.remAssign(matrix) }

    operator fun plusAssign(tensor3d: Tensor3D<E>) {
        requireSameFirstDimSize(tensor3d)
        for (i in 0..<this.shape[0]) {
            this.data[i].plusAssign(tensor3d.data[i])
        }
    }

    operator fun minusAssign(tensor3d: Tensor3D<E>) {
        requireSameFirstDimSize(tensor3d)
        for (i in 0..<this.shape[0]) {
            this.data[i].minusAssign(tensor3d.data[i])
        }
    }

    operator fun timesAssign(tensor3d: Tensor3D<E>) {
        requireSameFirstDimSize(tensor3d)
        for (i in 0..<this.shape[0]) {
            this.data[i].timesAssign(tensor3d.data[i])
        }
    }

    operator fun divAssign(tensor3d: Tensor3D<E>) {
        requireSameFirstDimSize(tensor3d)
        for (i in 0..<this.shape[0]) {
            this.data[i].divAssign(tensor3d.data[i])
        }
    }

    operator fun remAssign(tensor3d: Tensor3D<E>) {
        requireSameFirstDimSize(tensor3d)
        for (i in 0..<this.shape[0]) {
            this.data[i].remAssign(tensor3d.data[i])
        }
    }

    fun transpose(dim0: Int, dim1: Int): Tensor3D<E> {
        require(dim0 != dim1 && dim0 in 0..<this.ndim && dim1 in 0..<this.ndim) {
            "Two dim provided must be different and within the ndim of this tensor."
        }

        val dataArr = this.data.map { _2d -> _2d.data.map { _1d -> _1d.data } }

        val shapeT = this.shape.mapIndexed { i, v -> if (i == dim0) dim1 else if (i == dim1) dim0 else v }

        val thisT = MutableList<MutableList<MutableList<E>>>(shapeT[0]) {
            MutableList(shapeT[1]) { ArrayList(shapeT[2]) }
        }

        for (i in 0..<shapeT[0]) {
            for (ii in 0..<shapeT[1]) {
                for (iii in 0..<shapeT[2]) {
                    TODO()
                }
            }
        }

        return COMING_SOON()
    }
}

@JvmName("tensorOf3DList_Tensor2D")
inline fun <reified E : Number> tensorOf(data: List<Tensor2D<out E>>): Tensor3D<E> = COMING_SOON()

@JvmName("tensorOf3DArray_Tensor2D")
inline fun <reified E : Number> tensorOf(data: Array<out Tensor2D<out E>>): Tensor3D<E> = COMING_SOON()

@JvmName("tensorOf3DVararg_Tensor2D")
inline fun <reified E : Number> tensorOf(vararg data: Tensor2D<out E>): Tensor3D<E> = COMING_SOON()

inline fun <reified E : Number> List<Tensor2D<out E>>.toTensor(): Tensor3D<E> = COMING_SOON()

inline fun <reified E : Number> Array<out Tensor2D<out E>>.toTensor(): Tensor3D<E> = COMING_SOON()