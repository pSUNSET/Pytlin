package net.psunset.pytlin.collections

import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.KClass


interface WithDtype {
    val dtype: KClass<out Number>
}

interface IntAsDtype : WithDtype {
    override val dtype: KClass<out Number>
        get() = Int::class
}

interface LongAsDtype : WithDtype {
    override val dtype: KClass<out Number>
        get() = Long::class
}

interface FloatAsDtype : WithDtype {
    override val dtype: KClass<out Number>
        get() = Float::class
}

interface DoubleAsDtype : WithDtype {
    override val dtype: KClass<out Number>
        get() = Double::class
}

interface BigIntegerAsDtype : WithDtype {
    override val dtype: KClass<out Number>
        get() = BigInteger::class
}

interface BigDecimalAsDtype : WithDtype {
    override val dtype: KClass<out Number>
        get() = BigDecimal::class
}