package com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.adapters.TimetableAdapter
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentManageEnvironmentTimetablesBinding
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData

class ManageEnvironmentTimetablesFragment : Fragment() {
    private var _binding: FragmentManageEnvironmentTimetablesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentManageEnvironmentTimetablesBinding.inflate(inflater, container, false)

        setElements()

        return binding.root
    }

    private fun setElements() {
        // Header
        if (TemporaryManageEnvironmentData.isEditing) {
            binding.headerTitle.text = resources.getString(R.string.manage_environment_edit_title)
        }

        val recyclerView: RecyclerView = binding.timetablesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val timetables = TemporaryManageEnvironmentData.timetables
        val adapter = TimetableAdapter(timetables!!)
        recyclerView.adapter = adapter
    }
}
