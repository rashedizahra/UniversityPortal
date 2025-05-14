package com.example.loginsignupaql

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SelectActivity : AppCompatActivity() {
    private val selectedCourses = mutableListOf<Course>()
    private lateinit var dbHelper: databasehelper
    private lateinit var tableLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        dbHelper = databasehelper(this)
        tableLayout = findViewById(R.id.tableLayout)

        val etCourseName = findViewById<EditText>(R.id.etCourseName)
        val etCourseCode = findViewById<EditText>(R.id.etCourseCode)
        val btnAddCourse = findViewById<Button>(R.id.btnAddCourse)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnAddCourse.setOnClickListener {
            val courseId = etCourseCode.text.toString().toIntOrNull()
            val courseName = etCourseName.text.toString()

            if (courseId != null && courseName.isNotEmpty()) {
                val existingCourse = dbHelper.getCourseById(courseId)

                if (existingCourse != null && !isCourseConflict(existingCourse)) {
                    selectedCourses.add(existingCourse)
                    addCourseToTable(existingCourse)
                    etCourseName.text.clear()
                    etCourseCode.text.clear()
                } else {
                    Toast.makeText(this, "تداخل دروس وجود دارد یا درس نامعتبر است!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "لطفا نام و ID درس را وارد کنید", Toast.LENGTH_SHORT).show()
            }
        }

        btnSubmit.setOnClickListener {
            if (selectedCourses.isNotEmpty()) {
                selectedCourses.forEach { course ->
                    dbHelper.saveSelectedCourse(course)
                }
                Toast.makeText(this, "دروس با موفقیت ثبت شدند!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "هیچ درسی انتخاب نشده است!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isCourseConflict(newCourse: Course): Boolean {
        return selectedCourses.any {
            it.day == newCourse.day && it.time == newCourse.time
        }
    }

    private fun addCourseToTable(course: Course) {
        val row = TableRow(this)
        row.addView(TextView(this).apply { text = course.name })
        row.addView(TextView(this).apply { text = course.id.toString() })
        tableLayout.addView(row)
    }

    data class Course(
        val name: String,
        val id: Int,
        val day: String,
        val time: String
    )
}