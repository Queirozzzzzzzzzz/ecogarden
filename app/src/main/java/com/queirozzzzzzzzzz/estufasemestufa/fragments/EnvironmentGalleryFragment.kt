package com.queirozzzzzzzzzz.estufasemestufa.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentEnvironmentGalleryBinding

class EnvironmentGalleryFragment : Fragment() {
    private var _binding: FragmentEnvironmentGalleryBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnvironmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }
}