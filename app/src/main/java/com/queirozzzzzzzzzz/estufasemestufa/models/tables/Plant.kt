package com.queirozzzzzzzzzz.estufasemestufa.models.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "humidity") var humidity: String?,
    @ColumnInfo(name = "ph") var ph: Int?,
    @ColumnInfo(name = "light_intensity") var lightIntensity: String?,
    @ColumnInfo(name = "temperature") var temperature: Int?,
    @ColumnInfo(name = "environment_id") var environmentId: Int,
)
