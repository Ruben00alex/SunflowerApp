package com.example.sunflowerapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.sunflowerapp.screens.DashboardScreen
import com.example.sunflowerapp.ui.theme.SunflowerAppTheme
import com.example.sunflowerapp.utils.RepositoryProvider

class DashboardActivity : ComponentActivity() {
    private val plantRepository: PlantRepository by lazy {
        RepositoryProvider.providePlantRepository(applicationContext)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        //val plantRepository = PlantRepository(this)
        val listaDePlantas = plantRepository.getPlants()
        var listaDeJardin = plantRepository.getGardenPlants()


        super.onCreate(savedInstanceState)
        setContent {
            SunflowerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    DashboardScreen( listOfPlants = listaDePlantas, listOfGardenPlants = listaDeJardin,
                        onClick = { plant ->
                            val intent = Intent(this, PlantDetailActivity::class.java)
                            intent.putExtra("plant", plant) // Pass the plant object as an extra
                            startActivity(intent)
                        })

                }
            }
        }
    }
}
