package com.wilmer.fooddataandlist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.wilmer.fooddataandlist.data.model.FoodNutrient



@Composable
fun NutrientsCard(
    listOfNutrients: List<FoodNutrient>
) {
    Column {
        listOfNutrients.forEach { nutrient ->
            Row {
                Text(text = nutrient.name)
            }
        }
    }
}