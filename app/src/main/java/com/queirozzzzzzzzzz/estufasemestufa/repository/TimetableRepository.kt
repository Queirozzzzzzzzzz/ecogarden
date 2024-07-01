package com.queirozzzzzzzzzz.estufasemestufa.repository

import android.app.Application
import com.queirozzzzzzzzzz.estufasemestufa.data.dao.TimetableDao
import com.queirozzzzzzzzzz.estufasemestufa.data.db.LocalDatabase
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Timetable
import javax.inject.Inject

class TimetableRepository
    @Inject
    constructor(app: Application) {
        private var timetableDao: TimetableDao

        init {
            val db = LocalDatabase.getInstance(app)
            timetableDao = db.timetableDao()
        }

        suspend fun getEnvironmentTimetables(environmentId: Int): List<Timetable> {
            return timetableDao.getTimetablesByEnvironmentId(environmentId)
        }

        suspend fun getTimetableById(timetableId: Int): Timetable? {
            return timetableDao.getTimetableById(timetableId)
        }

        suspend fun insertTimetable(timetable: Timetable) {
            timetableDao.insertTimetable(timetable)
        }

        suspend fun deleteTimetable(timetableId: Int) {
            timetableDao.deleteTimetableById(timetableId)
        }
    }
