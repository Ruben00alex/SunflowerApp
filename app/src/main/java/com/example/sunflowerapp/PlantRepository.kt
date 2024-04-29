package com.example.sunflowerapp


import android.content.Context
import android.util.Log
import com.example.sunflowerapp.models.Plant
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PlantRepository(private val context: Context) {
    private val plants: List<Plant>
    private var gardenPlants: MutableList<Plant>

    init {
        plants = readPlantsJson(context)
        gardenPlants = readGardenJson(context).toMutableList()
    }
    fun waterPlantById(plantId: String): List<Plant> {
        val plantIndex = gardenPlants.indexOfFirst { it.id == plantId }
        if (plantIndex != -1) {
            val updatedPlant = gardenPlants[plantIndex].copy(lastWateredDate = getCurrentDate())

            val newGardenPlants = gardenPlants.toMutableList()
            newGardenPlants[plantIndex] = updatedPlant

            // Update the JSON file with the new list
            writeGardenJson(context, newGardenPlants)

            gardenPlants = newGardenPlants

            return newGardenPlants.toList()
        } else {
            return gardenPlants.toList()
        }
    }
    fun getPlants(): List<Plant> = plants

    fun getGardenPlants(): List<Plant> = gardenPlants

    fun addPlantToGarden(plant: Plant): List<Plant> {
        if (gardenPlants.any { it.id == plant.id }) {
            Log.println(Log.INFO, "PlantRepository", "Plant already in garden")
            return gardenPlants
        }

        val plantWithDate = plant.copy(plantedDate = getCurrentDate())
        gardenPlants.add(plantWithDate)
        writeGardenJson(context, gardenPlants) // context is now accessible
        return gardenPlants
    }

    fun removePlantFromGarden(plant: Plant): List<Plant> {
        //we will remove by id:
        gardenPlants = gardenPlants.filter { it.id != plant.id }.toMutableList()
        Log.println(Log.INFO, "PlantRepository", "Plant removed from garden")
        //log the whole garden list , only the name of the plant will be printed
        Log.println(Log.INFO, "PlantRepository", gardenPlants.toString())

        writeGardenJson(context, gardenPlants) // context is now accessible
        return gardenPlants
    }

    fun checkIfPlantInGarden(plant: Plant): Boolean {
        return gardenPlants.any { it.id == plant.id }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }

    private fun readPlantsJson(context: Context): List<Plant> {
        //log message with tag
        Log.println(Log.INFO, "PlantRepository", "readPlantsJson")

        val inputStream = context.assets.open("plants.json")
        val plantsJson = inputStream.bufferedReader().use { it.readText() }
        return Json.decodeFromString(plantsJson)
    }

    private fun readGardenJson(context: Context): List<Plant> {
        val gardenFile = context.getFileStreamPath("myGarden.json")

        val gardenJson = if (gardenFile.exists()) {
            context.openFileInput("myGarden.json").bufferedReader().use { it.readText() }
        } else {
            context.openFileOutput("myGarden.json", Context.MODE_PRIVATE)
                .use { it.write("".toByteArray()) }
            ""
        }

        return if (gardenJson.isNotEmpty()) {
            Json.decodeFromString(gardenJson)
        } else {
            emptyList()
        }
    }


    // reset the garden and write the empty list to the file
    fun resetGarden(): List<Plant> {
        gardenPlants = mutableListOf()
        writeGardenJson(context, gardenPlants)
        return gardenPlants
    }

    private fun writeGardenJson(context: Context, gardenPlants: List<Plant>) {
        println("writeGardenJson")
        val jsonString = Json.encodeToString(gardenPlants)
        //write the json string to the file in the internal storage:
        context.openFileOutput("myGarden.json", Context.MODE_PRIVATE)
            .use { it.write(jsonString.toByteArray()) }
    }
}