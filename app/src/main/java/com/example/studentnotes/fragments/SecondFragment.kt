package com.example.studentnotes.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.room.InvalidationTracker
import com.example.studentnotes.R
import com.example.studentnotes.database.Notes
import com.example.studentnotes.database.NotesDatabase
import com.example.studentnotes.database.NotesViewModel
import com.example.studentnotes.database.NotesViewModelFactory
import com.example.studentnotes.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val view = binding.root
        val sharedViewModel: NotesViewModel by activityViewModels()

        sharedViewModel.allCoursesGrades.observe(viewLifecycleOwner) { courseGrades ->
            val course1Grade = courseGrades[0]
            val course2Grade = courseGrades[1]
            val course3Grade = courseGrades[2]
            val course4Grade = courseGrades[3]
            val course5Grade = courseGrades[4]
            val course6Grade = courseGrades[5]
            val course7Grade = courseGrades[6]
            binding.course1Grade.text = course1Grade.toString()
            if(course1Grade >= 50) {
                binding.status1.text = "PASSED"
                binding.status1.setTextColor(Color.parseColor("#00FF00"))
            } else {
                binding.status1.text = "FAILED"
                binding.status1.setTextColor(Color.parseColor("#FF0000"))
            }
//            val course1GradeStr = course1Grade.toString()
//            val course2GradeStr = course2Grade.toString()
//            val course3GradeStr = course3Grade.toString()
//            val course4GradeStr = course4Grade.toString()
//            val course5GradeStr = course5Grade.toString()
//            val course6GradeStr = course6Grade.toString()
//            val course7GradeStr = course7Grade.toString()
        }

        binding.viewModel = sharedViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return view
    }

//    private fun updateTableLayout(courses: List<Float>) {
//        binding.tableLayout.removeAllViews()
//        for(courseNote in courses) {
//            val row = TableRow(requireContext())
//            val textView = TextView(requireContext())
//            textView.text = courseNote.toString()
//            row.addView(textView)
//            binding.tableLayout.addView(row)
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}