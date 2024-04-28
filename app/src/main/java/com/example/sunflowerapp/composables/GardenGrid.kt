package com.example.sunflowerapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sunflowerapp.models.Plant

@Composable
fun GardenGrid(listOfGardenPlants: List<Plant>,onClick: () -> Unit){
    if (listOfGardenPlants.isEmpty()) {
        //column will be centered!!!! for this we will use Box
        Box( ) {
Column (modifier = Modifier.fillMaxSize() , verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center, horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {


        Text(
            text = "Your garden is empty",
            modifier = Modifier.padding(16.dp)
        )

        Button(onClick = { onClick()}) {
            Text(text = "Add plants to your garden")

        }
}
        }
        return
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier.padding(16.dp)
    ) {
        items(listOfGardenPlants) { plant ->
            PlantCardGarden(
                plant = plant,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
