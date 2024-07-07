package com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentEnvironmentManageTasksBinding
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryData

class EnvironmentManageTasksFragment : Fragment() {
    private var _binding: FragmentEnvironmentManageTasksBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEnvironmentManageTasksBinding.inflate(inflater, container, false)
        setElements()

        return binding.root
    }

    private fun setElements() {
        binding.headerTitle.text = TemporaryData.selectedEnvironmentName
    }
}
