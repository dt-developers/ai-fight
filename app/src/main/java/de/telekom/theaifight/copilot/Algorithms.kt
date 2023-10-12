package de.telekom.theaifight.copilot

fun theBug() {
    val names  = List(42) {
        "pete".takeIf { Math.random() > 0.4 }
    }
    names.forEachIndexed { index, value ->
        val name = value as String
        println("$index: $name (${name.length})")
    }
}

fun fac(n: Int): Long{
    var result = 1L
    for (i in 1..n) {
        result *= i
    }

    return result
}

fun fibo(n: Int): Long {
    if (n == 0) return 0
    if (n == 1) return 1

    var a = 0L
    var b = 1L
    var c = 0L

    for (i in 2..n) {
        c = a + b
        a = b
        b = c
    }

    return c
}

