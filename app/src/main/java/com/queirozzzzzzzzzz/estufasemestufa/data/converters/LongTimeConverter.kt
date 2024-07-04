package com.queirozzzzzzzzzz.estufasemestufa.data.converters

import androidx.room.TypeConverter

object LongTimeConverter {
    @TypeConverter
    fun toLongTime(hour: Int, minutes: Int): Long {
        return (hour * 60 + minutes).toLong()
    }

    @TypeConverter
    fun fromLongTime(time: Long): String {
        val hours = time / 60
        val minutes = time % 60
        return String.format("%02d:%02d", hours, minutes)
    }
}