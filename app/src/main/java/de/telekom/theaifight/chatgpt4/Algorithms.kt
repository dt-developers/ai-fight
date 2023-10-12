package de.telekom.theaifight.chatgpt4

fun fac(n: Int): Long {
    var result = 1L
    for (i in 2..n) {
        result *= i
    }
    return result
}

fun fibo(n: Int): Long {
    if (n <= 1) return n.toLong()
    var fib = arrayOf(0L, 1L)
    for (i in 2..n) {
        val sum = fib[0] + fib[1]
        fib[0] = fib[1]
        fib[1] = sum
    }
    return fib[1]
}