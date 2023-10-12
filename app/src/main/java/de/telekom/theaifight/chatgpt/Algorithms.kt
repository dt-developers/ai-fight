package de.telekom.theaifight.chatgpt

fun fac(n: Int): Long {
    if (n < 0) {
        throw IllegalArgumentException("Input must be a non-negative integer.")
    }
    return if (n == 0 || n == 1) {
        1
    } else {
        n.toLong() * fac(n - 1)
    }
}

fun fibo(n: Int): Int {
    if (n <= 1) {
        return n
    }

    var prev = 0
    var current = 1

    for (i in 2 until n) {
        val next = prev + current
        prev = current
        current = next
    }

    return current
}