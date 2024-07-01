package com.queirozzzzzzzzzz.estufasemestufa.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Picture

@Dao
interface PictureDao {
    @Query("SELECT * FROM picture WHERE environment_id = :environmentId")
    suspend fun getPicturesByEnvironmentId(environmentId: Int): List<Picture>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(picture: Picture)

    @Query("DELETE FROM picture WHERE id = :id")
    suspend fun deletePictureById(id: Int)
}
