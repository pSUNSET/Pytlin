package net.psunset.pytlin.collections

import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.KClass

/**
 * A class for indicating what type of data a tensor storaging.
 */
internal interface WithDtype {
    val dtype: KClass<out Number>

    interface DInt : WithDtype {
        override val dtype: KClass<out Number>
            get() = Int::class
    }

    interface DLong : WithDtype {
        override val dtype: KClass<out Number>
            get() = Long::class
    }

    interface DFloat : WithDtype {
        override val dtype: KClass<out Number>
            get() = Float::class
    }

    interface DDouble : WithDtype {
        override val dtype: KClass<out Number>
            get() = Double::class
    }

    interface DBigInteger : WithDtype {
        override val dtype: KClass<out Number>
            get() = BigInteger::class
    }

    interface DBigDecimal : WithDtype {
        override val dtype: KClass<out Number>
            get() = BigDecimal::class
    }
}