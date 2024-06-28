package com.queirozzzzzzzzzz.estufasemestufa.ui.editEnvironment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentEditEnvironmentTimetablesBinding

class EditEnvironmentTimetablesFragment : Fragment() {
    private var _binding: FragmentEditEnvironmentTimetablesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEditEnvironmentTimetablesBinding.inflate(inflater, container, false)
        return binding.root
    }
}
