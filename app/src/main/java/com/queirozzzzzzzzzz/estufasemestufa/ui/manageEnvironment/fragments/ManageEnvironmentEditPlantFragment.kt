package com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentManageEnvironmentEditPlantBinding
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Plant
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData

class ManageEnvironmentEditPlantFragment : Fragment() {
    private var _binding: FragmentManageEnvironmentEditPlantBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentManageEnvironmentEditPlantBinding.inflate(inflater, container, false)

        setElements()

        return binding.root
    }

    private fun setElements() {
        if (TemporaryManageEnvironmentData.isEditing) {
            binding.headerTitle.text = resources.getString(R.string.manage_environment_edit_title)
        }

        setLightIntensity()
        setHumidity()
        setValues()

        binding.btnCreate.setOnClickListener {
            savePlant()
        }
    }

    var lightIntensity: String = ""
    var humidity: String = ""

    private fun setValues() {
        val plant =
            TemporaryManageEnvironmentData.plants?.firstOrNull { it.name == TemporaryManageEnvironmentData.selectedPlant }

        plant?.let {
            binding.name.setText(it.name)
            binding.humidity.setSelection(resources.getStringArray(R.array.humidity_array).indexOf(it.humidity))
            it.ph?.let { it1 -> binding.ph.setText(it1.toString()) }
            binding.lightIntensity.setSelection(resources.getStringArray(R.array.light_intensity_array).indexOf(it.lightIntensity))
            it.lightDuration?.let { it1 -> binding.lightDuration.setText(it1.toString()) }
            it.soilConductivity?.let { binding.soilConductivity.setText(it.toString()) }
            it.soilSalinity?.let { binding.soilSalinity.setText(it.toString()) }
            it.temperature?.let { binding.temperature.setText(it.toString()) }
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
        binding.humidity.adapter = adapter

        binding.humidity.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedHumidity = parent?.getItemAtPosition(position).toString()
                    humidity = selectedHumidity
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    println("Nothing selected")
                }
            }
    }

    private fun savePlant() {
        val editedPlant =
            Plant(0, binding.name.text.toString(), binding.humidity.selectedItem.toString(), binding.ph.text.toString().toIntOrNull(), binding.lightIntensity.selectedItem.toString(), binding.lightDuration.text.toString().toIntOrNull(), binding.soilConductivity.text.toString().toDoubleOrNull(), binding.soilSalinity.text.toString().toDoubleOrNull(), binding.temperature.text.toString().toIntOrNull(), 0)
        val plants = TemporaryManageEnvironmentData.plants?.toMutableList()
        val index = plants?.indexOfFirst { it.name == TemporaryManageEnvironmentData.selectedPlant }
        if (index != null) {
            plants[index] = editedPlant
        }
        TemporaryManageEnvironmentData.plants = plants
        binding.btnCancel.performClick()
    }
}
