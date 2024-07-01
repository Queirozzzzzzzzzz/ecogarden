package com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentManageEnvironmentPlantsBinding

class ManageEnvironmentPlantsFragment : Fragment() {
    private var _binding: FragmentManageEnvironmentPlantsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentManageEnvironmentPlantsBinding.inflate(inflater, container, false)
        return binding.root
    }
}
