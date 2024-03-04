package com.example.studentnotes.database

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotesViewModel(val dao: NotesDao) : ViewModel() {
    var firstName: String = ""
    var lastName: String = ""
    var course1Str = ""
    var course2Str = ""
    var course3Str = ""
    var course4Str = ""
    var course5Str = ""
    var course6Str = ""
    var course7Str = ""
    var avg: Float = 0f
    var avgStr: String = ""
    var sum = 0f
    var counter: Int = 0

    val fName: LiveData<String> = dao.getFirstName().map { it }
    val lName: LiveData<String> = dao.getLastName().map { it }
    val average: LiveData<String> = dao.getAverage().map { it.toString() }

    fun addNote() {
        viewModelScope.launch {
            val note = Notes()
            note.firstName = firstName
            note.lastName = lastName
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
            avgStr = avg.toString()
            note.avg = avg
            dao.insert(note)
        }
    }

    val allCoursesGrades: LiveData<List<Float>> = dao.getAllCourses()

//    private val _allCourses: MutableLiveData<List<Float>> = MutableLiveData()
//    val allCourses: LiveData<List<Float>> = _allCourses
//
//    fun getAllCourseNotes() {
//        viewModelScope.launch {
//            _allCourses.value = dao.getAllCourses().value?.toList()
//        }
//    }
}