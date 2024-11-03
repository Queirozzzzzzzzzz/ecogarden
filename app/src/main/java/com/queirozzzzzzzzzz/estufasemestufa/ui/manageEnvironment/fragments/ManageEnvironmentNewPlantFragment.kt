package com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentManageEnvironmentNewPlantBinding
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Plant
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData

class ManageEnvironmentNewPlantFragment : Fragment() {
    private var _binding: FragmentManageEnvironmentNewPlantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentManageEnvironmentNewPlantBinding.inflate(inflater, container, false)

        setElements()

        return binding.root
    }

    private fun setElements() {
        if (TemporaryManageEnvironmentData.isEditing) {
            binding.headerTitle.text = resources.getString(R.string.manage_environment_edit_title)
        }

        setLightIntensity()
        setHumidity()

        binding.btnCreate.setOnClickListener {
            createPlant()
        }
    }

    var lightIntensity: String = ""
    var soilHumidity: String = ""

    private fun createPlant() {
        val name = binding.name.text.toString()
        val ph = binding.ph.text.toString().toIntOrNull()
        val airTemperature = binding.airTemperature.text.toString().toDoubleOrNull()

        if (name.isEmpty()) {
            Toast.makeText(requireContext(), R.string.required_name, Toast.LENGTH_SHORT).show()
            return
        }

        val plant = Plant(0, name, soilHumidity, ph, lightIntensity, airTemperature, 0)
        val plants = TemporaryManageEnvironmentData.plants?.toMutableList()
        if (plant in plants!!) {
            Toast.makeText(requireContext(), R.string.plant_already_exists, Toast.LENGTH_SHORT)
                .show()
            return
        } else {
            plants.add(plant)
            TemporaryManageEnvironmentData.plants = plants
            binding.btnCancel.performClick()
        }
    }

    private fun setLightIntensity() {
        val lightIntensityList = resources.getStringArray(R.array.light_intensity_array)
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, lightIntensityList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.lightIntensity.adapter = adapter

        binding.lightIntensity.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedIntensity = parent?.getItemAtPosition(position).toString()
                    lightIntensity = selectedIntensity
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    println("Nothing selected")
                }
            }
    }

    private fun setHumidity() {
        val humidityList = resources.getStringArray(R.array.humidity_array)
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, humidityList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.soilHumidity.adapter = adapter

        binding.soilHumidity.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedHumidity = parent?.getItemAtPosition(position).toString()
                    soilHumidity = selectedHumidity
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    println("Nothing selected")
                }
            }
    }
}