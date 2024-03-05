package com.example.studentnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.studentnotes.database.NotesDatabase
import com.example.studentnotes.database.NotesViewModel
import com.example.studentnotes.database.NotesViewModelFactory
import com.example.studentnotes.fragments.SecondFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // we create a shared view model for all fragments, because we cannot share view models using safeargs
        val application = requireNotNull(this).application
        val dao = NotesDatabase.getInstance(application).notesDao
        val viewModelFactory = NotesViewModelFactory(dao)
        val viewModel: NotesViewModel = ViewModelProvider(this, viewModelFactory)[NotesViewModel::class.java]

        // check the database, if there is one row it will navigate to the second fragment directly
        // else it will stay in the first fragment
        val rowCounter = viewModel.rowsNum
        rowCounter.observe(this) { rowCount ->
            if(rowCount == 1) {
                navigateToSecondFragment()
            }
        }
    }

    override fun onBackPressed() {
        val currentFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)

        if(currentFragment is SecondFragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun navigateToSecondFragment() {
        val fragment = SecondFragment()
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}