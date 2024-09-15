package com.wilmer.fooddataandlist.data.model

import com.google.gson.annotations.SerializedName

data class Suggestions(
    @SerializedName("suggestion") val suggestion: Suggestion
)

data class Suggestion(
    @SerializedName("suggestion") val suggestions: List<String>
)