package de.telekom.theaifight.chatgpt4

import org.junit.Assert
import org.junit.Test

class ChatGpt4Test {
    @Test
    fun factorial() {
        Assert.assertEquals(1, fac(1))
        Assert.assertEquals(1, fac(0))

        Assert.assertEquals(24, fac(4))
        Assert.assertEquals(7538058755741581312, fac(42))

        Assert.assertEquals(1, fac(-1))
        Assert.assertEquals(1, fac(Int.MIN_VALUE))
    }

    @Test
    fun fibonacci() {
        Assert.assertEquals(0, fibo(0))
        Assert.assertEquals(1, fibo(1))
        Assert.assertEquals(1, fibo(2))
        Assert.assertEquals(2, fibo(3))
        Assert.assertEquals(3, fibo(4))
        Assert.assertEquals(5, fibo(5))
        Assert.assertEquals(8, fibo(6))
        Assert.assertEquals(4181, fibo(19))
    }
}