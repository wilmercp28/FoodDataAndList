package com.wilmer.fooddataandlist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wilmer.fooddataandlist.data.model.FoodList
import com.wilmer.fooddataandlist.data.model.Sizes
import com.wilmer.fooddataandlist.utilities.FontUtilities


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