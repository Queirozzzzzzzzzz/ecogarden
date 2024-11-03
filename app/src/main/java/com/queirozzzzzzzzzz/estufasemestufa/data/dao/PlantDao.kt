package com.queirozzzzzzzzzz.estufasemestufa.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Plant

@Dao
interface PlantDao {
    @Query("SELECT * FROM plant;")
    suspend fun getAllPlants(): List<Plant>

    @Query("SELECT * FROM plant WHERE environment_id = :environmentId;")
    suspend fun getPlantsByEnvironmentId(environmentId: Int): List<Plant>

    @Query("SELECT * FROM plant WHERE id = :id;")
    suspend fun getPlantById(id: Int): Plant?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlant(plant: Plant)

    @Query(
        "UPDATE plant SET name = :name, soil_humidity = :soilHumidity, ph = :ph, light_intensity = :lightIntensity, air_temperature = :airTemperature WHERE id = :id;",
    )
    suspend fun updatePlant(
        id: Int,
        name: String,
        soilHumidity: String?,
        ph: Int?,
        lightIntensity: String?,
        airTemperature: Double?,
    )

    @Query("DELETE FROM plant WHERE id = :id;")
    suspend fun deletePlantById(id: Int)

    @Query("DELETE FROM plant WHERE environment_id = :environmentId;")
    suspend fun deletePlantsByEnvironmentId(environmentId: Int)
}
