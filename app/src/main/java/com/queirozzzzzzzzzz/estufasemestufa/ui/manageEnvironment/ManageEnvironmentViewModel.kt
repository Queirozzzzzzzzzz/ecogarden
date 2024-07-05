package com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Environment
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Picture
import com.queirozzzzzzzzzz.estufasemestufa.repository.EnvironmentRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.PictureRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.PlantRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.TimetableRepository
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.biome
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.closed
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.environmentId
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.goals
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.name
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.picturePath
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.plants
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.timetables
import kotlinx.coroutines.launch
import javax.inject.Inject

class ManageEnvironmentViewModel
@Inject constructor(
    private val environmentRepository: EnvironmentRepository,
    private val pictureRepository: PictureRepository,
    private val plantRepository: PlantRepository,
    private val timetableRepository: TimetableRepository,
) : ViewModel() {

    // Functions
    fun createEnvironment(onCompleted: () -> Unit) {
        val environment = Environment(0, name!!, closed!!, biome, goals)
        viewModelScope.launch {
            environmentId = environmentRepository.insertEnvironment(environment).toInt()

            savePicture()
            savePlants()
            saveTimetables()

            return@launch onCompleted()
        }
    }

    private suspend fun savePicture() {
        try {
            val picture = Picture(
                0, picturePath!!, environmentId!!
            )
            pictureRepository.insertPicture(picture)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun savePlants() {
        try {
            val plants = plants
            for (plant in plants!!) {
                plant.environmentId = environmentId!!
                plantRepository.insertPlant(plant)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun saveTimetables() {
        try {
            val timetables = timetables
            for (timetable in timetables!!) {
                timetable.environmentId = environmentId!!
                timetableRepository.insertTimetable(timetable)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
