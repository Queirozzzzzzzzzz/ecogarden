package com.queirozzzzzzzzzz.estufasemestufa.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.CompletedTask

@Dao
interface CompletedTaskDao {
    @Query("SELECT * FROM completed_task WHERE environment_id = :environmentId")
    suspend fun getCompletedTasksByEnvironmentId(environmentId: Int): List<CompletedTask>

    @Query("SELECT * FROM completed_task WHERE id = :id")
    suspend fun getCompletedTaskById(id: Int): CompletedTask

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompletedTask(completedTask: CompletedTask)
}
