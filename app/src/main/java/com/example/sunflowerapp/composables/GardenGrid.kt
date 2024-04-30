package com.example.sunflowerapp.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.sunflowerapp.models.Plant
import com.example.sunflowerapp.viewmodels.GardenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GardenGrid(
    listOfGardenPlants: List<Plant>,
    onClick: () -> Unit,
    viewModel: GardenViewModel,
    openDetailActivity: (Plant) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
//            .paint(
//                // Replace with your image id
//                painterResource(id = R.drawable.garden_2),
//                contentScale = ContentScale.FillBounds
//            ),
    ) {
        if (listOfGardenPlants.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Column(
                    modifier = Modifier
                        .background(color = androidx.compose.material3.MaterialTheme.colorScheme.background)
                        .padding(16.dp)
                        .clip(androidx.compose.foundation.shape.RoundedCornerShape(16.dp))
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
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier.padding(16.dp)
            ) {
                items(listOfGardenPlants) { plant ->
                    PlantCardGarden(
                        plantID = plant.id,
                        modifier = Modifier.padding(16.dp),
                        viewModel = viewModel,
                        onClick = { openDetailActivity(plant) }
                    )
                }
            }
        }
    }
}
