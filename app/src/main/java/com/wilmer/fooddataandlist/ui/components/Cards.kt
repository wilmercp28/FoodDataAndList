package com.wilmer.fooddataandlist.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wilmer.fooddataandlist.data.model.FoodList
import com.wilmer.fooddataandlist.data.model.Item
import com.wilmer.fooddataandlist.data.model.Sizes
import com.wilmer.fooddataandlist.utilities.FontUtilities
import com.wilmer.fooddataandlist.viewmodel.FoodViewModel


@Composable
fun ListCard(
    list: FoodList,
    onCardClick: () -> Unit = {},
){

        Card(
            onClick = { onCardClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                FontUtilities.ScaledTitleLargeText(text = list.name, size = Sizes.MEDIUM)
                FontUtilities.ScaledTitleLargeText(text = "${list.items.size} Items", size = Sizes.EXTRASMAll )
            }
        }
}

@Composable
fun ItemCard(
    viewModel: FoodViewModel,
    item: Item,
    onCardClick: () -> Unit = {},
){
    val foodDetail = viewModel.foodDetails.collectAsState().value[item.id]

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = if (item.checked) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
        ),
        onClick = { onCardClick() }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .sizeIn(minHeight = 50.dp)
        ) {
            if (foodDetail == null) {
                Text(text = "Loading...")
            } else {
                FontUtilities.ScaledTitleLargeText(text = foodDetail.description, size = Sizes.EXTRASMAll)
                foodDetail.foodNutrients?.let { nutrients ->
                    Log.d("Nutrients", "Nutrients: $nutrients")
                    NutrientsCard(listOfNutrients = nutrients)
                }

            }
        }
    }

}