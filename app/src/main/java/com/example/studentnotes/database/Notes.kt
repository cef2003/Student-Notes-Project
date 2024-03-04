package com.example.studentnotes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Notes(
    @ColumnInfo(name = "student_id")
    @PrimaryKey(autoGenerate = true)
    var studentId: Long = 0L,
    @ColumnInfo(name = "first_name")
    var firstName: String = "",
    @ColumnInfo(name = "last_name")
    var lastName: String = "",
    @ColumnInfo(name = "I3301")
    var course1: Float = 0f,
    @ColumnInfo(name = "I3302")
    var course2: Float = 0f,
    @ColumnInfo(name = "I3303")
    var course3: Float = 0f,
    @ColumnInfo(name = "I3304")
    var course4: Float = 0f,
    @ColumnInfo(name = "I3305")
    var course5: Float = 0f,
    @ColumnInfo(name = "I3306")
    var course6: Float = 0f,
    @ColumnInfo(name = "I3350")
    var course7: Float = 0f,
    @ColumnInfo(name = "average")
    var avg: Float = 0f
)
