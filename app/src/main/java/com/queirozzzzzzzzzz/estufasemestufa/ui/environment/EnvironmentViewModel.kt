package com.queirozzzzzzzzzz.estufasemestufa.ui.environment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.queirozzzzzzzzzz.estufasemestufa.repository.EnvironmentRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.PictureRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.TaskRepository
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryData
import kotlinx.coroutines.launch
import javax.inject.Inject

class EnvironmentViewModel
    @Inject
    constructor(
        private val environmentRepository: EnvironmentRepository,
        private val pictureRepository: PictureRepository,
        private val taskRepository: TaskRepository,
    ) : ViewModel() {
        fun setEnvironmentName(onCompleted: () -> Unit) {
            viewModelScope.launch {
                val environment =
                    environmentRepository.getEnvironmentById(TemporaryData.selectedEnvironmentId!!)
                TemporaryData.selectedEnvironmentName = environment?.name

                return@launch onCompleted()
            }
        }

        fun deletePicture() {
            viewModelScope.launch {
                pictureRepository.deletePicture(TemporaryData.selectedPictureId!!)
            }
        }

        fun deleteTask(id: Int) {
            viewModelScope.launch {
                taskRepository.deleteTask(id)
            }
        }
    }
