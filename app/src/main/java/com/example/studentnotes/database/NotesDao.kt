package com.example.studentnotes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {
    // insert all data into the table
    @Insert
    suspend fun insert(note: Notes)

    // while it's not optimal to delete a course, so we will not implement the delete method
    @Delete
    suspend fun delete(note: Notes)

    @Update
    suspend fun update(note: Notes)

    // get the first name, last name and average to display it in the second and third fragment
    @Query("SELECT first_name FROM notes_table")
    fun getFirstName(): LiveData<String>

    @Query("SELECT last_name FROM notes_table")
    fun getLastName(): LiveData<String>

    @Query("SELECT average FROM notes_table")
    fun getAverage(): LiveData<Float>

    // get all the courses to display the notes in the third fragment
    @Query("SELECT I3301, I3302, I3303, I3304, I3305, I3306, I3350 FROM notes_table")
    fun getAllCourses(): LiveData<List<Float>>
}