package com.queirozzzzzzzzzz.estufasemestufa.models.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timetable")
data class Timetable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "startTime") var startTime: Long,
    @ColumnInfo(name = "finishTime") var finishTime: Long,
    @ColumnInfo(name = "environment_id") var environmentId: Int,
)
