package com.example.sunflowerapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

class PlantDetailActivity : ComponentActivity() {
    private val plantRepository: PlantRepository by lazy {
        RepositoryProvider.providePlantRepository(applicationContext)
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
                    //make plant a remember so it can be used in the composable and update the UI



                    if (plant != null) {
                        PlantDetailScreen(plant = plant , onShareClick = { plant1 ->
                            sharePlantDetails(plant1)
                        }, closeScreen = {
                            finish()
                        }, plantRepository = plantRepository
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
