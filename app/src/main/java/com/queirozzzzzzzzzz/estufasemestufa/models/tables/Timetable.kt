package com.queirozzzzzzzzzz.estufasemestufa.models.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "timetable")
data class Timetable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "startTime") var startTime: Date,
    @ColumnInfo(name = "finishTime") var finishTime: Date,
    @ColumnInfo(name = "environment_id") var environmentId: Int,
)
