package com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Environment
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Picture
import com.queirozzzzzzzzzz.estufasemestufa.repository.EnvironmentRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.PictureRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.PlantRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.TimetableRepository
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryData.selectedEnvironmentId
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.biome
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.closed
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.deletedPlants
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.deletedTimetables
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.environmentId
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.goals
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.isEditing
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.name
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.picturePath
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.plants
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData.timetables
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
        fun createEnvironment(onCompleted: () -> Unit) {
            val environment = Environment(0, name!!, closed!!, biome, goals)
            viewModelScope.launch {
                if (isEditing) {
                    editEnvironment(environment)
                } else {
                    environmentId = environmentRepository.insertEnvironment(environment).toInt()
                    savePicture()
                }

                savePlants()
                saveTimetables()

                return@launch onCompleted()
            }
        }

        suspend fun editEnvironment(environment: Environment) {
            environment.id = selectedEnvironmentId!!
            environmentRepository.updateEnvironment(environment)
        }

        private suspend fun savePicture() {
            try {
                val picture =
                    Picture(
                        0,
                        picturePath!!,
                        environmentId!!,
                    )
                pictureRepository.insertPicture(picture)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private suspend fun savePlants() {
            try {
                if (isEditing) {
                    val plants = plants

                    for (plant in plants!!) {
                        if (plant.id != 0) {
                            plantRepository.updatePlant(plant)
                        } else {
                            plant.environmentId = environmentId!!
                            plantRepository.insertPlant(plant)
                        }
                    }
                    for (plant in deletedPlants!!) {
                        plantRepository.deletePlant(plant.id)
                    }
                } else {
                    val plants = plants
                    for (plant in plants!!) {
                        plant.environmentId = environmentId!!
                        plantRepository.insertPlant(plant)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private suspend fun saveTimetables() {
            try {
                if (isEditing) {
                    val timetables = timetables

                    for (timetable in timetables!!) {
                        if (timetable.id != 0) {
                            timetableRepository.updateTimetable(timetable)
                        } else {
                            timetable.environmentId = environmentId!!
                            timetableRepository.insertTimetable(timetable)
                        }
                    }

                    for (timetable in deletedTimetables!!) {
                        timetableRepository.deleteTimetable(timetable.id)
                    }
                } else {
                    val timetables = timetables
                    for (timetable in timetables!!) {
                        timetable.environmentId = environmentId!!
                        timetableRepository.insertTimetable(timetable)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        suspend fun setEditEnvironment() {
            val environment = environmentRepository.getEnvironmentById(selectedEnvironmentId!!)

            isEditing = true

            if (environment != null) {
                environmentId = environment.id
                name = environment.name
                closed = environment.closed
                biome = environment.biome
                goals = environment.goals
            }

            plants = plantRepository.getPlantsByEnvironmentId(environment!!.id)
            timetables = timetableRepository.getEnvironmentTimetables(environment.id)
        }

        suspend fun deleteEnvironment(onCompleted: () -> Unit) {
            viewModelScope.launch {
                environmentRepository.deleteEnvironmentById(selectedEnvironmentId!!)
                pictureRepository.deletePicturesByEnvironmentId(selectedEnvironmentId!!)
                plantRepository.deletePlantsByEnvironmentId(selectedEnvironmentId!!)
                timetableRepository.deleteTimetablesByEnvironmentId(selectedEnvironmentId!!)

                return@launch onCompleted()
            }
        }
    }
