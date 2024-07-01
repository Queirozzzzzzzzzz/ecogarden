package com.queirozzzzzzzzzz.estufasemestufa.utils

import android.graphics.Bitmap
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Plant
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Timetable

object TemporaryFormData {
    var isEditing: Boolean = false
    var name: String? = null
    var closed: Boolean? = null
    var biome: String? = null
    var picture: Bitmap? = null
    var goals: List<String>? = null
    var plants: List<Plant>? = null
    var timetables: List<Timetable>? = null
}
