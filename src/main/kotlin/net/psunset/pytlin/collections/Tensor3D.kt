package net.psunset.pytlin.collections

import net.psunset.pytlin.errors.COMING_SOON

class Tensor3D {
    init {
        COMING_SOON()
    }
}

@JvmName("tensorOf2DList_Tensor")
inline fun <reified E : Number> tensorOf(data: List<Tensor2D<out E>>): Tensor2D<E> = COMING_SOON()
@JvmName("tensorOf2DArray_Tensor")
inline fun <reified E : Number> tensorOf(data: Array<out Tensor2D<out E>>): Tensor2D<E> = COMING_SOON()
@JvmName("tensorOf2DVararg_Tensor")
inline fun <reified E : Number> tensorOf(vararg data: Tensor2D<out E>): Tensor2D<E> = COMING_SOON()

inline fun <reified E : Number> List<Tensor2D<out E>>.toTensor(): Tensor2D<E> = COMING_SOON()

inline fun <reified E : Number> Array<out Tensor2D<out E>>.toTensor(): Tensor2D<E> = COMING_SOON()