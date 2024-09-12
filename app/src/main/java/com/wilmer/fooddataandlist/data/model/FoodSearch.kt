package com.wilmer.fooddataandlist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class FoodsSearchResponse(
    @SerialName("foods_search") val foodsSearch: FoodsSearch
)

@Serializable
data class FoodsSearch(
    @SerialName("max_results") val maxResults: String? = null,
    @SerialName("total_results") val totalResults: String? = null,
    @SerialName("page_number") val pageNumber: String? = null,
    val results: Results? = null
)

@Serializable
data class Results(
    val food: List<Food>? = null
)

@Serializable
data class Food(
    @SerialName("food_id") val foodId: String? = null,
    @SerialName("food_name") val foodName: String? = null,
    @SerialName("brand_name") val brandName: String? = null,
    @SerialName("food_type") val foodType: String? = null,
    @SerialName("food_sub_categories") val foodSubCategories: FoodSubCategories? = null,
    @SerialName("food_url") val foodUrl: String? = null,
    @SerialName("food_images") val foodImages: FoodImages? = null,
    @SerialName("food_attributes") val foodAttributes: FoodAttributes? = null,
    val servings: Servings? = null
)

@Serializable
data class FoodSubCategories(
    @SerialName("food_sub_category") val foodSubCategory: List<String>? = null
)

@Serializable
data class FoodImages(
    val foodImage: List<FoodImage>? = null
)

@Serializable
data class FoodImage(
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("image_type") val imageType: String? = null
)

@Serializable
data class FoodAttributes(
    val allergens: Allergens? = null,
    val preferences: Preferences? = null
)

@Serializable
data class Allergens(
    val allergen: List<Allergen>? = null
)

@Serializable
data class Allergen(
    val id: String? = null,
    val name: String? = null,
    val value: String? = null
)

@Serializable
data class Preferences(
    val preference: List<Preference>? = null
)

@Serializable
data class Preference(
    val id: String? = null,
    val name: String? = null,
    val value: String? = null
)

@Serializable
data class Servings(
    val serving: List<Serving>? = null
)

@Serializable
data class Serving(
    @SerialName("serving_id") val servingId: String? = null,
    @SerialName("serving_description") val servingDescription: String? = null,
    @SerialName("serving_url") val servingUrl: String? = null,
    @SerialName("metric_serving_amount") val metricServingAmount: String? = null,
    @SerialName("metric_serving_unit") val metricServingUnit: String? = null,
    @SerialName("number_of_units") val numberOfUnits: String? = null,
    @SerialName("measurement_description") val measurementDescription: String? = null,
    @SerialName("is_default") val isDefault: String? = null,
    val calories: String? = null,
    val carbohydrate: String? = null,
    val protein: String? = null,
    val fat: String? = null,
    @SerialName("saturated_fat") val saturatedFat: String? = null,
    @SerialName("polyunsaturated_fat") val polyunsaturatedFat: String? = null,
    @SerialName("monounsaturated_fat") val monounsaturatedFat: String? = null,
    @SerialName("trans_fat") val transFat: String? = null,
    val cholesterol: String? = null,
    val sodium: String? = null,
    val potassium: String? = null,
    val fiber: String? = null,
    val sugar: String? = null,
    @SerialName("added_sugars") val addedSugars: String? = null,
    @SerialName("vitamin_d") val vitaminD: String? = null,
    @SerialName("vitamin_a") val vitaminA: String? = null,
    @SerialName("vitamin_c") val vitaminC: String? = null,
    val calcium: String? = null,
    val iron: String? = null
)
