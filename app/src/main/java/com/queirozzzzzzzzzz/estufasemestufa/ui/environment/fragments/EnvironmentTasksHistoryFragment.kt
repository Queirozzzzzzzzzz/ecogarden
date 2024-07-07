package com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.queirozzzzzzzzzz.estufasemestufa.adapters.HistoryAdapter
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentEnvironmentTasksHistoryBinding
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Task
import com.queirozzzzzzzzzz.estufasemestufa.repository.CompletedTaskRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.TaskRepository
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryData
import kotlinx.coroutines.launch

class EnvironmentTasksHistoryFragment : Fragment() {
    private var _binding: FragmentEnvironmentTasksHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskRepository: TaskRepository
    private lateinit var completedTaskRepository: CompletedTaskRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEnvironmentTasksHistoryBinding.inflate(inflater, container, false)

        taskRepository = TaskRepository(this.requireActivity().application)
        completedTaskRepository = CompletedTaskRepository(this.requireActivity().application)

        setElements()

        return binding.root
    }

    private fun setElements() {
        binding.headerTitle.text = TemporaryData.selectedEnvironmentName

        setHistoryList()
    }

    private fun setHistoryList() {
        lifecycleScope.launch {
            val completedTasksList =
                completedTaskRepository.getCompletedTasksByEnvironmentId(TemporaryData.selectedEnvironmentId!!)
            val historyList: MutableList<Task> = mutableListOf()

            for (completedTask in completedTasksList) {
                try {
                    val task = taskRepository.getTaskById(completedTask.taskId)
                    task.completionDate = completedTask.completionDate
                    historyList.add(task)
                } catch (e: Exception) {
                    println(e)
                }
            }

            val recyclerView = binding.historyTasksRecyclerView
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            val adapter = HistoryAdapter(historyList)
            recyclerView.adapter = adapter
        }
    }
}
