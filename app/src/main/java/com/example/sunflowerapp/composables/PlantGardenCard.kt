package com.example.sunflowerapp.composables

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sunflowerapp.models.Plant
import com.example.sunflowerapp.viewmodels.GardenViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlantCardGarden(
    plantID: String,
    modifier: Modifier = Modifier,
    viewModel: GardenViewModel,
) {
    val plant = viewModel.getPlant(plantID)
    if (plant == null) {
        Log.e("PlantCardGarden", "Plant not found")
        return
    }

    OutlinedCard(
        modifier = modifier,
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(0.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier.padding(0.dp)
        ) {
            PlantImage(
                imageUrl = plant.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .blur(.5.dp)
            )

            Text(
                text = plant.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(0.dp)
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            //Planted on:
            Text(
                text = "Planted on: ${plant.plantedDate}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            //Planted on:
//            val wateredStatus = if (plant.lastWateredDate == null) "Not watered yet" else "Last watered: ${plant.lastWateredDate}"
            val wateredStatus = dateUntilWatering(plant)
            Text(
                text = wateredStatus,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )


            //Row with two buttons: Water and Remove
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly
            ) {
                //Water button
                TextButton(
                    onClick = {
                        viewModel.waterPlant(plant.id)
                    },
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth(1f / 2f)
                ) {
                    Text("Water")
                }
                //Remove button
                TextButton(
                    onClick = {
                        viewModel.removePlantFromGarden(plant)
                    },
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                ) {
                    Text("Remove")
                }

            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun dateUntilWatering(plant: Plant): String {
    plant.lastWateredDate?.let {

        val lastWatered = LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE)
        val nextWateringDate = lastWatered.plusDays(plant.wateringInterval.toLong())

        val today = LocalDate.now()

        // Calculate the days until next watering
        return if (today.isBefore(nextWateringDate)) {
            val daysUntilWatering = ChronoUnit.DAYS.between(today, nextWateringDate)
            "$daysUntilWatering days until watering"
        } else {
            "Water me!!!!"
        }
    }

    // If the plant hasn't been watered yet
    return "Not watered yet"
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewPlantCardGarden() {
    PlantCardGarden(
        plantID = "malus-pumila",
        modifier = Modifier.padding(8.dp),
        viewModel = {} as GardenViewModel,
    )
}

