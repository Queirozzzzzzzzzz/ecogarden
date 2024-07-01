package com.queirozzzzzzzzzz.estufasemestufa.ui.home

import androidx.lifecycle.ViewModel
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Environment
import com.queirozzzzzzzzzz.estufasemestufa.repository.EnvironmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val envRepo: EnvironmentRepository,
    ) : ViewModel() {
        // Queries
        suspend fun getEnvironments(): List<Environment> {
            val environments = envRepo.getAllEnvironments()
            return environments
        }

        suspend fun getEnvironmentById(environmentId: Int): Environment? {
            val environment = envRepo.getEnvironmentById(environmentId)
            return environment
        }

        suspend fun insertEnvironment(environment: Environment) {
            envRepo.insertEnvironment(environment)
        }

        suspend fun deleteEnvironments() {
            envRepo.deleteEnvironments()
        }
    }
