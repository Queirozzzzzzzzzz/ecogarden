package com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments

import EnvironmentPlantsAdapter
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentEnvironmentMainBinding
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.EnvironmentPlant
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Plant
import com.queirozzzzzzzzzz.estufasemestufa.repository.PlantRepository
import com.queirozzzzzzzzzz.estufasemestufa.utils.NetworkUtils
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryData
import com.queirozzzzzzzzzz.estufasemestufa.viewmodel.DataViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EnvironmentMainFragment : Fragment() {
    private var _binding: FragmentEnvironmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataViewModel: DataViewModel
    private lateinit var plantRepository: PlantRepository
    private var isRefreshing = false
    private var refreshJob: Job? = null
    private var clickedRefresh: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEnvironmentMainBinding.inflate(inflater, container, false)

        setElements()

        dataViewModel = ViewModelProvider(this)[DataViewModel::class.java]
        plantRepository = PlantRepository(requireActivity().application)

        refreshData(false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        startDataRefresh()
    }

    override fun onPause() {
        super.onPause()
        stopDataRefresh()
    }

    private fun startDataRefresh() {
        refreshJob?.cancel()

        refreshJob = lifecycleScope.launch {
            while (isActive) {
                if (clickedRefresh) {
                    clickedRefresh = false
                } else {
                    refreshData(true)
                }

                delay(5000)
            }
        }
    }

    private fun stopDataRefresh(){
        refreshJob?.cancel()
        refreshJob = null
    }

    private fun refreshData(isAutomatic: Boolean) {
        if(isRefreshing) return

        if(!NetworkUtils.hasInternet(this.requireContext())) {
            binding.noInternetText.visibility = View.VISIBLE
            return
        }
        binding.noInternetText.visibility = View.GONE

        if(!isAutomatic) {
            showLoadingDialog()
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val values = dataViewModel.getNew()
                withContext(Dispatchers.Main) {
                    binding.ph.text = values.get("ph")?.takeUnless { it.isJsonNull }?.asString ?: "--"

                    binding.soilHumidity.text = values.get("soil_humidity")?.takeUnless { it.isJsonNull }?.asString?.let{ humidityRawValue ->
                        humidityRawValue.toIntOrNull()?.let { currentHumidityInt ->
                            val humidityState = when {
                                currentHumidityInt > 30 -> getString(R.string.humidity_high)
                                currentHumidityInt < 30 -> getString(R.string.humidity_low)
                                else -> ""
                            }
                            "$humidityState ($humidityRawValue)"
                        } ?: humidityRawValue
                    } ?: "--"

                    binding.lightIntensity.text = values.get("light_intensity")?.takeUnless { it.isJsonNull }?.asString?.let { lightIntensityRawValue ->
                        lightIntensityRawValue.toDoubleOrNull()?.let { currentLightIntensityDouble ->
                            val intensityState = when {
                                currentLightIntensityDouble < 3 -> getString(R.string.light_intensity_low)
                                currentLightIntensityDouble in 3.0..7.0 -> getString(R.string.light_intensity_medium)
                                currentLightIntensityDouble > 7 -> getString(R.string.light_intensity_strong)
                                else -> ""
                            }
                            "$intensityState ($lightIntensityRawValue)"
                        } ?: lightIntensityRawValue
                    } ?: "--"

                    binding.airTemperature.text = values.get("air_temperature")?.takeUnless { it.isJsonNull }?.asString?.let { airTemperatureValue ->
                        if (airTemperatureValue != "--") "$airTemperatureValue °C" else airTemperatureValue
                    } ?: "--"

                    setPlants(values)
                }
            } catch (e: Exception) {
                Log.e("CoroutineException", "An error occurred while fetching data", e)
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Um erro ocorreu ao carregar os dados.", Toast.LENGTH_SHORT).show()
                }
            } finally {
                withContext(Dispatchers.Main) {
                    if(!isAutomatic){
                        hideLoadingDialog()
                    }
                }
            }
        }
    }

    private fun showLoadingDialog() {
        binding.data.visibility = View.GONE
        binding.environmentPlantsRecyclerView.visibility = View.GONE
        binding.loading.visibility = View.VISIBLE
        isRefreshing = true
    }

    private fun hideLoadingDialog() {
        binding.data.visibility = View.VISIBLE
        binding.environmentPlantsRecyclerView.visibility = View.VISIBLE
        binding.loading.visibility = View.GONE
        isRefreshing = false
    }

    private fun setElements() {
        binding.headerTitle.text = TemporaryData.selectedEnvironmentName

        binding.refreshDataBtn.setOnClickListener {
            clickedRefresh = true
            refreshData(false)
        }
    }

    private fun setPlants(data: JsonObject) {
        lifecycleScope.launch {
            val plants = plantRepository.getPlantsByEnvironmentId(TemporaryData.selectedEnvironmentId!!)

            if (isAdded) {
                val environmentPlants = withContext(Dispatchers.Default) {
                    getEnvironmentPlants(plants, data)
                }

                withContext(Dispatchers.Main) {
                    val recyclerView = binding.environmentPlantsRecyclerView
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    val adapter = EnvironmentPlantsAdapter(environmentPlants)
                    recyclerView.adapter = adapter
                }
            }
        }
    }

    private fun getEnvironmentPlants(plants: List<Plant>, data: JsonObject): List<EnvironmentPlant> {
        return plants.map { plant ->
            with(plant) {
                val currentSoilHumidity = getStringFromData(data, "soil_humidity")
                val currentLightIntensity = getStringFromData(data, "light_intensity")
                val currentPh = getStringFromData(data, "ph")
                val currentAirTemperature = getStringFromData(data, "air_temperature")

                EnvironmentPlant(name = name,
                    light_intensity = (lightIntensity?.let { evaluateLightIntensity(it, currentLightIntensity) } ?: getString(R.string.environment_plant_undefined)),
                    soil_humidity = (soilHumidity?.let { evaluateHumidity(it, currentSoilHumidity) } ?: getString(R.string.environment_plant_undefined)),
                    ph = (ph?.let { evaluatePh(it, currentPh) } ?: getString(R.string.environment_plant_undefined)),
                    air_temperature = (airTemperature?.let { evaluateTemperature(it, currentAirTemperature) } ?: getString(R.string.environment_plant_undefined))
                )
            }
        }
    }

    private fun getStringFromData(data: JsonObject, key: String): String {
        val element = data.get(key)
        return if (element != null) { element.asString } else { "--" }
    }

    enum class HumidityState(@StringRes private val resourceId: Int) {
        UNDEFINED(R.string.environment_plant_undefined),
        GOOD(R.string.humidity_good),
        BAD(R.string.humidity_bad);

        fun asString(context: Context): String {
            return context.getString(resourceId)
        }
    }

    enum class LightIntensityState(@StringRes private val resourceId: Int) {
        UNDEFINED(R.string.environment_plant_undefined),
        GOOD(R.string.light_intensity_good),
        BAD(R.string.light_intensity_bad),
        VERY_BAD(R.string.light_intensity_very_bad);

        fun asString(context: Context): String {
            return context.getString(resourceId)
        }
    }

    enum class PhState(@StringRes private val resourceId: Int) {
        UNDEFINED(R.string.environment_plant_undefined),
        GOOD(R.string.ph_good),
        LESS(R.string.ph_less),
        MORE(R.string.ph_more);

        fun asString(context: Context): String {
            return context.getString(resourceId)
        }
    }

    enum class TemperatureState(@StringRes private val resourceId: Int) {
        UNDEFINED(R.string.environment_plant_undefined),
        GOOD(R.string.temperature_good),
        LESS(R.string.temperature_less),
        MORE(R.string.temperature_more);

        fun asString(context: Context): String {
            return context.getString(resourceId)
        }
    }

    private fun evaluateHumidity(plantHumidity: String, currentHumidity: String): String {
        val currentHumidityInt = currentHumidity.toIntOrNull() ?: return getString(R.string.environment_plant_undefined)

        val humidityState = when {
            currentHumidityInt > 30 -> getString(R.string.humidity_high)
            currentHumidityInt < 30 -> getString(R.string.humidity_low)
            else -> HumidityState.UNDEFINED
        }

        val state = if (plantHumidity == humidityState) {
            HumidityState.GOOD
        } else {
            HumidityState.BAD
        }

        return state.asString(requireContext())
    }

    private fun evaluateLightIntensity(plantLightIntensity: String, currentLightIntensity: String): String {
        val currentLightIntensityDouble = currentLightIntensity.toDoubleOrNull() ?: return getString(R.string.environment_plant_undefined)

        val intensityState = when {
            currentLightIntensityDouble < 3 -> getString(R.string.light_intensity_low)
            currentLightIntensityDouble in 3.0..7.0 -> getString(R.string.light_intensity_medium)
            else -> getString(R.string.light_intensity_strong)
        }

        val state = if (plantLightIntensity == intensityState) {
            LightIntensityState.GOOD
        } else {
            LightIntensityState.BAD
        }

        return state.asString(requireContext())
    }

    private fun evaluatePh(plantPh: Int, currentPh: String): String {
        val currentPhInt = currentPh.toIntOrNull() ?: return getString(R.string.environment_plant_undefined)

        val state = when {
            plantPh == currentPhInt -> PhState.GOOD
            plantPh < currentPhInt -> PhState.MORE
            plantPh > currentPhInt -> PhState.LESS
            else -> PhState.UNDEFINED
        }

        val difference = currentPhInt - plantPh
        return "${state.asString(requireContext())} (${if (difference > 0) "+" else ""}$difference)"
    }

    private fun evaluateTemperature(plantTemperature: Double, currentTemperature: String): String {
        val currentTemperatureInt = currentTemperature.toDoubleOrNull() ?: return getString(R.string.environment_plant_undefined)

        val state = when {
            plantTemperature == currentTemperatureInt -> TemperatureState.GOOD
            plantTemperature < currentTemperatureInt -> TemperatureState.MORE
            plantTemperature > currentTemperatureInt -> TemperatureState.LESS
            else -> TemperatureState.UNDEFINED
        }

        val difference = currentTemperatureInt - plantTemperature
        return "${state.asString(requireContext())} (${if (difference > 0) "+" else ""}$difference °C)"
    }
}
