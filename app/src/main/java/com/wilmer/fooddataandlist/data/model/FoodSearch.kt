package com.wilmer.fooddataandlist.data.model

import kotlinx.serialization.Serializable


@Serializable
data class FoodSearchResponse(
    val totalHits: Int,
    val currentPage: Int,
    val totalPages: Int,
    val pageList: List<Int>,
    val foodSearchCriteria: FoodSearchCriteria,
    val foods: List<FoodDetail>
)

@Serializable
data class FoodSearchCriteria(
    val dataType: List<String>,
    val query: String,
    val generalSearchInput: String,
    val brandOwner: String,
    val pageNumber: Int,
    val sortBy: String,
    val sortOrder: String,
    val numberOfResultsPerPage: Int,
    val pageSize: Int,
    val requireAllWords: Boolean,
    val foodTypes: List<String>
)
