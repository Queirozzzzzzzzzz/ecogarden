package com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentEnvironmentEditTaskBinding
import com.queirozzzzzzzzzz.estufasemestufa.models.DaysList
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Task
import com.queirozzzzzzzzzz.estufasemestufa.repository.TaskRepository
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryData
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageTaskData
import kotlinx.coroutines.launch

class EnvironmentEditTaskFragment : Fragment() {
    private var _binding: FragmentEnvironmentEditTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskRepository: TaskRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEnvironmentEditTaskBinding.inflate(inflater, container, false)

        taskRepository = TaskRepository(this.requireActivity().application)

        setElements()

        return binding.root
    }

    private lateinit var options: List<String>
    private lateinit var checkboxMap: Map<String, ImageView>

    private fun setElements() {
        binding.headerTitle.text = TemporaryData.selectedEnvironmentName

        setValues()

        binding.btnSave.setOnClickListener {
            updateTask()
        }

        // Checkbox
        options =
            listOf(
                resources.getString(R.string.environment_tasks_create_task_sunday),
                resources.getString(R.string.environment_tasks_create_task_monday),
                resources.getString(R.string.environment_tasks_create_task_tuesday),
                resources.getString(R.string.environment_tasks_create_task_wednesday),
                resources.getString(R.string.environment_tasks_create_task_thursday),
                resources.getString(R.string.environment_tasks_create_task_friday),
                resources.getString(R.string.environment_tasks_create_task_saturday),
            )

        checkboxMap =
            mapOf(
                options[0] to binding.sundayCheckbox,
                options[1] to binding.mondayCheckbox,
                options[2] to binding.tuesdayCheckbox,
                options[3] to binding.wednesdayCheckbox,
                options[4] to binding.thursdayCheckbox,
                options[5] to binding.fridayCheckbox,
                options[6] to binding.saturdayCheckbox,
            )

        options.forEachIndexed { index, option ->
            val checkbox = checkboxMap[option]!!
            checkbox.setOnClickListener { toggleCheckbox(index, checkbox) }
        }
    }

    private fun setValues() {
        lifecycleScope.launch {
            val task = taskRepository.getTaskById(TemporaryData.selectedTaskId!!)

            binding.name.setText(task.name)
            TemporaryManageTaskData.days = task.days
            setCheckboxStates()
        }
    }

    private fun setCheckboxStates() {
        val daysList = TemporaryManageTaskData.days

        options.forEachIndexed { index, option ->
            val checkbox = checkboxMap[option]!!
            if (daysList?.get(index) == true) {
                checkbox.setImageResource(R.drawable.checkbox_checked)
            } else {
                checkbox.setImageResource(R.drawable.checkbox_unchecked)
            }
            checkbox.setOnClickListener { toggleCheckbox(index, checkbox) }
        }
    }

    private fun toggleCheckbox(
        dayIndex: Int,
        checkbox: ImageView,
    ) {
        val newDays =
            TemporaryManageTaskData.days ?: DaysList(
                false, false, false, false, false, false, false,
            )
        if (newDays[dayIndex]) {
            newDays[dayIndex] = false
            checkbox.setImageResource(R.drawable.checkbox_unchecked)
        } else {
            newDays[dayIndex] = true
            checkbox.setImageResource(R.drawable.checkbox_checked)
        }
        println(newDays)
        TemporaryManageTaskData.days = newDays
    }

    private fun updateTask() {
        val name = binding.name.text.toString()
        val days = TemporaryManageTaskData.days

        if (name.isNotEmpty()) {
            val task =
                Task(
                    id = TemporaryData.selectedTaskId!!,
                    name = name,
                    days = days!!,
                    environmentId = TemporaryData.selectedEnvironmentId!!,
                )

            lifecycleScope.launch {
                taskRepository.updateTask(task)
            }
        }
        binding.btnCancel.performClick()
    }
}
