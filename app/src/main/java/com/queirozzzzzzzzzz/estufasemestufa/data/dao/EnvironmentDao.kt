package com.queirozzzzzzzzzz.estufasemestufa.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Environment

@Dao
interface EnvironmentDao {
    @Query("SELECT * FROM environment")
    suspend fun getAllEnvironments(): List<Environment>

    @Query("SELECT * FROM environment WHERE id = :id")
    suspend fun getEnvironmentById(id: Int): Environment?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEnvironment(environment: Environment): Long

    @Query("UPDATE environment SET name = :name, closed = :closed, biome = :biome, goals = :goals WHERE id = :id")
    suspend fun updateEnvironment(
        id: Int,
        name: String,
        closed: Boolean,
        biome: String,
        goals: List<String>,
    )

    @Query("DELETE FROM environment WHERE id = :id")
    suspend fun deleteEnvironmentById(id: Int)
}
