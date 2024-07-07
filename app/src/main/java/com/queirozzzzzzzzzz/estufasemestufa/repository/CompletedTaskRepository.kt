package com.queirozzzzzzzzzz.estufasemestufa.repository

import android.app.Application
import com.queirozzzzzzzzzz.estufasemestufa.data.dao.CompletedTaskDao
import com.queirozzzzzzzzzz.estufasemestufa.data.db.LocalDatabase
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.CompletedTask
import javax.inject.Inject

class CompletedTaskRepository
    @Inject
    constructor(app: Application) {
        private var completedTaskDao: CompletedTaskDao

        init {
            val db = LocalDatabase.getInstance(app)
            completedTaskDao = db.completedTaskDao()
        }

        suspend fun getCompletedTasksByEnvironmentId(environmentId: Int): List<CompletedTask> {
            return completedTaskDao.getCompletedTasksByEnvironmentId(environmentId)
        }

        suspend fun getCompletedTaskById(id: Int): CompletedTask {
            return completedTaskDao.getCompletedTaskById(id)
        }

        suspend fun insertCompletedTask(completedTask: CompletedTask) {
            completedTaskDao.insertCompletedTask(completedTask)
        }
    }
