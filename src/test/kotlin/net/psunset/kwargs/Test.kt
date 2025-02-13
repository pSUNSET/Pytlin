package net.psunset.kwargs

import net.psunset.kwargs.lang.isFractionalPartZero

class Test {
    /**
     * Just for test
     */
    @org.junit.jupiter.api.Test
    fun test() {
        val i = 1.0
        println(!i.isFractionalPartZero())
    }
}
