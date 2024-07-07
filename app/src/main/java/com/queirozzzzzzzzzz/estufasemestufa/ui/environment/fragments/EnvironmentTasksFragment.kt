package com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.adapters.TaskCompleteAdapter
import com.queirozzzzzzzzzz.estufasemestufa.adapters.TaskNameAdapter
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentEnvironmentTasksBinding
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Task
import com.queirozzzzzzzzzz.estufasemestufa.repository.CompletedTaskRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.TaskRepository
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryData
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

class EnvironmentTasksFragment : Fragment() {
    private var _binding: FragmentEnvironmentTasksBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskRepository: TaskRepository
    private lateinit var completedTaskRepository: CompletedTaskRepository
    private var currentDayOfWeek: DayOfWeek = LocalDate.now().dayOfWeek

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEnvironmentTasksBinding.inflate(inflater, container, false)

        taskRepository = TaskRepository(this.requireActivity().application)
        completedTaskRepository = CompletedTaskRepository(this.requireActivity().application)

        setElements()

        return binding.root
    }

    private fun setElements() {
        binding.headerTitle.text = TemporaryData.selectedEnvironmentName

        lifecycleScope.launch {
            val tasks =
                taskRepository.getTasksByEnvironmentId(TemporaryData.selectedEnvironmentId!!)
            setTasksList(tasks)
        }
    }

    private fun getDay(date: Long): DayOfWeek {
        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.timeInMillis = date
        return DayOfWeek.of(calendar.get(Calendar.DAY_OF_WEEK))
    }

    private suspend fun setTasksList(tasks: List<Task>) {
        val todayBindingTitle = getTodayBindingTitle()
        todayBindingTitle.background = resources.getDrawable(R.drawable.right_round_rectangle)

        val completedTasks = completedTaskRepository.getCompletedTasksByEnvironmentId(TemporaryData.selectedEnvironmentId!!)
        val completedTasksIds: MutableList<Int> = mutableListOf()
        val todayDay = getDay(System.currentTimeMillis())

        for (completedTask in completedTasks) {
            if (getDay(completedTask.completionDate) == todayDay) {
                completedTasksIds.add(completedTask.taskId)
            }
        }

        for (task in tasks) {
            if (task.id in completedTasksIds) {
                task.days.sunday = false
            }
        }

        val sundayTasks = tasks.filter { task -> task.days.sunday }
        val mondayTasks = tasks.filter { task -> task.days.monday }
        val tuesdayTasks = tasks.filter { task -> task.days.tuesday }
        val wednesdayTasks = tasks.filter { task -> task.days.wednesday }
        val thursdayTasks = tasks.filter { task -> task.days.thursday }
        val fridayTasks = tasks.filter { task -> task.days.friday }
        val saturdayTasks = tasks.filter { task -> task.days.saturday }

        val sundayAdapter = getAdapterForDay(DayOfWeek.SUNDAY, sundayTasks)
        val mondayAdapter = getAdapterForDay(DayOfWeek.MONDAY, mondayTasks)
        val tuesdayAdapter = getAdapterForDay(DayOfWeek.TUESDAY, tuesdayTasks)
        val wednesdayAdapter = getAdapterForDay(DayOfWeek.WEDNESDAY, wednesdayTasks)
        val thursdayAdapter = getAdapterForDay(DayOfWeek.THURSDAY, thursdayTasks)
        val fridayAdapter = getAdapterForDay(DayOfWeek.FRIDAY, fridayTasks)
        val saturdayAdapter = getAdapterForDay(DayOfWeek.SATURDAY, saturdayTasks)

        getRecyclerView(DayOfWeek.SUNDAY).adapter = sundayAdapter
        getRecyclerView(DayOfWeek.MONDAY).adapter = mondayAdapter
        getRecyclerView(DayOfWeek.TUESDAY).adapter = tuesdayAdapter
        getRecyclerView(DayOfWeek.WEDNESDAY).adapter = wednesdayAdapter
        getRecyclerView(DayOfWeek.THURSDAY).adapter = thursdayAdapter
        getRecyclerView(DayOfWeek.FRIDAY).adapter = fridayAdapter
        getRecyclerView(DayOfWeek.SATURDAY).adapter = saturdayAdapter
    }

    private fun getTodayBindingTitle(): View {
        return when (currentDayOfWeek) {
            DayOfWeek.SUNDAY -> binding.sundayTitle
            DayOfWeek.MONDAY -> binding.mondayTitle
            DayOfWeek.TUESDAY -> binding.tuesdayTitle
            DayOfWeek.WEDNESDAY -> binding.wednesdayTitle
            DayOfWeek.THURSDAY -> binding.thursdayTitle
            DayOfWeek.FRIDAY -> binding.fridayTitle
            DayOfWeek.SATURDAY -> binding.saturdayTitle
            else -> throw IllegalArgumentException("Invalid day")
        }
    }

    private fun getAdapterForDay(
        dayOfWeek: DayOfWeek,
        tasks: List<Task>,
    ): RecyclerView.Adapter<*> {
        return when (dayOfWeek) {
            currentDayOfWeek -> TaskCompleteAdapter(tasks)
            else -> TaskNameAdapter(tasks)
        }
    }

    private fun getRecyclerView(day: DayOfWeek): RecyclerView {
        val recyclerView =
            when (day) {
                DayOfWeek.SUNDAY -> binding.sundayTaskRecyclerView
                DayOfWeek.MONDAY -> binding.mondayTaskRecyclerView
                DayOfWeek.TUESDAY -> binding.tuesdayTaskRecyclerView
                DayOfWeek.WEDNESDAY -> binding.wednesdayTaskRecyclerView
                DayOfWeek.THURSDAY -> binding.thursdayTaskRecyclerView
                DayOfWeek.FRIDAY -> binding.fridayTaskRecyclerView
                DayOfWeek.SATURDAY -> binding.saturdayTaskRecyclerView
                else -> throw IllegalArgumentException("Invalid day")
            }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return recyclerView
    }
}
