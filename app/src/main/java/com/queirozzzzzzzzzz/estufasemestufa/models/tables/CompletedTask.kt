package com.queirozzzzzzzzzz.estufasemestufa.models.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "completed_task")
data class CompletedTask(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "taskId") var taskId: Int,
    @ColumnInfo(name = "completion_date") var completionDate: Long,
    @ColumnInfo(name = "environment_id") var environmentId: Int,
)
