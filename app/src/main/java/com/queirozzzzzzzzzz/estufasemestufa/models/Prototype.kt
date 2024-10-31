package com.queirozzzzzzzzzz.estufasemestufa.models

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import com.queirozzzzzzzzzz.estufasemestufa.api.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class Prototype {
    private val service = Service().getService()

    suspend fun synchronize(authCookie: String): JsonObject {
        return JsonObject()
    }

}