package com.example.studentnotes.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import com.example.studentnotes.R
import com.example.studentnotes.database.NotesViewModel
import com.example.studentnotes.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val PASSED = "PASSED"
    private val FAILED = "FAILED"
    private val REDCOLOR = "#FF0000"
    private val GREENCOLOR = "#00FF00"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val view = binding.root
        val sharedViewModel: NotesViewModel by activityViewModels()

        val avg: LiveData<Float> = sharedViewModel.average
        // we cannot compare avg directly to 50 because it's of type LiveData so we will observe it then compare it
        avg.observe(viewLifecycleOwner) { avgData ->
            if(avgData >= 50) {
                binding.averageTV.text = avgData.toString()
                binding.averageTV.setTextColor(Color.parseColor(GREENCOLOR))
            } else {
                binding.averageTV.text = avgData.toString()
                binding.averageTV.setTextColor(Color.parseColor(REDCOLOR))
            }
        }

        sharedViewModel.allCoursesGrades.observe(viewLifecycleOwner) { courseGrades ->
            val courses = courseGrades[0]
            val allCourses = arrayOf(
                courses.course1,
                courses.course2,
                courses.course3,
                courses.course4,
                courses.course5,
                courses.course6,
                courses.course7
            )
            val coursesTextView = arrayOf(
                binding.course1Grade,
                binding.course2Grade,
                binding.course3Grade,
                binding.course4Grade,
                binding.course5Grade,
                binding.course6Grade,
                binding.course7Grade
            )
            val statusTextView = arrayOf(
                binding.status1,
                binding.status2,
                binding.status3,
                binding.status4,
                binding.status5,
                binding.status6,
                binding.status7,
            )
            for (i in 0..6) {
                coursesTextView[i].text = allCourses[i].toString()
                if(allCourses[i] >= 50) {
                    statusTextView[i].text = PASSED
                    statusTextView[i].setTextColor(Color.parseColor(GREENCOLOR))
                } else {
                    statusTextView[i].text = FAILED
                    statusTextView[i].setTextColor(Color.parseColor(REDCOLOR))
                }
            }
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle the back button press in the second fragment
                requireActivity().finish() // This will exit the entire app
            }
        }

        // Add the callback to the OnBackPressedDispatcher
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

//        binding.editBtn.setOnClickListener {
//            view.findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
//        }

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