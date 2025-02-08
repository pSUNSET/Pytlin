package net.psunset.kwargs

import net.psunset.kwargs.collections.kwargsOf

class Test {
    /**
     * Just for test
     */
    @org.junit.jupiter.api.Test
    fun test() {
        val kwargs = kwargsOf()

        println(!!kwargs)
    }
}