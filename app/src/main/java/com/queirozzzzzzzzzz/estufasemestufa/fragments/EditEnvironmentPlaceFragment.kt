package com.queirozzzzzzzzzz.estufasemestufa.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentEditEnvironmentPlaceBinding

class EditEnvironmentPlaceFragment : Fragment() {
    private var _binding: FragmentEditEnvironmentPlaceBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditEnvironmentPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }
}