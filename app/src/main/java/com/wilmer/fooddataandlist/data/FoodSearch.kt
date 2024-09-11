package com.wilmer.fooddataandlist.data

import kotlinx.serialization.Serializable

@Serializable
data class FoodsSearchResponse(
    val foods_search: FoodsSearch
)

@Serializable
data class FoodsSearch(
    val max_results: String,
    val total_results: String,
    val page_number: String,
    val results: Results
)

@Serializable
data class Results(
    val food: List<Food>
)

@Serializable
data class Food(
    val food_id: String,
    val food_name: String,
    val brand_name: String,
    val food_type: String,
    val food_sub_categories: FoodSubCategories,
    val food_url: String,
    val food_attributes: FoodAttributes,
    val servings: Servings
)

@Serializable
data class FoodSubCategories(
    val food_sub_category: List<String>
)

@Serializable
data class FoodAttributes(
    val allergens: Allergens,
    val preferences: Preferences
)

@Serializable
data class Allergens(
    val allergen: List<Allergen>
)

@Serializable
data class Allergen(
    val id: String,
    val name: String,
    val value: String
)

@Serializable
data class Preferences(
    val preference: List<Preference>
)

@Serializable
data class Preference(
    val id: String,
    val name: String,
    val value: String
)

@Serializable
data class Servings(
    val serving: List<Serving>
)

@Serializable
data class Serving(
    val serving_id: String,
    val serving_description: String,
    val serving_url: String,
    val metric_serving_amount: String,
    val metric_serving_unit: String,
    val number_of_units: String,
    val measurement_description: String,
    val is_default: String,
    val calories: String,
    val carbohydrate: String,
    val protein: String,
    val fat: String,
    val saturated_fat: String,
    val polyunsaturated_fat: String,
    val monounsaturated_fat: String,
    val trans_fat: String,
    val cholesterol: String,
    val sodium: String,
    val potassium: String,
    val fiber: String,
    val sugar: String,
    val added_sugars: String,
    val vitamin_d: String,
    val vitamin_a: String,
    val vitamin_c: String,
    val calcium: String,
    val iron: String
)
