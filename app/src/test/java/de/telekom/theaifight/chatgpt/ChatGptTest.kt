package de.telekom.theaifight.chatgpt

import org.junit.Assert
import org.junit.Test

class ChatGptTest {
    @Test
    fun factorial() {
        Assert.assertEquals(1, fac(0))
        Assert.assertEquals(1, fac(1))

        Assert.assertEquals(24, fac(4))
        Assert.assertEquals(7538058755741581312, fac(42))

        Assert.assertThrows(java.lang.IllegalArgumentException::class.java) { fac(-1) }
        Assert.assertThrows(java.lang.IllegalArgumentException::class.java) { fac(Int.MIN_VALUE) }
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

    @Test
    fun naive_fibonacci() {
        // for personal sanity
        Assert.assertEquals(0, naive_fibo(0))
        Assert.assertEquals(1, naive_fibo(1))
        Assert.assertEquals(1, naive_fibo(2))
        Assert.assertEquals(2, naive_fibo(3))
        Assert.assertEquals(3, naive_fibo(4))
        Assert.assertEquals(5, naive_fibo(5))
        Assert.assertEquals(8, naive_fibo(6))
        Assert.assertEquals(4181, naive_fibo(19))
    }

    private fun naive_fibo(n: Int): Int {
        return if (n <= 0) {
            0
        } else if (n == 1) {
            1
        } else {
            naive_fibo(n - 1) + naive_fibo(n - 2)
        }
    }

}