package com.wilmer.fooddataandlist.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FoodDetail(
    val fdcId: Int,
    val description: String,
    val dataType: String,  // Branded, Foundation, SRLegacy, Survey
    val gtinUpc: String? = null,  // Only for Branded
    val brandOwner: String? = null,  // Only for Branded
    val ingredients: String? = null,  // Only for Branded
    val servingSize: Double? = null,  // Only for Branded & Foundation
    val servingSizeUnit: String? = null,  // Only for Branded & Foundation
    val labelNutrients: LabelNutrients? = null,  // Only for Branded
    val scientificName: String? = null,  // Only for Foundation
    val foodCategory: FoodCategory? = null,  // Only for Foundation & SRLegacy
    val ndbNumber: Int? = null,  // Only for SRLegacy
    val foodCode: String? = null,  // Only for Survey
    val foodAttributes: List<FoodAttribute>? = null,  // Common to all types
    val foodNutrients: List<FoodNutrient>? = null,  // Common to all types
    val publicationDate: String? = null,  // Common to all types
    val modifiedDate: String? = null,  // Common to all types
    val householdServingFullText: String? = null,  // Only for Branded
    val footNote: String? = null,  // Only for Foundation
    val inputFoods: List<InputFood>? = null  // Only for Survey & Foundation
)

@Serializable
data class LabelNutrients(
    val fat: NutrientValue?,
    val saturatedFat: NutrientValue?,
    val carbohydrates: NutrientValue?,
    val fiber: NutrientValue?,
    val sugars: NutrientValue?,
    val protein: NutrientValue?,
    val calcium: NutrientValue?,
    val iron: NutrientValue?,
    val potassium: NutrientValue?,
    val calories: NutrientValue?
)

@Serializable
data class NutrientValue(
    val value: Double
)

@Serializable
data class FoodCategory(
    val id: Int,
    val description: String
)

@Serializable
data class FoodAttribute(
    val id: Int,
    val value: String
)

@Serializable
data class FoodNutrient(
    val number: String,
    val name: String,
    val amount: Double,
    val unitName: String
)

@Serializable
data class AbridgedFoodDetail(
    val fdcId: Int,
    val description: String,
    val dataType: String,  // Branded, Foundation, SRLegacy, Survey
    // Add other fields as necessary
    val foodNutrients: List<FoodNutrient>? = null
)

@Serializable
data class InputFood(
    val id: Int,
    val foodDescription: String,
    val ingredientCode: Int? = null,  // Only for Survey
    val ingredientDescription: String? = null,  // Only for Survey
    val portionCode: String? = null,  // Only for Survey
    val portionDescription: String? = null,  // Only for Survey
    val unit: String? = null  // Only for Survey
)
