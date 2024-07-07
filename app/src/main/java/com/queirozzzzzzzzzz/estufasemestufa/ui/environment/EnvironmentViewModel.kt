package com.queirozzzzzzzzzz.estufasemestufa.ui.environment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.CompletedTask
import com.queirozzzzzzzzzz.estufasemestufa.repository.CompletedTaskRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.EnvironmentRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.PictureRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.TaskRepository
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryData
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

class EnvironmentViewModel
    @Inject
    constructor(
        private val environmentRepository: EnvironmentRepository,
        private val pictureRepository: PictureRepository,
        private val taskRepository: TaskRepository,
        private val completedTaskRepository: CompletedTaskRepository,
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

        fun completeTask(id: Int) {
            viewModelScope.launch {
                val task = taskRepository.getTaskById(id)
                val todayDate = LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC).toEpochMilli()
                val completedTask =
                    CompletedTask(
                        0,
                        task.id,
                        todayDate,
                        TemporaryData.selectedEnvironmentId!!,
                    )
                completedTaskRepository.insertCompletedTask(completedTask)
            }
        }
    }
