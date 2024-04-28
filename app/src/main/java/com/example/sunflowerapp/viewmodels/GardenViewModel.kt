package com.example.sunflowerapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunflowerapp.PlantRepository
import com.example.sunflowerapp.models.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GardenViewModel(private val plantRepository: PlantRepository) : ViewModel() {
    private val _gardenPlants = MutableStateFlow<List<Plant>>(emptyList())
    val gardenPlants: StateFlow<List<Plant>> = _gardenPlants.asStateFlow()


    init {
        loadGardenPlants()
    }

    //check the id of the plant in the garden
    fun isPlantInGarden(plant: Plant): StateFlow<Boolean> = _gardenPlants
        .map { plants -> plants.any { it.id == plant.id } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)
    fun loadGardenPlants() {
        viewModelScope.launch {
            _gardenPlants.value = plantRepository.getGardenPlants()
        }
    }

    fun getPlants() = plantRepository.getPlants()
    fun addPlantToGarden(plant: Plant) {
        viewModelScope.launch {

            _gardenPlants.value = plantRepository.addPlantToGarden(plant)
        }
    }

    fun removePlantFromGarden(plant: Plant) {
        viewModelScope.launch {

            _gardenPlants.value = plantRepository.removePlantFromGarden(plant)
        }
    }
}
