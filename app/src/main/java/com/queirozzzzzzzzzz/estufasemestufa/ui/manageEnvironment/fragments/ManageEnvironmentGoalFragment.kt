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
        val optionIds =
            listOf(
                R.string.manage_environment_goal_aesthetic_option,
                R.string.manage_environment_goal_air_option,
                R.string.manage_environment_goal_medicinal_option,
                R.string.manage_environment_goal_spices_option,
            )

        val checkboxMap =
            mapOf(
                optionIds[0] to binding.aestheticCheckbox,
                optionIds[1] to binding.airCheckbox,
                optionIds[2] to binding.medicinalCheckbox,
                optionIds[3] to binding.spicesCheckbox,
            )

        optionIds.forEach { optionId ->
            val checkbox = checkboxMap[optionId]!!
            checkbox.setOnClickListener { toggleCheckbox(optionId.toString(), checkbox) }
            checkbox.setImageResource(
                if (TemporaryManageEnvironmentData.goals?.contains(optionId.toString()) == true) R.drawable.checkbox_checked else R.drawable.checkbox_unchecked,
            )
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
