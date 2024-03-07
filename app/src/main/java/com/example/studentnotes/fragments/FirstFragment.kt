package com.example.studentnotes.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.studentnotes.R
import com.example.studentnotes.database.Notes
import com.example.studentnotes.database.NotesViewModel
import com.example.studentnotes.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: NotesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root

        val allCoursesEditText = arrayOf(
            binding.cours1,
            binding.cours2,
            binding.cours3,
            binding.cours4,
            binding.cours5,
            binding.cours6,
            binding.cours7,
        )

        binding.viewModel = sharedViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.saveBtn.setOnClickListener {
            if(isInputValid()) {
                // calling the addNote() method here and not in the fragment_first.xml to have the ability to navigate to the second fragment
                sharedViewModel.addNote()
                val navController = view.findNavController()
                navController.navigate(R.id.action_firstFragment_to_secondFragment)
            } else {
                showToast("Check your grades!")
            }
        }

        // we can use safeargs instead
        sharedViewModel.startEdit.observe(viewLifecycleOwner) { isStartEdit ->
            if(isStartEdit) {
                binding.saveBtn.text = "UPDATE"
                binding.saveBtn.setOnClickListener {
                    if(isInputValid()) {
                        val updatedCourses = mutableListOf<Float>()
                        for(i in 0..6) {
                            updatedCourses.add(allCoursesEditText[i].text.toString().toFloat())
                        }
                        val updatedNote = Notes(
                            studentId = 1,
                            firstName = binding.firstName.text.toString(),
                            lastName = binding.familyName.text.toString(),
                            course1 = updatedCourses[0],
                            course2 = updatedCourses[1],
                            course3 = updatedCourses[2],
                            course4 = updatedCourses[3],
                            course5 = updatedCourses[4],
                            course6 = updatedCourses[5],
                            course7 = updatedCourses[6],
                            avg = sharedViewModel.calculateAverage(updatedCourses)
                        )
                        sharedViewModel.update(updatedNote)
                    } else {
                        showToast("Check your grades!")
                    }
                }

                sharedViewModel.fName.observe(viewLifecycleOwner) { firstName ->
                    binding.firstName.text = Editable.Factory.getInstance().newEditable(firstName.toString())
                }
                sharedViewModel.lName.observe(viewLifecycleOwner) { lastName ->
                    binding.familyName.text = Editable.Factory.getInstance().newEditable(lastName.toString())
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

                    for(i in 0..6) {
                        allCoursesEditText[i].setText(allCourses[i].toString())
                    }
                }
            }
        }

        return view
    }

    private fun isInputValid(): Boolean {
        val allCoursesEditText = arrayOf(
            binding.cours1,
            binding.cours2,
            binding.cours3,
            binding.cours4,
            binding.cours5,
            binding.cours6,
            binding.cours7,
        )

        for (i in 0 until allCoursesEditText.size) {
            val input = allCoursesEditText[i].text.toString()
            val grade = input.toFloatOrNull()

            if (grade == null || grade < 0f || grade > 100f) {
                return false
            }
        }

        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}