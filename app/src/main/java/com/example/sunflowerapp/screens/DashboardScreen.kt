package com.example.sunflowerapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.sunflowerapp.composables.GardenGrid
import com.example.sunflowerapp.composables.PlantList
import com.example.sunflowerapp.models.Plant

@Composable
//TabRow element with the tabs for the dashboard screen, a tab for Plant list and one for garden.

fun DashboardScreen(listOfPlants : List<Plant>, listOfGardenPlants : List<Plant>){
    val tabs = listOf("Plants", "Garden","Home")
    val (selectedTabIndex, setSelectedTabIndex) = remember { mutableStateOf(0) }

    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = selectedTabIndex == index,
                    onClick = { setSelectedTabIndex(index) }
                )
            }
        }
        //display the PlantList or GardenScreen composable based on the selected tab
        when (selectedTabIndex) {
            0 -> PlantList(listOfPlants)
            1 -> GardenGrid(listOfGardenPlants)
        }
    }
}