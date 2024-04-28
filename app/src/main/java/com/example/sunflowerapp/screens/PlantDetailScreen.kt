package com.example.sunflowerapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sunflowerapp.PlantRepository
import com.example.sunflowerapp.composables.PlantImage
import com.example.sunflowerapp.models.Plant

@Composable
fun PlantDetailScreen(plant: Plant,onShareClick: (Plant) -> Unit, closeScreen: () -> Unit , plantRepository: PlantRepository?) {
    Column(modifier =Modifier.fillMaxHeight()) {
        //Box, which will contain the image and two buttons, one to go back on the top left corner,
        // one to share the plant using an intent on the top right corner.
        // and on the bottom right , if the plant is not in the garden, a button to add it to the garden,
        // if it is in the garden, a button to remove it from the garden.
        Box(
            modifier = Modifier
        ) {
            PlantImage(
                imageUrl = plant.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 12f),
                contentScale = ContentScale.Crop

            )
            //back button (closeScrreen function will be passed.
            Button(
                onClick = {
                    // Close the screen
                    closeScreen()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopStart)
            ) {

                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Back")
            }
            Button(
                onClick = {
                    // Share Details of the Plant
                    onShareClick(plant)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopEnd)
            ) {

                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Share")
            }

            //Button with a conditional, if plant is in the garden, show the remove button, else show the add button.
            if (plantRepository != null) {
                if (plantRepository.checkIfPlantInGarden(plant)) {
                    Button(
                        onClick = {
                            // Remove the plant from the garden
                            plantRepository.resetGarden()
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.BottomEnd)
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Remove from Garden")
                    }
                } else {
                    Button(
                        onClick = {
                            // Add the plant to the garden
                            plantRepository.addPlantToGarden(plant)
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.BottomEnd)
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Add to Garden")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = plant.name,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = plant.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun PreviewPlantDetailScreen() {
    PlantDetailScreen(
        plant = Plant(
            id = "malus-pumila",
            name = "Apple",
            description = "An apple is a sweet, edible fruit produced by an apple tree (Malus pumila). Apple trees are cultivated worldwide, and are the most widely grown species in the genus Malus. The tree originated in Central Asia, where its wild ancestor, Malus sieversii, is still found today. Apples have been grown for thousands of years in Asia and Europe, and were brought to North America by European colonists. Apples have religious and mythological significance in many cultures, including Norse, Greek and European Christian traditions.<br><br>Apple trees are large if grown from seed. Generally apple cultivars are propagated by grafting onto rootstocks, which control the size of the resulting tree. There are more than 7,500 known cultivars of apples, resulting in a range of desired characteristics. Different cultivars are bred for various tastes and uses, including cooking, eating raw and cider production. Trees and fruit are prone to a number of fungal, bacterial and pest problems, which can be controlled by a number of organic and non-organic means. In 2010, the fruit's genome was sequenced as part of research on disease control and selective breeding in apple production.<br><br>Worldwide production of apples in 2014 was 84.6 million tonnes, with China accounting for 48% of the total.<br><br>(From <a href=\"https://en.wikipedia.org/wiki/Apple\">Wikipedia</a>)",
            growZoneNumber = 3,
            wateringInterval = 30,
            imageUrl = "Apple_orchard_in_Tasmania.jpg"),
        onShareClick = {},
        closeScreen = {},
        plantRepository = null
    )
}


