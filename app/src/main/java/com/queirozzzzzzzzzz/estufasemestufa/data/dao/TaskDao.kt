package com.queirozzzzzzzzzz.estufasemestufa.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.queirozzzzzzzzzz.estufasemestufa.models.DaysList
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task WHERE environment_id = :environmentId")
    suspend fun getTasksByEnvironmentId(environmentId: Int): List<Task>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Query("UPDATE task SET name = :name, days = :days WHERE id = :id")
    suspend fun updateTask(
        id: Int,
        name: String,
        days: DaysList,
    )

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deleteTask(id: Int)
}
