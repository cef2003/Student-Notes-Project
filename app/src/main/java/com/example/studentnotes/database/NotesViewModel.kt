package com.example.studentnotes.database

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotesViewModel(val dao: NotesDao) : ViewModel() {
    var firstName: String = ""
    var lastName: String = ""
    var course1Str: String = ""
    var course2Str: String = ""
    var course3Str: String = ""
    var course4Str: String = ""
    var course5Str: String = ""
    var course6Str: String = ""
    var course7Str: String = ""
//    var data = arrayOf(
//        course1Str.toFloat(), course2Str.toFloat(),
//        course3Str.toFloat(), course4Str.toFloat(), course5Str.toFloat(),
//        course6Str.toFloat(), course7Str.toFloat()
//    )
    var avg: Float = 0f
    var sum = 0f
    var counter: Int = 0

    val fName: LiveData<String> = dao.getFirstName().map { it }
    val lName: LiveData<String> = dao.getLastName().map { it }
    val average: LiveData<Float> = dao.getAverage().map { it }

    val allCoursesGrades: LiveData<List<Notes>> = dao.getAllCourses()
    val rowsNum: LiveData<Int> = dao.getRowCount()

    fun addNote() {
        viewModelScope.launch {
            val note = Notes()
            note.firstName = firstName
            note.lastName = lastName
//            val oldNotes = arrayOf(note.course1, note.course2, note.course3, note.course4, note.course5, note.course6, note.course7)
            note.course1 = course1Str.toFloat()
            note.course2 = course2Str.toFloat()
            note.course3 = course3Str.toFloat()
            note.course4 = course4Str.toFloat()
            note.course5 = course5Str.toFloat()
            note.course6 = course6Str.toFloat()
            note.course7 = course7Str.toFloat()
            val notes = arrayOf(note.course1, note.course2, note.course3, note.course4, note.course5, note.course6, note.course7)
            for (i in notes) {
                sum += i
                counter++
            }
            avg = sum/counter.toFloat()
            note.avg = avg
            dao.insert(note)
        }
    }

//    fun checkValidity(context: Context) {
//        for(i in 0..6) {
//            if(data[i] < 0 || data[i] > 100) {
//                show(context, "")
//            }
//        }
//    }
//
//    private fun show(context: Context, message: String) {
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//    }
}