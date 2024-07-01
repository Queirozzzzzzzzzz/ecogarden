package com.queirozzzzzzzzzz.estufasemestufa.ui.home

import androidx.lifecycle.ViewModel
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
        suspend fun hasEnvironments(): Boolean {
            val environments = envRepo.getAllEnvironments()
            return environments.isNotEmpty()
        }
    }
