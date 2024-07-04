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
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.biome
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.closed
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.goals
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.name
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

        // Functions
        fun createEnvironment() {
            val environment = Environment(0, name!!, closed!!, biome, goals)
            viewModelScope.launch {
                TemporaryManageEnvironmentData.environmentId = environmentRepository.insertEnvironment(environment).toInt()

                savePicture()
                savePlants()
                saveTimetables()
            }
        }

        suspend fun savePicture() {
            // TODO: Save picture
        }

        suspend fun savePlants() {
            val plants = TemporaryManageEnvironmentData.plants
            for (plant in plants!!) {
                plant.environmentId = TemporaryManageEnvironmentData.environmentId!!
                plantRepository.insertPlant(plant)
            }
        }

        suspend fun saveTimetables() {
            val timetables = TemporaryManageEnvironmentData.timetables
            for (timetable in timetables!!) {
                timetable.environmentId = TemporaryManageEnvironmentData.environmentId!!
                timetableRepository.insertTimetable(timetable)
            }
        }
    }
