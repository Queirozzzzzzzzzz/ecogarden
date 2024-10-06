package com.queirozzzzzzzzzz.estufasemestufa.models.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "environment")
data class EnvironmentPlant(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "light_intensity") var light_intensity: String,
    @ColumnInfo(name = "humidity") var humidity: String,
    @ColumnInfo(name = "ph") var ph: String,
    @ColumnInfo(name = "temperature") var temperature: String,
    )
