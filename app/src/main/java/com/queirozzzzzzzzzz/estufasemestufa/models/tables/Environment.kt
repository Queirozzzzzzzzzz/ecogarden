package com.queirozzzzzzzzzz.estufasemestufa.models.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "environment")
data class Environment(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "closed") var closed: Boolean,
    @ColumnInfo(name = "location") var location: String,
    @ColumnInfo(name = "goals") var goals: List<String>,
)
