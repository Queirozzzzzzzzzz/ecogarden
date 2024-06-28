package com.queirozzzzzzzzzz.estufasemestufa.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.queirozzzzzzzzzz.estufasemestufa.models.DaysList

object DaysListTypeConverter {
    @TypeConverter
    fun fromDaysList(value: String?): DaysList? {
        val listType = object : TypeToken<DaysList>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toDaysList(list: DaysList?): String? {
        return Gson().toJson(list)
    }
}
