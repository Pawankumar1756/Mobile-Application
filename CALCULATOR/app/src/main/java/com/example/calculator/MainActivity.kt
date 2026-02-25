package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    private lateinit var screen: TextView
    private var input = ""
    private var firstValue = 0.0
    private var op = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screen = findViewById(R.id.displayBox)

        number(R.id.num0, "0")
        number(R.id.num1, "1")
        number(R.id.num2, "2")
        number(R.id.num3, "3")
        number(R.id.num4, "4")
        number(R.id.num5, "5")
        number(R.id.num6, "6")
        number(R.id.num7, "7")
        number(R.id.num8, "8")
        number(R.id.num9, "9")
        number(R.id.numDot, ".")

        operation(R.id.btnPlus, "+")
        operation(R.id.btnMinus, "-")
        operation(R.id.btnMultiply, "*")
        operation(R.id.btnDivide, "/")

        findViewById<Button>(R.id.btnEquals).setOnClickListener { result() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { reset() }

        scientific(R.id.btnSin) { sin(Math.toRadians(it)) }
        scientific(R.id.btnCos) { cos(Math.toRadians(it)) }
        scientific(R.id.btnTan) { tan(Math.toRadians(it)) }
        scientific(R.id.btnLog) { log10(it) }
        scientific(R.id.btnRoot) { sqrt(it) }
    }

    private fun number(id: Int, value: String) {
        findViewById<Button>(id).setOnClickListener {
            input += value
            screen.text = input
        }
    }

    private fun operation(id: Int, symbol: String) {
        findViewById<Button>(id).setOnClickListener {
            firstValue = input.toDoubleOrNull() ?: 0.0
            op = symbol
            input = ""
        }
    }

    private fun result() {
        val secondValue = input.toDoubleOrNull() ?: 0.0
        val answer = when (op) {
            "+" -> firstValue + secondValue
            "-" -> firstValue - secondValue
            "*" -> firstValue * secondValue
            "/" -> firstValue / secondValue
            else -> secondValue
        }
        screen.text = answer.toString()
        input = answer.toString()
    }

    private fun scientific(id: Int, calc: (Double) -> Double) {
        findViewById<Button>(id).setOnClickListener {
            val value = input.toDoubleOrNull() ?: return@setOnClickListener
            val ans = calc(value)
            screen.text = ans.toString()
            input = ans.toString()
        }
    }

    private fun reset() {
        input = ""
        firstValue = 0.0
        op = ""
        screen.text = "0"
    }
}