package com.queirozzzzzzzzzz.estufasemestufa.data.converters

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object MilissecondsToDateConverter {
    private val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    @TypeConverter
    fun fromMilissecondsToDate(milisseconds: Long): String {
        return formatter.format(Date(milisseconds))
    }

    @TypeConverter
    fun fromDateToMilisseconds(dateString: String): Long {
        return formatter.parse(dateString)?.time ?: 0L
    }
}
