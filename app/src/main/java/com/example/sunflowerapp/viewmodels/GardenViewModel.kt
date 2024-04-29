package com.example.sunflowerapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunflowerapp.PlantRepository
import com.example.sunflowerapp.models.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GardenViewModel(private val plantRepository: PlantRepository) : ViewModel() {
    private val _gardenPlants = MutableStateFlow<List<Plant>>(emptyList())
    val gardenPlants: StateFlow<List<Plant>> = _gardenPlants.asStateFlow()//.asStateFlow() will
    //convert the MutableStateFlow to StateFlow so that it can be observed by the UI.

    init {
        loadGardenPlants()
    }

    fun getPlantRepository() = plantRepository
//    viewModel.getPlant(plantID)
    fun getPlant(plantID: String) = plantRepository.getGardenPlants().find { it.id == plantID }
    fun loadGardenPlants() {
        viewModelScope.launch {
            _gardenPlants.value = plantRepository.getGardenPlants()
        }
    }
    fun waterPlant(plantId: String) {
        viewModelScope.launch {

            _gardenPlants.value =plantRepository.waterPlantById(plantId)
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
