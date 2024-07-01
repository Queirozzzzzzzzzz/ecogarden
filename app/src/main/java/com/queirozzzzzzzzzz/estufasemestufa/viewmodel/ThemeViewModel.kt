package com.queirozzzzzzzzzz.estufasemestufa.viewmodel

import androidx.appcompat.app.AppCompatDelegate
import com.queirozzzzzzzzzz.estufasemestufa.data.Preferences

class ThemeViewModel {
    fun setTheme(darkTheme: Boolean) {
        AppCompatDelegate.setDefaultNightMode(if (darkTheme) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
    }

    fun switchTheme() {
        val darkTheme = Preferences.getDarkTheme()
        Preferences.setDarkTheme(!darkTheme)
        setTheme(!darkTheme)
    }
}
