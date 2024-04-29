package com.example.sunflowerapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.sunflowerapp.models.Plant
import com.example.sunflowerapp.screens.PlantDetailScreen
import com.example.sunflowerapp.ui.theme.SunflowerAppTheme
import com.example.sunflowerapp.utils.RepositoryProvider
import com.example.sunflowerapp.viewmodels.GardenViewModel
import com.example.sunflowerapp.viewmodels.GardenViewModelFactory

class PlantDetailActivity : ComponentActivity() {
    private val viewModel: GardenViewModel by viewModels {
        GardenViewModelFactory(RepositoryProvider.providePlantRepository(applicationContext))
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SunflowerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //get the plant from the intent
                    val plant = intent.getParcelableExtra("plant", Plant::class.java)
                    if (plant != null) {
                        PlantDetailScreen(plant = plant, onShareClick = { plant1 ->
                            sharePlantDetails(plant1)
                        }, closeScreen = {
                            finish()
                        }, viewModel = viewModel,
                        )
                    } else {
                        // Handle the case where the Plant object is null
                        Text("Error: Plant data is missing")
                    }
                }
            }
        }
    }


    private fun sharePlantDetails(plant: Plant) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Check out this plant: ${plant.name}\n${plant.description}"
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}
