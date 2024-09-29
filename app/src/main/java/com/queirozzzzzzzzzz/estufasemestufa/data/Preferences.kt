package com.queirozzzzzzzzzz.estufasemestufa.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

object Preferences {
    private var prefs: SharedPreferences? = null

    fun setup(con: Context) {
        prefs = con.getSharedPreferences("estufasemestufa_prefs", MODE_PRIVATE)
    }

    fun setDarkTheme(darkTheme: Boolean) {
        Key.KEY_DARK_THEME.setBoolean(darkTheme)
    }

    fun getDarkTheme(): Boolean {
        return Key.KEY_DARK_THEME.getBoolean() ?: false
    }

    fun setAuthCookie(token: String) {
        Key.KEY_AUTH_TOKEN.setString(token)
    }

    fun getAuthCookie(): String? {
        return Key.KEY_AUTH_TOKEN.getString()
    }

    fun setLoginError(error: String) {
        Key.KEY_LOGIN_ERROR.setString(error)
    }

    fun getLoginError(): String? {
        return Key.KEY_LOGIN_ERROR.getString()
    }

    fun setSignupError(error: String) {
        Key.KEY_SIGNUP_ERROR.setString(error)
    }

    fun getSignupError(): String? {
        return Key.KEY_SIGNUP_ERROR.getString()
    }

    private enum class Key {
        KEY_DARK_THEME,
        KEY_AUTH_TOKEN,
        KEY_LOGIN_ERROR,
        KEY_SIGNUP_ERROR,
        ;

        fun getBoolean(): Boolean? = if (prefs!!.contains(name)) prefs!!.getBoolean(name, false) else null

        fun getFloat(): Float? = if (prefs!!.contains(name)) prefs!!.getFloat(name, 0f) else null

        fun getInt(): Int? = if (prefs!!.contains(name)) prefs!!.getInt(name, 0) else null

        fun getLong(): Long? = if (prefs!!.contains(name)) prefs!!.getLong(name, 0) else null

        fun getString(): String? = if (prefs!!.contains(name)) prefs!!.getString(name, "") else null

        fun setBoolean(value: Boolean?) = value?.let { prefs!!.edit { putBoolean(name, value) } } ?: remove()

        fun setFloat(value: Float?) = value?.let { prefs!!.edit { putFloat(name, value) } } ?: remove()

        fun setInt(value: Int?) = value?.let { prefs!!.edit { putInt(name, value) } } ?: remove()

        fun setLong(value: Long?) = value?.let { prefs!!.edit { putLong(name, value) } } ?: remove()

        fun setString(value: String?) = value?.let { prefs!!.edit { putString(name, value) } } ?: remove()

        fun exists(): Boolean = prefs!!.contains(name)

        fun remove() = prefs!!.edit { remove(name) }
    }
}
