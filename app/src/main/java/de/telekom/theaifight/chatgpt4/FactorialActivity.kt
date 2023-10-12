package de.telekom.theaifight.chatgpt4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import de.telekom.theaifight.R

class FactorialActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bug)

        val inputNumber = findViewById<EditText>(R.id.editTextNumber)
        val calculateButton = findViewById<Button>(R.id.buttonCalculate)
        val resultText = findViewById<TextView>(R.id.textViewResult)

        calculateButton.setOnClickListener {
            val number = inputNumber.text.toString().toIntOrNull()
            if (number != null) {
                val result = factorialIterative(number)
                resultText.text = "Factorial of $number is $result"
            } else {
                resultText.text = "Please enter a valid number"
            }
        }
    }

    private fun factorialIterative(n: Int): Long {
        var result = 1L
        for (i in 2..n) {
            result *= i
        }
        return result
    }
}
