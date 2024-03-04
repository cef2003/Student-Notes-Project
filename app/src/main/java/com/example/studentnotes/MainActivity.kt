package com.example.studentnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.studentnotes.database.NotesDatabase
import com.example.studentnotes.database.NotesViewModel
import com.example.studentnotes.database.NotesViewModelFactory
import com.example.studentnotes.fragments.FirstFragment
import com.example.studentnotes.fragments.SecondFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // we create a shared view model for all fragments, because we cannot share view models using safeargs
        val application = requireNotNull(this).application
        val dao = NotesDatabase.getInstance(application).notesDao
        val viewModelFactory = NotesViewModelFactory(dao)
        val viewModel: NotesViewModel =
            ViewModelProvider(this, viewModelFactory)[NotesViewModel::class.java]
    }
}