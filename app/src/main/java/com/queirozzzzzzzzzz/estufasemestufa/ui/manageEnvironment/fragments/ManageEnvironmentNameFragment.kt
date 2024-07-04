package com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentManageEnvironmentNameBinding
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData

class ManageEnvironmentNameFragment : Fragment() {
    private var _binding: FragmentManageEnvironmentNameBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentManageEnvironmentNameBinding.inflate(inflater, container, false)

        setElements()

        return binding.root
    }

    private fun setElements() {
        if (TemporaryManageEnvironmentData.isEditing) {
            binding.headerTitle.text = resources.getString(R.string.manage_environment_edit_title)
        }

        if (TemporaryManageEnvironmentData.name?.isNotEmpty() == true) {
            binding.name.setText(TemporaryManageEnvironmentData.name)
        }

        binding.name.doOnTextChanged { _, _, _, _ ->
            saveName()
        }

        binding.btnSave.setOnClickListener {
            if (binding.name.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), R.string.required_name, Toast.LENGTH_SHORT).show()
            } else {
                binding.btnFinish.performClick()
            }
        }
    }

    private fun saveName() {
        TemporaryManageEnvironmentData.name = binding.name.text.toString()
    }
}
