package com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentManageEnvironmentGoalBinding
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData

class ManageEnvironmentGoalFragment : Fragment() {
    private var _binding: FragmentManageEnvironmentGoalBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentManageEnvironmentGoalBinding.inflate(inflater, container, false)

        setElements()

        return binding.root
    }

    private fun setElements() {
        // Header
        if (TemporaryManageEnvironmentData.isEditing) {
            binding.headerTitle.text = resources.getString(R.string.manage_environment_edit_title)
        }

        // Checkbox
        val options = listOf(
            resources.getString(R.string.manage_environment_goal_aesthetic_option),
            resources.getString(R.string.manage_environment_goal_air_option),
            resources.getString(R.string.manage_environment_goal_medicinal_option),
            resources.getString(R.string.manage_environment_goal_spices_option)
        )

        val checkboxMap = mapOf(
            options[0] to binding.aestheticCheckbox,
            options[1] to binding.airCheckbox,
            options[2] to binding.medicinalCheckbox,
            options[3] to binding.spicesCheckbox
        )

        options.forEach { option ->
            val checkbox = checkboxMap[option]!!
            checkbox.setOnClickListener { toggleCheckbox(option, checkbox) }
            checkbox.setImageResource(
                if (TemporaryManageEnvironmentData.goals?.contains(option) == true) R.drawable.checkbox_checked else R.drawable.checkbox_unchecked
            )
        }

        // Save Button
        binding.btnSave.setOnClickListener {
            binding.btnNext.performClick()
        }
    }

    private fun toggleCheckbox(
        goal: String,
        checkbox: ImageView,
    ) {
        val newGoals = TemporaryManageEnvironmentData.goals?.toMutableSet() ?: mutableSetOf()
        if (goal in newGoals) {
            newGoals.remove(goal)
            checkbox.setImageResource(R.drawable.checkbox_unchecked)
        } else {
            newGoals.add(goal)
            checkbox.setImageResource(R.drawable.checkbox_checked)
        }
        TemporaryManageEnvironmentData.goals = newGoals.toList()
    }
}
