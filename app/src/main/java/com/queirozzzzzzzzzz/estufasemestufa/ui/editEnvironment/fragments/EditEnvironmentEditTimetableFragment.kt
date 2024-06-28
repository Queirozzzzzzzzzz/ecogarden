package com.queirozzzzzzzzzz.estufasemestufa.ui.editEnvironment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentEditEnvironmentEditTimetableBinding

class EditEnvironmentEditTimetableFragment : Fragment() {
    private var _binding: FragmentEditEnvironmentEditTimetableBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEditEnvironmentEditTimetableBinding.inflate(inflater, container, false)
        return binding.root
    }
}
