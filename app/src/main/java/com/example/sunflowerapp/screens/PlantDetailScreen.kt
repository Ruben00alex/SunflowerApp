package com.example.sunflowerapp.screens

import android.graphics.Typeface
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.example.sunflowerapp.composables.PlantImage
import com.example.sunflowerapp.models.Plant
import com.example.sunflowerapp.viewmodels.GardenViewModel

@Composable
fun PlantDetailScreen(
    plant: Plant,
    onShareClick: (Plant) -> Unit,
    closeScreen: () -> Unit,
    viewModel: GardenViewModel,
) {
    val isPlantInGarden =
        remember { mutableStateOf(viewModel.getPlantRepository().checkIfPlantInGarden(plant)) }
    Log.d("PlantDetailScreen", "isPlantInGarden: ${isPlantInGarden.value}")

    Column(modifier = Modifier.fillMaxHeight()) {
        Box(modifier = Modifier) {
            PlantImage(
                imageUrl = plant.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 12f),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 12f)
                    .padding(8.dp)
            ) {
                Button(onClick = closeScreen, modifier = Modifier.align(Alignment.TopStart)) {
                    Text(text = "Back")
                }

                Button(
                    onClick = { onShareClick(plant) }, modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Text(text = "Share")
                }

                Button(
                    onClick = {
                        if (isPlantInGarden.value) {
                            viewModel.removePlantFromGarden(plant)
                            isPlantInGarden.value = false

                        } else {
                            viewModel.addPlantToGarden(plant)
                            isPlantInGarden.value = true
                        }
                    }, modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Text(if (isPlantInGarden.value) "Remove from Garden" else "Add to Garden")
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = plant.name, style = MaterialTheme.typography.displayMedium

                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            val spanned = HtmlCompat.fromHtml(plant.description, HtmlCompat.FROM_HTML_MODE_COMPACT)

            Text(
                text = spanned.toAnnotatedString(), style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

fun Spanned.toAnnotatedString(): AnnotatedString = buildAnnotatedString {
    val spanned = this@toAnnotatedString
    append(spanned.toString())
    getSpans(0, spanned.length, Any::class.java).forEach { span ->
        val start = getSpanStart(span)
        val end = getSpanEnd(span)
        when (span) {
            is StyleSpan -> when (span.style) {
                Typeface.BOLD -> addStyle(SpanStyle(fontWeight = FontWeight.Bold), start, end)
                Typeface.ITALIC -> addStyle(SpanStyle(fontStyle = FontStyle.Italic), start, end)
                Typeface.BOLD_ITALIC -> addStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic
                    ), start, end
                )
            }

            is UnderlineSpan -> addStyle(
                SpanStyle(textDecoration = TextDecoration.Underline), start, end
            )

            is ForegroundColorSpan -> addStyle(
                SpanStyle(color = Color(span.foregroundColor)), start, end
            )
        }
    }
}

@Preview
@Composable
fun PreviewPlantDetailScreen() {
    PlantDetailScreen(plant = Plant(
        id = "malus-pumila",
        name = "Apple",
        description = "An apple is a sweet, edible fruit produced by an apple tree (Malus pumila). Apple trees are cultivated worldwide, and are the most widely grown species in the genus Malus. The tree originated in Central Asia, where its wild ancestor, Malus sieversii, is still found today. Apples have been grown for thousands of years in Asia and Europe, and were brought to North America by European colonists. Apples have religious and mythological significance in many cultures, including Norse, Greek and European Christian traditions.<br><br>Apple trees are large if grown from seed. Generally apple cultivars are propagated by grafting onto rootstocks, which control the size of the resulting tree. There are more than 7,500 known cultivars of apples, resulting in a range of desired characteristics. Different cultivars are bred for various tastes and uses, including cooking, eating raw and cider production. Trees and fruit are prone to a number of fungal, bacterial and pest problems, which can be controlled by a number of organic and non-organic means. In 2010, the fruit's genome was sequenced as part of research on disease control and selective breeding in apple production.<br><br>Worldwide production of apples in 2014 was 84.6 million tonnes, with China accounting for 48% of the total.<br><br>(From <a href=\"https://en.wikipedia.org/wiki/Apple\">Wikipedia</a>)",
        growZoneNumber = 3,
        wateringInterval = 30,
        imageUrl = "Apple_orchard_in_Tasmania.jpg"
    ), onShareClick = {}, closeScreen = {}, viewModel = {} as GardenViewModel

    )
}


