package com.wilmer.fooddataandlist.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class FoodsSearchResponse(
    @SerializedName("foods_search") val foodsSearch: FoodsSearchData?
)

data class FoodsSearchData(
    @SerializedName("max_results") val maxResults: String?,
    @SerializedName("total_results") val totalResults: String?,
    @SerializedName("page_number") val pageNumber: String?,
    @SerializedName("results") val results: Results?
)

data class Results(
    @SerializedName("food") val food: List<Food>?
)
