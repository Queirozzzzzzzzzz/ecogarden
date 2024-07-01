package com.queirozzzzzzzzzz.estufasemestufa.repository

import android.app.Application
import com.queirozzzzzzzzzz.estufasemestufa.data.dao.PlantDao
import com.queirozzzzzzzzzz.estufasemestufa.data.db.LocalDatabase
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Plant
import javax.inject.Inject

class PlantRepository
    @Inject
    constructor(app: Application) {
        private var plantDao: PlantDao

        init {
            val db = LocalDatabase.getInstance(app)
            plantDao = db.plantDao()
        }

        suspend fun getPlantsByEnvironmentId(environmentId: Int): List<Plant> {
            return plantDao.getPlantsByEnvironmentId(environmentId)
        }

        suspend fun getPlantById(plantId: Int): Plant? {
            return plantDao.getPlantById(plantId)
        }

        suspend fun insertPlant(plant: Plant) {
            plantDao.insertPlant(plant)
        }

        suspend fun deletePlant(plantId: Int) {
            plantDao.deletePlantById(plantId)
        }
    }
