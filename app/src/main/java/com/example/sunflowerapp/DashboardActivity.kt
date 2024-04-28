package com.example.sunflowerapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.sunflowerapp.screens.DashboardScreen
import com.example.sunflowerapp.ui.theme.SunflowerAppTheme
import com.example.sunflowerapp.utils.RepositoryProvider
import com.example.sunflowerapp.viewmodels.GardenViewModel
import com.example.sunflowerapp.viewmodels.GardenViewModelFactory

class DashboardActivity : ComponentActivity() {


    private val viewModel: GardenViewModel by viewModels {
        GardenViewModelFactory(RepositoryProvider.providePlantRepository(applicationContext))
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            SunflowerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    DashboardScreen(
                        onClick = { plant ->
                            val intent = Intent(this, PlantDetailActivity::class.java)
                            intent.putExtra("plant", plant)
                            startActivity(intent)
                        },
                        viewModel= viewModel

                        )

                }
            }
        }
    }
}
