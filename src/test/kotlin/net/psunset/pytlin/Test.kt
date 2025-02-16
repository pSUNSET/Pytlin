package net.psunset.pytlin

import net.psunset.pytlin.collections.tensorOf1D
import net.psunset.pytlin.collections.tensorOf2D

class Test {
    /**
     * Just for test
     */
    @org.junit.jupiter.api.Test
    fun test() {
        val t2 = tensorOf2D(tensorOf1D(1, 2, 3), tensorOf1D(2.5, 3.5, 4.5))
        println(t2)
    }
}
