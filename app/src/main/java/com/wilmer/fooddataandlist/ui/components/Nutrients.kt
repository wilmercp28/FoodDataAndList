package com.wilmer.fooddataandlist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.wilmer.fooddataandlist.data.model.FoodNutrient

enum class NutrientsIDs(val id: Int) {
    PROTEIN(1003),
    FAT(1004),
    CARBOHYDRATES(1005),
    SUGAR(2000),
    CHOLESTEROL(1253),
    MONOUNSATURATED_FAT(1292),
    POLYUNSATURATED_FAT(1293),
    FIBRE(1079),
}

@Composable
fun NutrientsCard(
    listOfNutrients: List<FoodNutrient>
) {
    val nutrientMap = listOfNutrients.associateBy { it.nutrient.id }

    val nutrients = listOf(
        NutrientsIDs.FAT,
        NutrientsIDs.PROTEIN,
        NutrientsIDs.CARBOHYDRATES,
        NutrientsIDs.SUGAR
    )

    Column {
        nutrients.forEach { nutrient ->
            Row {
                Text(text = nutrient.name)
                Text(text = nutrientMap[nutrient.id]?.amount.toString())
            }
        }
    }


}