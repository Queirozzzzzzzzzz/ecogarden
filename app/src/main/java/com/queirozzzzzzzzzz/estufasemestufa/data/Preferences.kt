package com.queirozzzzzzzzzz.estufasemestufa.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

object Preferences {
    const val PREF_NAME: String = "prefs"

    private var prefs: SharedPreferences? = null

    fun setup(con: Context) {
        prefs = con.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
    }

    fun setDarkTheme(darkTheme: Boolean) {
        Key.KEY_DARK_THEME.setBoolean(darkTheme)
    }

    fun getDarkTheme(): Boolean {
        return Key.KEY_DARK_THEME.getBoolean() ?: false
    }

    private enum class Key {
        KEY_DARK_THEME, ;

        fun getBoolean(): Boolean? =
            if (prefs!!.contains(name)) prefs!!.getBoolean(name, false) else null

        fun getFloat(): Float? = if (prefs!!.contains(name)) prefs!!.getFloat(name, 0f) else null

        fun getInt(): Int? = if (prefs!!.contains(name)) prefs!!.getInt(name, 0) else null

        fun getLong(): Long? = if (prefs!!.contains(name)) prefs!!.getLong(name, 0) else null

        fun getString(): String? = if (prefs!!.contains(name)) prefs!!.getString(name, "") else null

        fun setBoolean(value: Boolean?) =
            value?.let { prefs!!.edit { putBoolean(name, value) } } ?: remove()

        fun setFloat(value: Float?) =
            value?.let { prefs!!.edit { putFloat(name, value) } } ?: remove()

        fun setInt(value: Int?) = value?.let { prefs!!.edit { putInt(name, value) } } ?: remove()

        fun setLong(value: Long?) = value?.let { prefs!!.edit { putLong(name, value) } } ?: remove()

        fun setString(value: String?) =
            value?.let { prefs!!.edit { putString(name, value) } } ?: remove()

        fun exists(): Boolean = prefs!!.contains(name)

        fun remove() = prefs!!.edit { remove(name) }
    }
}
