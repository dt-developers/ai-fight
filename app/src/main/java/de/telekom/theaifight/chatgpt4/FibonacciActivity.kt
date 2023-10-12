package de.telekom.theaifight.chatgpt4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.telekom.theaifight.R
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity

class FibonacciActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bug)

        val inputN = findViewById<EditText>(R.id.editTextNumber)
        val btnCalculate = findViewById<Button>(R.id.buttonCalculate)
        val txtResult = findViewById<TextView>(R.id.textViewResult)

        btnCalculate.setOnClickListener {
            val n = inputN.text.toString().toIntOrNull()
            if (n != null) {
                val result = fibonacci(n)
                txtResult.text = "The ${n}th Fibonacci number is: $result"
            } else {
                txtResult.text = "Please enter a valid number."
            }
        }
    }

    private fun fibonacci(n: Int): Long {
        if (n <= 1) return n.toLong()
        var fib = arrayOf(0L, 1L)
        for (i in 2..n) {
            val sum = fib[0] + fib[1]
            fib[0] = fib[1]
            fib[1] = sum
        }
        return fib[1]
    }

}