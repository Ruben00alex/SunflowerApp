package com.example.sunflowerapp.models
import android.os.Parcelable

import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class Plant(
    //val plantId: String,
    @SerialName("plantId") val id: String,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("growZoneNumber") val growZoneNumber: Int,
    @SerialName("wateringInterval") val wateringInterval: Int,
    @SerialName("imageUrl") val imageUrl: String,
    @SerialName("plantedDate") val plantedDate: String? = null,
    val lastWateredDate: String? = null,
) : Parcelable
