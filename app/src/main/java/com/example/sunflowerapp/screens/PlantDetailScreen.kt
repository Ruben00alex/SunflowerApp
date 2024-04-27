package com.example.sunflowerapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.sunflowerapp.models.Plant
//
//@Composable
//fun PlantDetailScreen(plant: Plant) {
//    Column {
//        Image(
//
//            painter = painterResource(plant.imageUrl),
//            contentDescription = null,
//            //will be 1/3 height of the screen, we will use instead the fillMaxHeight() modifier
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxWidth(0.33f),
//            contentScale = ContentScale.Crop
//        )
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth()
//        ) {
//            Text(
//                text = plant.name,
//                style = MaterialTheme.typography.h5
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = plant.description,
//                style = MaterialTheme.typography.body1
//            )
//        }
//    }
//}