package com.queirozzzzzzzzzz.estufasemestufa.repository

import android.app.Application
import com.queirozzzzzzzzzz.estufasemestufa.data.dao.PictureDao
import com.queirozzzzzzzzzz.estufasemestufa.data.db.LocalDatabase
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Picture
import javax.inject.Inject

class PictureRepository
    @Inject
    constructor(app: Application) {
        private var pictureDao: PictureDao

        init {
            val db = LocalDatabase.getInstance(app)
            pictureDao = db.pictureDao()
        }

        suspend fun getEnvironmentPictures(environmentId: Int): List<Picture> {
            return pictureDao.getPicturesByEnvironmentId(environmentId)
        }

        suspend fun insertPicture(picture: Picture) {
            pictureDao.insertPicture(picture)
        }

        suspend fun deletePicture(pictureId: Int) {
            pictureDao.deletePictureById(pictureId)
        }
    }
