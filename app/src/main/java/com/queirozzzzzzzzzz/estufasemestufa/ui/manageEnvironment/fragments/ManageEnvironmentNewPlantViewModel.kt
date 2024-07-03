package com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments

import androidx.lifecycle.ViewModel
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Plant
import com.queirozzzzzzzzzz.estufasemestufa.repository.PlantRepository
import javax.inject.Inject

class ManageEnvironmentNewPlantViewModel
    @Inject
    constructor(private val plantRepository: PlantRepository) :
    ViewModel() {
        suspend fun getAllPlants(): List<Plant> {
            return plantRepository.getAllPlants()
        }
    }
