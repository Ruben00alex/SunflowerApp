package com.example.sunflowerapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sunflowerapp.ui.theme.SunflowerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        //plant repository with context as parameter

//        println("MainActivity onCreate")
//        //test of the repository methods:
//        plantRepository.getPlants().forEach { plant -> println(plant.name) }
//
//        println("Garden plants:")
//        plantRepository.getGardenPlants().forEach { plant -> println(plant.plantedDate) }
//        //add the first plant in the list to the garden:
//        plantRepository.addPlantToGarden(plantRepository.getPlants().last())
//
//        //print the garden plants:
//        println("Garden plants:")
//        //print the whole file , DO NOT USE forEach!!
//        println(plantRepository.getGardenPlants())
//
//        //plantRepository.resetGarden()


        setContent {
            SunflowerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    GoToDashboardButton()
            }
        }
    }
}

    @Composable
    ////Button with implicit intent to open DashboardActivity
    fun GoToDashboardButton() {
        Button(onClick = {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }) {
            Text("Go to Dashboard")
        }
    }

}