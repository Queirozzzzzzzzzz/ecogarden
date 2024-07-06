package com.queirozzzzzzzzzz.estufasemestufa.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Timetable

@Dao
interface TimetableDao {
    @Query("SELECT * FROM timetable WHERE environment_id = :environmentId")
    suspend fun getTimetablesByEnvironmentId(environmentId: Int): List<Timetable>

    @Query("SELECT * FROM timetable WHERE id = :id")
    suspend fun getTimetableById(id: Int): Timetable?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimetable(timetable: Timetable)

    @Query("UPDATE timetable SET startTime = :startTime, finishTime = :finishTime WHERE id = :id")
    suspend fun updateTimetable(
        id: Int,
        startTime: Long,
        finishTime: Long,
    )

    @Query("DELETE FROM timetable WHERE id = :id")
    suspend fun deleteTimetableById(id: Int)

    @Query("DELETE FROM timetable WHERE environment_id = :environmentId")
    suspend fun deleteTimetablesByEnvironmentId(environmentId: Int)
}
