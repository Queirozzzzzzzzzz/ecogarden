package com.queirozzzzzzzzzz.estufasemestufa.data.converters

import android.net.Uri
import androidx.room.TypeConverter

object UriConverter {
    @TypeConverter
    fun fromUri(uri: Uri?): String? {
        return uri?.toString()
    }

    @TypeConverter
    fun toUri(uriString: String?): Uri? {
        return Uri.parse(uriString)
    }
}
