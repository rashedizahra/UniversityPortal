package com.example.loginsignupaql

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScheduleActivity : AppCompatActivity() {
    private lateinit var dbHelper: databasehelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        dbHelper = databasehelper(this)

        // خواندن داده‌ها از دیتابیس
        val classes = dbHelper.getAllClasses() // متد جدید برای خواندن اطلاعات کلاس‌ها

        // اضافه کردن داده‌ها به جدول
        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)
        for (classInfo in classes) {
            val row = TableRow(this)
            row.addView(TextView(this).apply { text = classInfo.time }) // ساعت
            row.addView(TextView(this).apply { text = classInfo.day }) // روز
            row.addView(TextView(this).apply { text = classInfo.name }) // نام درس
            tableLayout.addView(row)
        }
    }

    data class ClassInfo(
        val name: String,
        val day: String,
        val time: String
    )
}