package com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.queirozzzzzzzzzz.estufasemestufa.api.Endpoints
import com.queirozzzzzzzzzz.estufasemestufa.api.Service
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentEnvironmentMainBinding
import com.queirozzzzzzzzzz.estufasemestufa.models.Data
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryData
import com.queirozzzzzzzzzz.estufasemestufa.viewmodel.DataViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EnvironmentMainFragment : Fragment() {
    private var _binding: FragmentEnvironmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewmodel: DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEnvironmentMainBinding.inflate(inflater, container, false)

        setElements()

        viewmodel = ViewModelProvider(this)[DataViewModel::class.java]

        return binding.root
    }

    private fun refreshData() {
        CoroutineScope(Dispatchers.IO).launch {
            val values = viewmodel.getNew()
            withContext(Dispatchers.Main) {
                binding.ph.text = values.get("ph")?.takeUnless { it.isJsonNull }?.asString ?: "--"
                binding.humidity.text = values.get("humidity")?.takeUnless { it.isJsonNull }?.asString ?: "--"
                binding.temperature.text = values.get("temperature")?.takeUnless { it.isJsonNull }?.asString ?: "--"
                binding.lightIntensity.text = values.get("light_intensity")?.takeUnless { it.isJsonNull }?.asString ?: "--"
            }
        }
    }

    private fun setElements() {
        binding.headerTitle.text = TemporaryData.selectedEnvironmentName

        binding.refreshDataBtn.setOnClickListener {
            refreshData()
        }
    }
}
