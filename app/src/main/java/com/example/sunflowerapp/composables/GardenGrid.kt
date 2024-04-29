package com.example.sunflowerapp.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.sunflowerapp.viewmodels.GardenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GardenGrid(listOfGardenPlants: List<Plant>, onClick: () -> Unit, viewModel: GardenViewModel) {
    if (listOfGardenPlants.isEmpty()) {
        Box() {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Your garden is empty",
                    modifier = Modifier.padding(16.dp)
                )

                Button(onClick = { onClick() }) {
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
                plantID = plant.id,
                modifier = Modifier.padding(16.dp),
                viewModel = viewModel,
            )
        }
    }
}
