package com.queirozzzzzzzzzz.estufasemestufa.viewmodel

import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.queirozzzzzzzzzz.estufasemestufa.models.Data
import com.queirozzzzzzzzzz.estufasemestufa.models.Prototype

class PrototypeViewModel: ViewModel() {
    private val prototype = Prototype()

    suspend fun synchronize(cookie: String): JsonObject {
        return prototype.synchronize(cookie)
    }
}