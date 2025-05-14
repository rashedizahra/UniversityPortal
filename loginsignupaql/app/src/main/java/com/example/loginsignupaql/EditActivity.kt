package com.example.loginsignupaql

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginsignupaql.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var databasehelper: databasehelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databasehelper = databasehelper(this)

        // دریافت رکورد از دیتابیس
        val student = databasehelper.getLastStudent()
        if (student != null) {
            binding.etName.setText(student.firstName)
            binding.etLastname.setText(student.lastName)
            binding.etEmail.setText(student.email)
            binding.etAddress.setText(student.address)
            binding.etPhone.setText(student.phone)
            binding.etGpa.setText(student.gpa)
            binding.etStudentId.setText(student.id.toString())
        } else {
            Toast.makeText(this, "No student found", Toast.LENGTH_SHORT).show()
        }

// دکمه ذخیره
        binding.btnSave.setOnClickListener {
            val firstName = binding.etName.text.toString()
            val lastName = binding.etLastname.text.toString()
            val address = binding.etAddress.text.toString()
            val gpa = binding.etGpa.text.toString()
            val email = binding.etEmail.text.toString()
            val phone = binding.etPhone.text.toString()
            val studentId = binding.etStudentId.text.toString().toIntOrNull() ?: 0

            // بررسی اینکه شماره دانشجویی معتبر باشد
            if (studentId > 0) {
                saveStudent(firstName, lastName, email, address, phone, gpa, studentId)
            } else {
                Toast.makeText(this, "Invalid student ID", Toast.LENGTH_SHORT).show()
            }
        }

        // دکمه ویرایش
        binding.btnEdit.setOnClickListener {
            val student = databasehelper.getLastStudent()
            if (student != null) {
                val firstName = binding.etName.text.toString()
                val lastName = binding.etLastname.text.toString()
                val address = binding.etAddress.text.toString()
                val gpa = binding.etGpa.text.toString()
                val email = binding.etEmail.text.toString()
                val phone = binding.etPhone.text.toString()
                updateStudent(student.id, firstName, lastName, email, address, phone, gpa)
            } else {
                Toast.makeText(this, "No student found to update", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun saveStudent(
        firstName: String,
        lastName: String,
        email: String,
        address: String,
        phone: String,
        gpa: String,
        studentId: Int
    ) {
        val insertedRowId = databasehelper.insertStudent(firstName, lastName, email, address, phone, gpa, studentId)
        if (insertedRowId != -1L) {
            Toast.makeText(this, "با موفقیت ایجاد شده", Toast.LENGTH_SHORT).show()
            finish() // برای برگشت به صفحه قبلی
        } else {
            Toast.makeText(this, "خطا در ایجاد", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateStudent(
        studentId: Int,
        firstName: String,
        lastName: String,
        email: String,
        address: String,
        phone: String,
        gpa: String
    ) {
        val updatedRowId = databasehelper.updateStudent(studentId, firstName, lastName, email, address, phone, gpa)
        if (updatedRowId > 0) {
            Toast.makeText(this, "با موفقیت ویرایش شد", Toast.LENGTH_SHORT).show()
            finish() // برای برگشت به صفحه قبلی

        } else {
            Toast.makeText(this, "خطا در ویرایش", Toast.LENGTH_SHORT).show()
        }
    }
}