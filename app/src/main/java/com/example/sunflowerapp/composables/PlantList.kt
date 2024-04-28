package com.example.sunflowerapp.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sunflowerapp.models.Plant

@Composable

fun PlantList(ListOfPlants: List<Plant>, onClick: (Plant) -> Unit){
    //display the list of plants
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(16.dp)
    ) {
        items(ListOfPlants) { plant ->
            PlantCard(
                plant = plant,
                modifier = Modifier.padding(8.dp),
                onClick =  onClick
            )
        }
    }


}