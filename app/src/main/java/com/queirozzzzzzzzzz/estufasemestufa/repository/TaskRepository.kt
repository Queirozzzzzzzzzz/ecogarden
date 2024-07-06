package com.queirozzzzzzzzzz.estufasemestufa.repository

import android.app.Application
import com.queirozzzzzzzzzz.estufasemestufa.data.dao.TaskDao
import com.queirozzzzzzzzzz.estufasemestufa.data.db.LocalDatabase
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Task
import javax.inject.Inject

class TaskRepository
    @Inject
    constructor(app: Application) {
        private var taskDao: TaskDao

        init {
            val db = LocalDatabase.getInstance(app)
            taskDao = db.taskDao()
        }

        suspend fun getTasksByEnvironmentId(environmentId: Int): List<Task> {
            return taskDao.getTasksByEnvironmentId(environmentId)
        }

        suspend fun insertTask(task: Task) {
            taskDao.insertTask(task)
        }

        suspend fun updateTask(task: Task) {
            val (id, name, days) = task

            taskDao.updateTask(id, name, days)
        }

        suspend fun deleteTask(id: Int) {
            taskDao.deleteTask(id)
        }
    }
