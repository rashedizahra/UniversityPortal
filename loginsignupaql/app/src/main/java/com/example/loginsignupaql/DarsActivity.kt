package com.example.loginsignupaql

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DarsActivity : AppCompatActivity() {
    private lateinit var dbHelper: databasehelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dars)

        dbHelper = databasehelper(this)

        // خواندن داده‌ها از دیتابیس
        val courses = dbHelper.getAllCourses()

        // اضافه کردن داده‌ها به جدول
        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)
        for (course in courses) {
            val row = TableRow(this)
            row.addView(TextView(this).apply { text = course.description })
            row.addView(TextView(this).apply { text = course.instructor })
            row.addView(TextView(this).apply { text = course.credits.toString() })
            row.addView(TextView(this).apply { text = course.code })
            row.addView(TextView(this).apply { text = course.name })
            tableLayout.addView(row)
        }
    }

    data class Course(
        val name: String,
        val code: String,
        val credits: Int,
        val instructor: String,
        val description: String
    )
}