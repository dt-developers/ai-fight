package de.telekom.theaifight.studiobot

fun theBug() {
    val names = List(42) {
        "pete".takeUnless { Math.random() > 0.4 }
    }
    names.forEachIndexed { index, value ->
        val name = value as String?
        if (name != null) {
            println("$index: $name (${name.length})")
        }
    }
}

fun fac(n: Long): Long {
    if (n < 2) {
        return n
    }

    return n * fac(n - 1)
}

fun fibo(n: Int): Int {
    if (n < 2) {
        return n
    }

    return fibo(n - 1) + fibo(n - 2)
}

