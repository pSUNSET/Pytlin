package net.psunset.pytlin

import net.psunset.pytlin.collections.tensorOf

class Test {
    /**
     * Just for test
     */
    @org.junit.jupiter.api.Test
    fun test() {
        val t2 = tensorOf(tensorOf(1, 2, 3), tensorOf(2.5, 3.5, 4.5))
        println(t2)
    }
}
