package com.queirozzzzzzzzzz.estufasemestufa.models.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.queirozzzzzzzzzz.estufasemestufa.models.DaysList

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "days") var days: DaysList,
    @ColumnInfo(name = "environment_id") var environmentId: Int,
) {
    var completionDate: Long = 0
}
