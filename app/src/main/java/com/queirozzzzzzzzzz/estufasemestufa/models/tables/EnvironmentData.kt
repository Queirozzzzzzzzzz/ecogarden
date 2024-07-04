package com.queirozzzzzzzzzz.estufasemestufa.models.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "environment_data")
data class EnvironmentData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "date") var date: Date?,
    @ColumnInfo(name = "humidity") var humidity: String?,
    @ColumnInfo(name = "ph") var ph: Int?,
    @ColumnInfo(name = "light_intensity") var lightIntensity: String?,
    @ColumnInfo(name = "light_duration") var lightDuration: Int?,
    @ColumnInfo(name = "soil_conductivity") var soilConductivity: Double?,
    @ColumnInfo(name = "soil_salinity") var soilSalinity: Double?,
    @ColumnInfo(name = "temperature") var temperature: Int?,
    @ColumnInfo(name = "environment_id") var environmentId: Int,
)
