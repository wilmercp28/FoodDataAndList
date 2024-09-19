package com.wilmer.fooddataandlist.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wilmer.fooddataandlist.data.mock.getMockShoppingList
import com.wilmer.fooddataandlist.data.model.ShoppingList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShoppingListView(
    listOfShoppingList: List<ShoppingList>,
    itemsOnView: Int = 10,
    padding: Dp = 8.dp
) {
    var searchText by remember { mutableStateOf("") }
    var isSelecting by remember { mutableStateOf(false) }

    val filterList by remember(searchText) {
        mutableStateOf(
            if (searchText.isNotBlank()) listOfShoppingList.filter { it.name.contains(searchText, true) }
            else listOfShoppingList
        )
    }

    Column {

        SearchBarSimple(
            textField = searchText,
            onTextFieldChange = { searchText = it },
            onSearchClick = {})

        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {

            val availableHeight = this.maxHeight - (padding * (itemsOnView - 1)) - (padding * 2)
            val itemHeight = availableHeight / itemsOnView


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(padding),
                verticalArrangement = Arrangement.spacedBy(padding) // Space between items
            ) {
                items(filterList, key = { it.id }) { shoppingList ->
                    val modifier = Modifier.animateItem(fadeInSpec = null, fadeOutSpec = null)
                    ShoppingListCard(
                        modifier = modifier,
                        isSelecting = isSelecting,
                        shoppingList = shoppingList,
                        height = itemHeight,
                        onClick = {},
                        onLongClick = {
                            isSelecting = !isSelecting
                        }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun PreviewShoppingListView() {
    val listOfShoppingList = getMockShoppingList(20)
    ShoppingListView(listOfShoppingList)
}

