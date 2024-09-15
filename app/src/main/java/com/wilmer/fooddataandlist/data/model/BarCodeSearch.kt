package com.wilmer.fooddataandlist.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName


data class BarCodeSearch(
    @SerialName("food_id")
    val foodId: FoodId
)
