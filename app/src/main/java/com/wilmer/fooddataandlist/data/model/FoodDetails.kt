package com.wilmer.fooddataandlist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodDetail(
    @SerialName("dataType") val dataType: String,
    @SerialName("description") val description: String,
    @SerialName("fdcId") val fdcId: Int,
    @SerialName("foodNutrients") val foodNutrients: List<FoodNutrient>,
    @SerialName("publicationDate") val publicationDate: String,
    @SerialName("brandOwner") val brandOwner: String,
    @SerialName("gtinUpc") val gtinUpc: String,
    @SerialName("ndbNumber") val ndbNumber: Int,
    @SerialName("foodCode") val foodCode: String
)
