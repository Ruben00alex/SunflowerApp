package com.example.sunflowerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sunflowerapp.screens.DashboardScreen
import com.example.sunflowerapp.ui.theme.SunflowerAppTheme

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val plantRepository = PlantRepository(this)
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
                    DashboardScreen( listOfPlants = listaDePlantas, listOfGardenPlants = listaDeJardin)
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    SunflowerAppTheme {
        Greeting3("Android")
    }
}