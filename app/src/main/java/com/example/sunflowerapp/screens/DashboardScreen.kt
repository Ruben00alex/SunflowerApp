package com.example.sunflowerapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.sunflowerapp.composables.GardenGrid
import com.example.sunflowerapp.composables.PlantList
import com.example.sunflowerapp.models.Plant
import com.example.sunflowerapp.viewmodels.GardenViewModel

@Composable
fun DashboardScreen(viewModel: GardenViewModel, onClick: (Plant) -> Unit) {
    val tabs = listOf("Plants", "Garden")
    val (selectedTabIndex, setSelectedTabIndex) = remember { mutableStateOf(0) }

    val gardenPlants = viewModel.gardenPlants.collectAsState().value
    val plantList = viewModel.getPlants()
    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = selectedTabIndex == index,
                    onClick = {
                        setSelectedTabIndex(index)
                        if (index == 1) {
                            viewModel.loadGardenPlants()
                        }
                    }
                )
            }
        }
        // Display the PlantList or GardenScreen Composable based on the selected tab
        when (selectedTabIndex) {
            0 -> PlantList(plantList, onClick = onClick)
            1 -> GardenGrid(gardenPlants)
        }
    }
}