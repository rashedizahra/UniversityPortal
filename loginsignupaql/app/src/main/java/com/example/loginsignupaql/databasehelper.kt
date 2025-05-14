package com.example.loginsignupaql

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class databasehelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        fun readStudent(): Int {
            TODO("Not yet implemented")
        }



        private const val DATABASE_NAME = "UniversityDatabase.db"
        private const val DATABASE_VERSION = 1

        // Tables
        private const val TABLE_NAME = "data"
        private const val TABLE_STUDENT = "student_info"
        const val TABLE_COURSES = "courses"
        private const val TABLE_CLASS_GROUPS = "class_groups"
        private const val TABLE_TUITION = "tuition"

        // login & signup Table Columns
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"

        // Student Info Table Columns
        private const val COLUMN_STUDENT_ID = "student_id"
        private const val COLUMN_FIRST_NAME = "first_name"
        private const val COLUMN_LAST_NAME = "last_name"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_ADDRESS = "address"
        private const val COLUMN_PHONE = "phone"
        private const val COLUMN_GPA = "gpa"

        // Courses Table Columns
        const val COLUMN_COURSE_ID = "course_id"
        const val COLUMN_COURSE_NAME = "course_name"
        const val COLUMN_CREDITS = "credits"
        const val COLUMN_PREREQUISITE = "prerequisite"
        private const val COLUMN_COREQUISITE = "corequisite"
        const val COLUMN_CLASS_DAY = "class_day"
        const val COLUMN_CLASS_TIME = "class_time"
        private const val COLUMN_EXAM_DATE = "exam_date"
        private const val COLUMN_EXAM_TIME = "exam_time"
        private const val COLUMN_CLASS_LOCATION = "ostad"
        private const val COLUMN_CAPACITY = "capacity"
        private const val COLUMN_TUITION_FEE = "tuition_fee"

        // Class Groups Table Columns
        private const val COLUMN_GROUP_ID = "group_id"
        private const val COLUMN_INSTRUCTOR = "instructor"
        private const val COLUMN_STUDENTS_COUNT = "students_count"

        // Tuition Table Columns
        private const val COLUMN_PAYMENT_ID = "payment_id"
        private const val COLUMN_STUDENT_NUMBER = "student_number"
        private const val COLUMN_AMOUNT_PAID = "amount_paid"
        private const val COLUMN_PAYMENT_DATE = "payment_date"
        private const val COLUMN_PAYMENT_STATUS = "payment_status"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        createTableQuerys(db)
        createStudentInfoTable(db)
        createCoursesTable(db)
        createClassGroupsTable(db)
        createTuitionTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_STUDENT")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_COURSES")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_CLASS_GROUPS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_TUITION")
        onCreate(db)
    }

    private fun createTableQuerys(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USERNAME TEXT, " +
                "$COLUMN_PASSWORD TEXT)")
        db?.execSQL(createTableQuery)
    }

    private fun createStudentInfoTable(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_STUDENT (" +
                "$COLUMN_STUDENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_FIRST_NAME TEXT, " +
                "$COLUMN_LAST_NAME TEXT, " +
                "$COLUMN_EMAIL TEXT, " +
                "$COLUMN_ADDRESS TEXT, " +
                "$COLUMN_PHONE TEXT, " +
                "$COLUMN_GPA FLOAT)")
        db?.execSQL(createTableQuery)
    }

    private fun createCoursesTable(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_COURSES (" +
                "$COLUMN_COURSE_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_COURSE_NAME TEXT, " +
                "$COLUMN_CREDITS INTEGER, " +
                "$COLUMN_PREREQUISITE INTEGER, " +
                "$COLUMN_COREQUISITE INTEGER, " +
                "$COLUMN_CLASS_DAY TEXT, " +
                "$COLUMN_CLASS_TIME TEXT, " +
                "$COLUMN_EXAM_DATE TEXT, " +
                "$COLUMN_EXAM_TIME TEXT, " +
                "$COLUMN_CLASS_LOCATION TEXT, " +
                "$COLUMN_CAPACITY INTEGER, " +
                "$COLUMN_TUITION_FEE INTEGER)")
        db?.execSQL(createTableQuery)
    }

    private fun createClassGroupsTable(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_CLASS_GROUPS (" +
                "$COLUMN_COURSE_ID INTEGER, " +
                "$COLUMN_GROUP_ID INTEGER, " +
                "$COLUMN_INSTRUCTOR TEXT, " +
                "$COLUMN_STUDENTS_COUNT INTEGER)")
        db?.execSQL(createTableQuery)
    }

    private fun createTuitionTable(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_TUITION (" +
                "$COLUMN_PAYMENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_STUDENT_NUMBER INTEGER, " +
                "$COLUMN_AMOUNT_PAID INTEGER, " +
                "$COLUMN_PAYMENT_DATE TEXT, " +
                "$COLUMN_PAYMENT_STATUS TEXT)")
        db?.execSQL(createTableQuery)
    }

    fun insertStudent(
        firstName: String,
        lastName: String,
        email: String,
        address: String,
        phone: String,
        gpa: String,
        studentId: Int
    ): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_FIRST_NAME, firstName)
            put(COLUMN_LAST_NAME, lastName)
            put(COLUMN_EMAIL, email)
            put(COLUMN_ADDRESS, address)
            put(COLUMN_PHONE, phone)
            put(COLUMN_GPA, gpa)
            put(COLUMN_STUDENT_ID, studentId)
        }
        return db.insert(TABLE_STUDENT, null, values)
    }

    //login & signup
    fun insertUser(username: String,password: String): Long{
        val value = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }
        val db = writableDatabase
        return db.insert(TABLE_NAME, null, value)
    }

    fun readUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query(TABLE_NAME, null, selection,selectionArgs,null,null,null)

        val userExits = cursor.count > 0
        cursor.close()
        return userExits
    }

    data class Student(
        val id: Int,
        val firstName: String,
        val lastName: String,
        val email: String,
        val address: String,
        val phone: String,
        val gpa: String
    )

    @SuppressLint("Range")
    fun getLastStudent(): Student? {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_STUDENT,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_STUDENT_ID DESC", // ترتیب نزولی بر اساس student_id
            "1" // فقط یک رکورد
        )

        return if (cursor.moveToFirst()) {
            val student = Student(
                id = cursor.getInt(cursor.getColumnIndex(COLUMN_STUDENT_ID)),
                firstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)),
                lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)),
                email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
                address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)),
                phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)),
                gpa = cursor.getString(cursor.getColumnIndex(COLUMN_GPA))
            )
            cursor.close()
            student
        } else {
            cursor.close()
            null
        }
    }


    fun readStudent(studentNumber: Int): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_STUDENT_ID = ?"
        val selectionArgs = arrayOf(studentNumber.toString())
        val cursor = db.query(TABLE_STUDENT, null, selection, selectionArgs, null, null, null)

        val studentExists = cursor.count > 0
        cursor.close()
        return studentExists
    }


    fun updateStudent(
        studentId: Int,
        firstName: String,
        lastName: String,
        email: String,
        address: String,
        phone: String,
        gpa: String
    ): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_FIRST_NAME, firstName)
            put(COLUMN_LAST_NAME, lastName)
            put(COLUMN_EMAIL, email)
            put(COLUMN_ADDRESS, address)
            put(COLUMN_PHONE, phone)
            put(COLUMN_GPA, gpa)
        }
        return db.update(
            TABLE_STUDENT,
            values,
            "$COLUMN_STUDENT_ID = ?",
            arrayOf(studentId.toString())
        )
    }



    fun insertCourse(
        courseName: String,
        credits: Int,
        prerequisite: Int,
        corequisite: Int,
        classDay: String,
        classTime: String,
        examDate: String,
        examTime: String,
        ostad
        : String,
        capacity: Int,
        tuitionFee: Int
    ): Long {
        val values = ContentValues().apply {
            put(COLUMN_COURSE_NAME, courseName)
            put(COLUMN_CREDITS, credits)
            put(COLUMN_PREREQUISITE, prerequisite)
            put(COLUMN_COREQUISITE, corequisite)
            put(COLUMN_CLASS_DAY, classDay)
            put(COLUMN_CLASS_TIME, classTime)
            put(COLUMN_EXAM_DATE, examDate)
            put(COLUMN_EXAM_TIME, examTime)
            put(COLUMN_CLASS_LOCATION, ostad)
            put(COLUMN_CAPACITY, capacity)
            put(COLUMN_TUITION_FEE, tuitionFee)
        }
        val db = writableDatabase
        return db.insert(TABLE_COURSES, null, values)
    }


    @SuppressLint("Range")
    fun getAllCourses(): List<DarsActivity.Course> {
        val courses = mutableListOf<DarsActivity.Course>()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_COURSES", null)

        if (cursor.moveToFirst()) {
            do {
                val course = DarsActivity.Course(
                    name = cursor.getString(cursor.getColumnIndex(COLUMN_COURSE_NAME)),
                    code = cursor.getString(cursor.getColumnIndex(COLUMN_COURSE_ID)),
                    credits = cursor.getInt(cursor.getColumnIndex(COLUMN_CREDITS)),
                    description = cursor.getString(cursor.getColumnIndex(COLUMN_CLASS_DAY)), // Adjust column name accordingly
                instructor = cursor.getString(cursor.getColumnIndex(COLUMN_CLASS_LOCATION)) // Adjust column name accordingly
                )
                courses.add(course)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return courses
    }



    @SuppressLint("Range")
    fun getCourseById(courseId: Int): SelectActivity.Course? {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_COURSES, null, "$COLUMN_COURSE_ID = ?", arrayOf(courseId.toString()), null, null, null)
        return if (cursor.moveToFirst()) {
            SelectActivity.Course(
                cursor.getString(cursor.getColumnIndex(COLUMN_COURSE_NAME)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_COURSE_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_CLASS_DAY)),
                cursor.getString(cursor.getColumnIndex(COLUMN_CLASS_TIME))
            )
        } else {
            null
        }.also { cursor.close() }
    }


    // متد برای ذخیره‌سازی درس انتخاب شده
    fun saveSelectedCourse(course: SelectActivity.Course) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_GROUP_ID, course.name)
            put(COLUMN_INSTRUCTOR, course.day)
            put(COLUMN_STUDENTS_COUNT, course.time)
        }
        db.insert(TABLE_CLASS_GROUPS, null, values)
    }
    @SuppressLint("Range")
    fun getAllClasses(): List<ScheduleActivity.ClassInfo> {
        val classes = mutableListOf<ScheduleActivity.ClassInfo>()
        val db = this.readableDatabase
        val cursor = db.query(TABLE_CLASS_GROUPS, null, null, null, null, null, null)

        while (cursor.moveToNext()) {
            val classInfo = ScheduleActivity.ClassInfo(
                name = cursor.getString(cursor.getColumnIndex(COLUMN_GROUP_ID)),
                day = cursor.getString(cursor.getColumnIndex(COLUMN_INSTRUCTOR)),
                time = cursor.getString(cursor.getColumnIndex(COLUMN_STUDENTS_COUNT))
            )
            classes.add(classInfo)
        }
        cursor.close()
        return classes
    }


    fun readCourse(courseId: Int): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_COURSE_ID = ?"
        val selectionArgs = arrayOf(courseId.toString())
        val cursor = db.query(TABLE_COURSES, null, selection, selectionArgs, null, null, null)

        val courseExists = cursor.count > 0
        cursor.close()
        return courseExists
    }

    fun updateCourse(
        courseId: Int,
        courseName: String,
        credits: Int,
        prerequisite: Int,
        corequisite: Int,
        classDay: String,
        classTime: String,
        examDate: String,
        examTime: String,
        ostad: String,
        capacity: Int,
        tuitionFee: Int
    ): Int {
        val values = ContentValues().apply {
            put(COLUMN_COURSE_NAME, courseName)
            put(COLUMN_CREDITS, credits)
            put(COLUMN_PREREQUISITE, prerequisite)
            put(COLUMN_COREQUISITE, corequisite)
            put(COLUMN_CLASS_DAY, classDay)
            put(COLUMN_CLASS_TIME, classTime)
            put(COLUMN_EXAM_DATE, examDate)
            put(COLUMN_EXAM_TIME, examTime)
            put(COLUMN_CLASS_LOCATION, ostad)
            put(COLUMN_CAPACITY, capacity)
            put(COLUMN_TUITION_FEE, tuitionFee)
        }
        val db = writableDatabase
        val selection = "$COLUMN_COURSE_ID = ?"
        val selectionArgs = arrayOf(courseId.toString())
        return db.update(TABLE_COURSES, values, selection, selectionArgs)
    }

    fun insertClassGroup(
        courseId: Int,
        groupId: Int,
        instructor: String,
        studentsCount: Int
    ): Long {
        val values = ContentValues().apply {
            put(COLUMN_COURSE_ID, courseId)
            put(COLUMN_GROUP_ID, groupId)
            put(COLUMN_INSTRUCTOR, instructor)
            put(COLUMN_STUDENTS_COUNT, studentsCount)
        }
        val db = writableDatabase
        return db.insert(TABLE_CLASS_GROUPS, null, values)
    }

    fun readClassGroup(courseId: Int, groupId: Int): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_COURSE_ID = ? AND $COLUMN_GROUP_ID = ?"
        val selectionArgs = arrayOf(courseId.toString(), groupId.toString())
        val cursor = db.query(TABLE_CLASS_GROUPS, null, selection, selectionArgs, null, null, null)

        val groupExists = cursor.count > 0
        cursor.close()
        return groupExists
    }

    fun updateClassGroup(
        courseId: Int,
        groupId: Int,
        instructor: String,
        studentsCount: Int
    ): Int {
        val values = ContentValues().apply {
            put(COLUMN_INSTRUCTOR, instructor)
            put(COLUMN_STUDENTS_COUNT, studentsCount)
        }
        val db = writableDatabase
        val selection = "$COLUMN_COURSE_ID = ? AND $COLUMN_GROUP_ID = ?"
        val selectionArgs = arrayOf(courseId.toString(), groupId.toString())
        return db.update(TABLE_CLASS_GROUPS, values, selection, selectionArgs)
    }

    fun insertTuition(
        studentNumber: Int,
        amountPaid: Int,
        paymentDate: String,
        paymentStatus: String
    ): Long {
        val values = ContentValues().apply {
            put(COLUMN_STUDENT_NUMBER, studentNumber)
            put(COLUMN_AMOUNT_PAID, amountPaid)
            put(COLUMN_PAYMENT_DATE, paymentDate)
            put(COLUMN_PAYMENT_STATUS, paymentStatus)
        }
        val db = writableDatabase
        return db.insert(TABLE_TUITION, null, values)
    }

    fun readTuition(paymentId: Int): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_PAYMENT_ID = ?"
        val selectionArgs = arrayOf(paymentId.toString())
        val cursor = db.query(TABLE_TUITION, null, selection, selectionArgs, null, null, null)

        val paymentExists = cursor.count > 0
        cursor.close()
        return paymentExists
    }

    fun updateTuition(
        paymentId: Int,
        amountPaid: Int,
        paymentDate: String,
        paymentStatus: String
    ): Int {
        val values = ContentValues().apply {
            put(COLUMN_AMOUNT_PAID, amountPaid)
            put(COLUMN_PAYMENT_DATE, paymentDate)
            put(COLUMN_PAYMENT_STATUS, paymentStatus)
        }
        val db = writableDatabase
        val selection = "$COLUMN_PAYMENT_ID = ?"
        val selectionArgs = arrayOf(paymentId.toString())
        return db.update(TABLE_TUITION, values, selection, selectionArgs)
    }


}