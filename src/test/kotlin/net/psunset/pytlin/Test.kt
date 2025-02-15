package net.psunset.pytlin

import net.psunset.pytlin.collections.tensorOf1D
import java.math.BigDecimal
import java.text.DecimalFormat

class Test {
    /**
     * Just for test
     */
    @org.junit.jupiter.api.Test
    fun test() {
        val d = tensorOf1D(arrayOf(53.9, 854.3, 264.7, 964.4))
        val dd = tensorOf1D(arrayOf(85.5, 31.6, 953.2, 259.9))
        val l = d.toBigDecimalTensor()
        val ll = dd.toBigDecimalTensor()

        ll /= l
        ll /= l

        dd /= d
        dd /= d

        println(ll)
        println(ll[0])
        println(dd)
    }
}
