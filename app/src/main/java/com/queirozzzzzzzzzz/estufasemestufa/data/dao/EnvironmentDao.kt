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

    @Query("SELECT * FROM environment WHERE id = :environmentId")
    suspend fun getEnvironmentById(environmentId: Int): Environment?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEnvironment(environment: Environment)

    @Query("UPDATE environment SET name = :name, closed = :closed, location = :location, goals = :goals WHERE id = :environmentId")
    suspend fun updateEnvironment(
        environmentId: Int,
        name: String,
        closed: Boolean,
        location: String,
        goals: List<String>,
    )

    @Query("DELETE FROM environment")
    suspend fun deleteEnvironments()
}
