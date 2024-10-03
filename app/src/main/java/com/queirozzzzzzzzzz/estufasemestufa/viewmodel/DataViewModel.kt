package com.queirozzzzzzzzzz.estufasemestufa.viewmodel

import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.queirozzzzzzzzzz.estufasemestufa.models.Data

class DataViewModel: ViewModel() {
    private val data = Data()

    suspend fun getNew(): JsonObject {
        return data.getNew()
    }
}