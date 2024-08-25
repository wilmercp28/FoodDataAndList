package com.wilmer.fooddataandlist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodSearchResponse(
    @SerialName("foodSearchCriteria") val foodSearchCriteria: FoodSearchCriteria,
    @SerialName("totalHits") val totalHits: Int,
    @SerialName("currentPage") val currentPage: Int,
    @SerialName("totalPages") val totalPages: Int,
    @SerialName("foods") val foods: List<Food>
)

@Serializable
data class FoodSearchCriteria(
    @SerialName("query") val query: String,
    @SerialName("dataType") val dataType: List<String>,
    @SerialName("pageSize") val pageSize: Int,
    @SerialName("pageNumber") val pageNumber: Int,
    @SerialName("sortBy") val sortBy: String,
    @SerialName("sortOrder") val sortOrder: String,
    @SerialName("brandOwner") val brandOwner: String,
    @SerialName("tradeChannel") val tradeChannel: List<String>,
    @SerialName("startDate") val startDate: String,
    @SerialName("endDate") val endDate: String
)

@Serializable
data class Food(
    @SerialName("fdcId") val fdcId: Int,
    @SerialName("dataType") val dataType: String,
    @SerialName("description") val description: String,
    @SerialName("foodCode") val foodCode: String,
    @SerialName("foodNutrients") val foodNutrients: List<FoodNutrient>,
    @SerialName("publicationDate") val publicationDate: String,
    @SerialName("scientificName") val scientificName: String,
    @SerialName("brandOwner") val brandOwner: String,
    @SerialName("gtinUpc") val gtinUpc: String,
    @SerialName("ingredients") val ingredients: String,
    @SerialName("ndbNumber") val ndbNumber: Int,
    @SerialName("additionalDescriptions") val additionalDescriptions: String,
    @SerialName("allHighlightFields") val allHighlightFields: String,
    @SerialName("score") val score: Double
)

@Serializable
data class FoodNutrient(
    @SerialName("number") val number: Int,
    @SerialName("name") val name: String,
    @SerialName("amount") val amount: Double,
    @SerialName("unitName") val unitName: String,
    @SerialName("derivationCode") val derivationCode: String,
    @SerialName("derivationDescription") val derivationDescription: String
)
