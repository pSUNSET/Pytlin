package net.psunset.pytlin

import net.psunset.pytlin.collections.mutableKwargsOf
import net.psunset.pytlin.collections.setDefault

class Test {
    /**
     * Just for test
     */
    @org.junit.jupiter.api.Test
    fun test() {
        val a = mutableKwargsOf("i" to 1, "v" to 5)
        println(a.setDefault("x", 10))
        println(a)
    }
}