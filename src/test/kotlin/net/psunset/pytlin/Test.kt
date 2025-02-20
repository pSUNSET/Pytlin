package net.psunset.pytlin

import net.psunset.pytlin.collections.tensorOf

class Test {
    /**
     * Just for test
     */
    @org.junit.jupiter.api.Test
    fun test() {
        val t = tensorOf(
            tensorOf(
                tensorOf(1, 2),
                tensorOf(3, 4)
            ),
            tensorOf(
                tensorOf(5, 6),
                tensorOf(7, 8)
            )
        )
        val u = tensorOf(
            tensorOf(1, 2),
            tensorOf(3, 4)
        )

        println(t + u)
        println(t - u)
        println(t * u)
        println(t / u)
        println(t % u)
        println(t pow u)

        val a = tensorOf(1, 2, 3)
        val b = tensorOf(4, 5, 6)

        println(a dot b)
        println(a cross b)
    }
}