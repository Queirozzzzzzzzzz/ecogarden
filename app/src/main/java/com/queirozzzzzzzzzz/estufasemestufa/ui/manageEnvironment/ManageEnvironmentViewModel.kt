package com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Environment
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Picture
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Plant
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Timetable
import com.queirozzzzzzzzzz.estufasemestufa.repository.EnvironmentRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.PictureRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.PlantRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.TimetableRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ManageEnvironmentViewModel
    @Inject
    constructor(
        private val environmentRepository: EnvironmentRepository,
        private val pictureRepository: PictureRepository,
        private val plantRepository: PlantRepository,
        private val timetableRepository: TimetableRepository,
    ) : ViewModel() {
        private var environmentId: Int? = null
        private var name: String? = null
        private var closed: Boolean? = null
        private var biome: String? = null
        private var picture: Bitmap? = null
        private var goals: List<String>? = null
        private var plants: List<Plant>? = null
        private var timetables: List<Timetable>? = null

        // Functions
        fun setName(name: String) {
            this.name = name
        }

        fun setClosed(closed: Boolean) {
            this.closed = closed
        }

        fun setBiome(biome: String) {
            this.biome = biome
        }

        fun setPicture(picture: Bitmap) {
            this.picture = picture
        }

        fun setGoals(goals: List<String>) {
            this.goals = goals
        }

        fun addPlant(plant: Plant) {
            // TODO: Add plant
        }

        fun addTimetable(timetable: Timetable) {
            // TODO: Add timetable
        }

        fun fakeSaveEnvironment() {
            val environment = Environment(0, name!!, closed!!, biome!!, goals!!)
            viewModelScope.launch {
                environmentId = environmentRepository.insertEnvironment(environment).toInt()
                println("Environment ID: $environmentId")
                environmentRepository.deleteEnvironmentById(environmentId!!)
            }
        }

        fun createEnvironment() {
            val environment = Environment(0, name!!, closed!!, biome!!, goals!!)
            viewModelScope.launch {
                environmentId = environmentRepository.insertEnvironment(environment).toInt()

                savePicture()
                savePlants()
                saveTimetables()
            }
        }

        suspend fun savePicture() {
            // TODO: Save picture
        }

        suspend fun savePlants() {
            // TODO: Save plants
        }

        suspend fun saveTimetables() {
            // TODO: Save timetables
        }

        // Queries
        suspend fun insertEnvironment(environment: Environment) {
            environmentRepository.insertEnvironment(environment)
        }

        suspend fun insertPicture(picture: Picture) {
            pictureRepository.insertPicture(picture)
        }

        suspend fun insertPlant(plant: Plant) {
            plantRepository.insertPlant(plant)
        }

        suspend fun insertTimetable(timetable: Timetable) {
            timetableRepository.insertTimetable(timetable)
        }
    }
