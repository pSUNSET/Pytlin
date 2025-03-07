/*
 For test
 */

package net.psunset.pytlin

import net.psunset.pytlin.collections.set

class Test {
    @org.junit.jupiter.api.Test
    fun test() {
        val a = mutableListOf(1,2,3,4,5,6,7,8,9,10)
        a[0..4] = arrayOf(0,0,0)
        println(a)
    }
}