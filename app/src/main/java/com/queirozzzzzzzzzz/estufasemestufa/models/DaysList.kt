package com.queirozzzzzzzzzz.estufasemestufa.models

data class DaysList(
    var sunday: Boolean,
    var monday: Boolean,
    var tuesday: Boolean,
    var wednesday: Boolean,
    var thursday: Boolean,
    var friday: Boolean,
    var saturday: Boolean,
) {
    operator fun get(dayIndex: Int): Boolean {
        return when (dayIndex) {
            0 -> sunday
            1 -> monday
            2 -> tuesday
            3 -> wednesday
            4 -> thursday
            5 -> friday
            6 -> saturday
            else -> throw IllegalArgumentException("Invalid day index")
        }
    }

    operator fun set(
        dayIndex: Int,
        value: Boolean,
    ) {
        when (dayIndex) {
            0 -> sunday = value
            1 -> monday = value
            2 -> tuesday = value
            3 -> wednesday = value
            4 -> thursday = value
            5 -> friday = value
            6 -> saturday = value
        }
    }
}
