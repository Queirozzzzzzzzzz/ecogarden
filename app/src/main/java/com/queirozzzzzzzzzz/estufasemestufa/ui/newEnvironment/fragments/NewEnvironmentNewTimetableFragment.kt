package com.queirozzzzzzzzzz.estufasemestufa.ui.newEnvironment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentNewEnvironmentNewTimetableBinding

class NewEnvironmentNewTimetableFragment : Fragment() {
    private var _binding: FragmentNewEnvironmentNewTimetableBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewEnvironmentNewTimetableBinding.inflate(inflater, container, false)
        return binding.root
    }
}
