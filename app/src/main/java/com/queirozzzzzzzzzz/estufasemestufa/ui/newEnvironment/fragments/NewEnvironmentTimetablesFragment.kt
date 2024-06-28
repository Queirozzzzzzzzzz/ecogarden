package com.queirozzzzzzzzzz.estufasemestufa.ui.newEnvironment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentNewEnvironmentTimetablesBinding

class NewEnvironmentTimetablesFragment : Fragment() {
    private var _binding: FragmentNewEnvironmentTimetablesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewEnvironmentTimetablesBinding.inflate(inflater, container, false)
        return binding.root
    }
}
