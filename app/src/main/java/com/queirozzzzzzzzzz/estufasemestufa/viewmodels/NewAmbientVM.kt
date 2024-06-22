package com.queirozzzzzzzzzz.estufasemestufa.viewmodels

import android.graphics.Bitmap

class NewEnvironmentVM {
    // Place
    var isOpen: Boolean = false
    var location: String = ""
    var picture: Bitmap? = null

    // Goal
    var goals: ArrayList<String> = ArrayList()

    // Plants
    var plants: ArrayList<ArrayList<String>> = ArrayList()

    // Timetables
    var timetables: ArrayList<ArrayList<String>> = ArrayList()
}