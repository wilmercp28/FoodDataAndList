package com.wilmer.fooddataandlist.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Food(
    @SerializedName("food_id") val foodId: Long,
    @SerializedName("food_name") val foodName: String?,
    @SerializedName("food_type") val foodType: String?,
    @SerializedName("food_sub_categories") val foodSubCategories: FoodSubCategories?,
    @SerializedName("food_url") val foodUrl: String?,
    @SerializedName("food_attributes") val foodAttributes: FoodAttributes?,
    @SerializedName("servings") val servings: Servings?
)

data class FoodId(
    @SerialName("value")
    val value: Long
)

data class FoodSubCategories(
    @SerializedName("food_sub_category") val foodSubCategory: List<String>?
)

data class FoodAttributes(
    @SerializedName("allergens") val allergens: Allergens?,
    @SerializedName("preferences") val preferences: Preferences?
)

data class Allergens(
    @SerializedName("allergen") val allergen: List<Allergen>?
)

data class Allergen(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("value") val value: String?
)

data class Preferences(
    @SerializedName("preference") val preference: List<Preference>?
)

data class Preference(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("value") val value: String?
)

data class Servings(
    @SerializedName("serving") val serving: List<Serving>?
)

data class Serving(
    @SerializedName("serving_id") val servingId: String?,
    @SerializedName("serving_description") val servingDescription: String?,
    @SerializedName("serving_url") val servingUrl: String?,
    @SerializedName("metric_serving_amount") val metricServingAmount: String?,
    @SerializedName("metric_serving_unit") val metricServingUnit: String?,
    @SerializedName("number_of_units") val numberOfUnits: String?,
    @SerializedName("measurement_description") val measurementDescription: String?,
    @SerializedName("calories") val calories: String?,
    @SerializedName("carbohydrate") val carbohydrate: String?,
    @SerializedName("protein") val protein: String?,
    @SerializedName("fat") val fat: String?,
    @SerializedName("saturated_fat") val saturatedFat: String?,
    @SerializedName("polyunsaturated_fat") val polyunsaturatedFat: String?,
    @SerializedName("monounsaturated_fat") val monounsaturatedFat: String?,
    @SerializedName("cholesterol") val cholesterol: String?,
    @SerializedName("sodium") val sodium: String?,
    @SerializedName("potassium") val potassium: String?,
    @SerializedName("fiber") val fiber: String?,
    @SerializedName("sugar") val sugar: String?,
    @SerializedName("vitamin_a") val vitaminA: String?,
    @SerializedName("vitamin_c") val vitaminC: String?,
    @SerializedName("calcium") val calcium: String?,
    @SerializedName("iron") val iron: String?
)
