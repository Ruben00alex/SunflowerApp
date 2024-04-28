package com.example.sunflowerapp.utils

import android.content.Context
import com.example.sunflowerapp.PlantRepository

object RepositoryProvider {
    private var plantRepository: PlantRepository? = null

    fun providePlantRepository(context: Context): PlantRepository {
        return plantRepository ?: synchronized(this) {
            plantRepository ?: PlantRepository(context.applicationContext).also {
                plantRepository = it
            }
        }
    }
}