package com.queirozzzzzzzzzz.estufasemestufa.models.tables

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "picture")
data class Picture(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "path") var path: Uri,
    @ColumnInfo(name = "created_at") var createdAt: Long,
    @ColumnInfo(name = "environment_id") var environmentId: Int,
)
