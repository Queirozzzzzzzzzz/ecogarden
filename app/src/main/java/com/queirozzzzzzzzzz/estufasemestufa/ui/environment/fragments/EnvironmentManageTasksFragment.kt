package com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.queirozzzzzzzzzz.estufasemestufa.adapters.TaskAdapter
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentEnvironmentManageTasksBinding
import com.queirozzzzzzzzzz.estufasemestufa.repository.TaskRepository
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryData
import kotlinx.coroutines.launch

class EnvironmentManageTasksFragment : Fragment() {
    private var _binding: FragmentEnvironmentManageTasksBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskRepository: TaskRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEnvironmentManageTasksBinding.inflate(inflater, container, false)

        taskRepository = TaskRepository(this.requireActivity().application)

        setElements()

        return binding.root
    }

    private fun setElements() {
        binding.headerTitle.text = TemporaryData.selectedEnvironmentName

        lifecycleScope.launch {
            val recyclerView: RecyclerView = binding.tasksRecyclerView
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            val tasks = taskRepository.getTasksByEnvironmentId(TemporaryData.selectedEnvironmentId!!)
            val adapter = TaskAdapter(tasks)
            recyclerView.adapter = adapter
        }
    }
}
