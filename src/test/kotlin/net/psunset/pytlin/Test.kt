package net.psunset.pytlin

import net.psunset.pytlin.collections.deepFlatten
import net.psunset.pytlin.collections.doubleNdarray1DOf

class Test {
    /**
     * Just for test
     */
    @org.junit.jupiter.api.Test
    fun test() {
        val l = doubleNdarray1DOf(listOf(7.0, 3.0, 2.64, 69.24))
        l += 3.0
        l *= 2.9
        println(l)
    }
}
