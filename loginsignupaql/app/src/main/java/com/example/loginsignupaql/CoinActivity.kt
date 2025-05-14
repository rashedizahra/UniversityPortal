package com.example.loginsignupaql

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CoinActivity : AppCompatActivity() {

    private lateinit var unitsEditText: EditText
    private lateinit var priceEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var totalTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)

        unitsEditText = findViewById(R.id.unitsEditText)
        priceEditText = findViewById(R.id.priceEditText)
        calculateButton = findViewById(R.id.calculateButton)
        totalTextView = findViewById(R.id.totalTextView)

        calculateButton.setOnClickListener {
            calculateTuition()
        }
    }

    private fun calculateTuition() {
        val unitsString = unitsEditText.text.toString()
        val priceString = priceEditText.text.toString()

        if (unitsString.isEmpty() || priceString.isEmpty()) {
            return
        }

        val units = unitsString.toInt()
        val price = priceString.toDouble()

        val totalTuition = units * price

        totalTextView.text = "شهریه کل: $totalTuition"
    }
}