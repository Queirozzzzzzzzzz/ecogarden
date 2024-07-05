package com.queirozzzzzzzzzz.estufasemestufa.utils

import android.graphics.Bitmap
import android.net.Uri
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Plant
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Timetable

object TemporaryManageEnvironmentData {
    var isEditing: Boolean = false
    var name: String? = null
    var closed: Boolean? = null
    var biome: String? = null
    var picturePath: Uri? = null
    var goals: List<String>? = emptyList()
    var plants: List<Plant>? = emptyList()
    var selectedPlant: String? = null
    var timetables: List<Timetable>? = emptyList()
    var selectedTimetable: String? = null
    var environmentId: Int? = null

    fun reset() {
        isEditing = false
        name = null
        closed = null
        biome = null
        picturePath = null
        goals = emptyList()
        plants = emptyList()
        selectedPlant = null
        timetables = emptyList()
        selectedTimetable = null
        environmentId = null
    }
}
