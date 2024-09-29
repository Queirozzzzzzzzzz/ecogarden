package com.queirozzzzzzzzzz.estufasemestufa.models

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.queirozzzzzzzzzz.estufasemestufa.api.Service
import com.queirozzzzzzzzzz.estufasemestufa.data.Preferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class Account {
    private val service  = Service().getService()

    suspend fun login(email: String, password: String): Boolean {
        val res = withContext(Dispatchers.IO) {
            service.login(mapOf("email" to email, "password" to password))
        }
        return res.isSuccessful.also { success ->
            if (success){
                res.body()?.let { resBody ->
                    val jsonObject = Gson().fromJson(resBody.string(), JsonObject::class.java)
                    val token = jsonObject.get("token").asString
                    Preferences.setAuthCookie("token=$token")
                }
            } else {
                Preferences.setAuthCookie("")
                Preferences.setLoginError(getErrorMessage(res.errorBody()?.string()))
                res.errorBody()?.string()?.let { Log.e("LOGIN ERROR", it) }
            }
        }
    }

    suspend fun signup(email: String, password: String): Boolean {
        val res = withContext(Dispatchers.IO) {
            service.signup(mapOf("email" to email, "password" to password))
        }
        return res.isSuccessful.also { success ->
            if (success){
                login(email, password)
            } else {
                Preferences.setSignupError(getErrorMessage(res.errorBody()?.string()))
                res.errorBody()?.string()?.let { Log.e("SIGNUP ERROR", it) }
            }
        }
    }

    private fun getErrorMessage(errorBody: String?): String {
        if (errorBody != null) {
            val errorJson = JSONObject(errorBody)
            return errorJson.getString("message")
        }

        return "Erro desconhecido"
    }

}