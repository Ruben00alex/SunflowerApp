package com.example.sunflowerapp.screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.sunflowerapp.PlantRepository
import com.example.sunflowerapp.composables.GardenGrid
import com.example.sunflowerapp.composables.PlantList
import com.example.sunflowerapp.models.Plant
import com.example.sunflowerapp.viewmodels.GardenViewModel

@SuppressLint("StateFlowValueCalledInComposition", "RememberReturnType")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen(viewModel: GardenViewModel, onClick: (Plant) -> Unit  ){
    val tabs = listOf("Plants", "Garden")
    val (selectedTabIndex, setSelectedTabIndex) = remember { mutableStateOf(0) }

    val gardenPlants =viewModel.gardenPlants.collectAsState()
    val plantList = remember { viewModel.getPlants() }

    val pagerState = rememberPagerState {
        tabs.size
    }
    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = selectedTabIndex == index,
                    onClick = {
                        setSelectedTabIndex(index)
                        if (index == 1) {
                            viewModel.loadGardenPlants()
                        }
                    }
                )
            }
        }

        LaunchedEffect(key1 = gardenPlants.value) {
            // This block will execute every time gardenPlants.value changes,
            // forcing recomposition of everything inside it.
            Log.d("DashboardScreen", "Garden plants changed")
        }
        LaunchedEffect(selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }

        LaunchedEffect(pagerState.currentPage) {
            setSelectedTabIndex(pagerState.currentPage)
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxHeight()
        ) { index ->
            when (index) {
                0 -> PlantList(plantList, onClick = onClick)
                1 -> GardenGrid(gardenPlants.value, onClick = { setSelectedTabIndex(0) }, viewModel = viewModel  )
                else -> throw IllegalStateException("Unexpected index: $index")
            }

        }
    }
}