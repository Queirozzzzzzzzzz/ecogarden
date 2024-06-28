package com.queirozzzzzzzzzz.estufasemestufa.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.queirozzzzzzzzzz.estufasemestufa.data.converters.DateTypeConverter
import com.queirozzzzzzzzzz.estufasemestufa.data.converters.DaysListTypeConverter
import com.queirozzzzzzzzzz.estufasemestufa.data.converters.StringListConverter
import com.queirozzzzzzzzzz.estufasemestufa.data.dao.CompletedTaskDao
import com.queirozzzzzzzzzz.estufasemestufa.data.dao.EnvironmentDao
import com.queirozzzzzzzzzz.estufasemestufa.data.dao.EnvironmentDataDao
import com.queirozzzzzzzzzz.estufasemestufa.data.dao.PictureDao
import com.queirozzzzzzzzzz.estufasemestufa.data.dao.PlantDao
import com.queirozzzzzzzzzz.estufasemestufa.data.dao.TaskDao
import com.queirozzzzzzzzzz.estufasemestufa.data.dao.TimetableDao
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.CompletedTask
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Environment
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.EnvironmentData
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Picture
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Plant
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Task
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Timetable

@Database(
    entities = [CompletedTask::class, Environment::class, EnvironmentData::class, Picture::class, Plant::class, Task::class, Timetable::class],
    version = 6,
)
@TypeConverters(DateTypeConverter::class, StringListConverter::class, DaysListTypeConverter::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun completedTaskDao(): CompletedTaskDao

    abstract fun environmentDao(): EnvironmentDao

    abstract fun environmentDataDao(): EnvironmentDataDao

    abstract fun pictureDao(): PictureDao

    abstract fun plantDao(): PlantDao

    abstract fun taskDao(): TaskDao

    abstract fun timetableDao(): TimetableDao

    companion object {
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(con: Context): LocalDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(
                            con.applicationContext, LocalDatabase::class.java, "estufa_sem_estufa_db",
                        ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
