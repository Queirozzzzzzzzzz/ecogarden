package com.queirozzzzzzzzzz.estufasemestufa.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.EnvironmentData

@Dao
interface EnvironmentDataDao {
    @Query("SELECT * FROM environment_data")
    suspend fun getAllEnvironmentData(): List<EnvironmentData>

    @Query("SELECT * FROM environment_data WHERE environment_id = :environmentId")
    suspend fun getEnvironmentDataByEnvironmentId(environmentId: Int): EnvironmentData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEnvironmentData(environmentData: EnvironmentData)
}
