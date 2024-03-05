package com.example.studentnotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.studentnotes.R
import com.example.studentnotes.database.NotesViewModel
import com.example.studentnotes.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        val sharedViewModel: NotesViewModel by activityViewModels()

        binding.viewModel = sharedViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.saveBtn.setOnClickListener {
            val navController = view.findNavController()
//            sharedViewModel.checkValidity(requireContext())
            sharedViewModel.addNote() // calling the addNote() method here and not in the fragment_first.xml to have the ability to navigate to the second fragment
            navController.navigate(R.id.action_firstFragment_to_secondFragment)
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}