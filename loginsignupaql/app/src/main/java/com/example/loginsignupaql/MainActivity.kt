package com.example.loginsignupaql


import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainvi)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //  imagebutton ها
        val imageButton1 = findViewById<ImageButton>(R.id.ImageButton1)
        val imageButton2 = findViewById<ImageButton>(R.id.ImageButton2)
        val imageButton3 = findViewById<ImageButton>(R.id.ImageButton3)
        val imageButton4 = findViewById<ImageButton>(R.id.ImageButton4)
        val imageButton5 = findViewById<ImageButton>(R.id.ImageButton5)
        val imageButton6 = findViewById<ImageButton>(R.id.ImageButton6)



        imageButton1.setOnClickListener {
            // ریدایرکت مورد نظر برای imagebutton 1
            val intent = Intent(this, DarsActivity::class.java)
            startActivity(intent)        }
        imageButton2.setOnClickListener {
            // ریدایرکت مورد نظر برای imagebutton 2
            val intent = Intent(this, SelectActivity::class.java)
            startActivity(intent)        }
        imageButton3.setOnClickListener {
            // ریدایرکت مورد نظر برای imagebutton 3
            val intent = Intent(this, CoinActivity::class.java)
            startActivity(intent)        }
        imageButton4.setOnClickListener {
            // ریدایرکت مورد نظر برای imagebutton 4
            val intent = Intent(this, ScheduleActivity::class.java)
            startActivity(intent)        }
        imageButton5.setOnClickListener {
            // ریدایرکت مورد نظر برای imagebutton 5
            val intent = Intent(this, MessageActivity::class.java)
            startActivity(intent)        }
        imageButton6.setOnClickListener {
            // ریدایرکت مورد نظر برای imagebutton 6
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)        }

    }
}