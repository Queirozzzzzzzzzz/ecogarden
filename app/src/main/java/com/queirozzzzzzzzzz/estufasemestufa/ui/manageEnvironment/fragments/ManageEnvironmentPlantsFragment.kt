package com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.queirozzzzzzzzzz.estufasemestufa.adapters.PlantAdapter
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentManageEnvironmentPlantsBinding
import com.queirozzzzzzzzzz.estufasemestufa.repository.PlantRepository
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData

class ManageEnvironmentPlantsFragment : Fragment() {
    private var _binding: FragmentManageEnvironmentPlantsBinding? = null
    private val binding get() = _binding!!
    private lateinit var plantRepository: PlantRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentManageEnvironmentPlantsBinding.inflate(inflater, container, false)

        plantRepository = PlantRepository(requireActivity().application)

        setElements()

        return binding.root
    }

    private fun setElements() {
        val recyclerView: RecyclerView = binding.plantsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val plants = TemporaryManageEnvironmentData.plants
        val adapter = PlantAdapter(plants!!)
        recyclerView.adapter = adapter
    }
}
