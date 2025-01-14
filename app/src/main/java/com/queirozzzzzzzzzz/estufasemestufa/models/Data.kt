package com.queirozzzzzzzzzz.estufasemestufa.models

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import com.queirozzzzzzzzzz.estufasemestufa.api.Service
import com.queirozzzzzzzzzz.estufasemestufa.data.Preferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class Data {
    private val service = Service().getService()
    private val gson = GsonBuilder().serializeNulls().create()

    suspend fun getNew(): JsonObject {
        return withContext(Dispatchers.IO) {
            try {
                service.getNewData().let{ res ->
                    if (res.isSuccessful) {
                        res.body()?.string()?.let { responseString ->
                            gson.fromJson(responseString, JsonObject::class.java)
                        } ?: JsonObject()
                    } else {
                        if ( res.code() == 404 ) {
                            Preferences.setAuthCookie("")
                        }

                        Log.e("DATA FETCH ERROR", res.errorBody()?.string() ?: "Unknown error")
                        JsonObject()
                    }
                }
            } catch (e: JsonSyntaxException) {
                Log.e("JSON PARSE ERROR", "Failed to parse JSON: ${e.message}")
                JsonObject()
            }
        }
    }

}