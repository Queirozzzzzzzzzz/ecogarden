package com.queirozzzzzzzzzz.estufasemestufa.models.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "environment_data")
data class EnvironmentData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "date") var date: Long?,
    @ColumnInfo(name = "soil_humidity") var humidity: String?,
    @ColumnInfo(name = "ph") var ph: Int?,
    @ColumnInfo(name = "light_intensity") var lightIntensity: String?,
    @ColumnInfo(name = "air_temperature") var temperature: Int?,
    @ColumnInfo(name = "environment_id") var environmentId: Int,
)
