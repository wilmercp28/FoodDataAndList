package com.wilmer.fooddataandlist.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wilmer.fooddataandlist.data.mock.getMockShoppingList
import com.wilmer.fooddataandlist.data.model.ShoppingList
import com.wilmer.fooddataandlist.services.vibratePhone

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListView(
    listOfShoppingList: List<ShoppingList>,
    itemsOnView: Int = 10,
    padding: Dp = 8.dp
) {
    val context = LocalContext.current
    var searchText by remember { mutableStateOf("") }
    var isSelecting by remember { mutableStateOf(false) }
    var selectedItems by remember { mutableStateOf(listOf<ShoppingList>()) }

    val filterList by remember(searchText) {
        mutableStateOf(
            if (searchText.isNotBlank()) listOfShoppingList.filter {
                it.name.contains(
                    searchText,
                    true
                )
            }
            else listOfShoppingList
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (!isSelecting) {
                        SearchBarSimple(
                            textField = searchText,
                            onTextFieldChange = { searchText = it },
                            onSearchClick = {})
                    } else {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = selectedItems.size.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                },
                actions = {
                    if (!isSelecting) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Rounded.Add,
                                contentDescription = "Add Shopping List"
                            )
                        }
                    } else {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Rounded.Delete,
                                contentDescription = ""
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Rounded.Warning,
                                contentDescription = ""
                            )
                        }
                    }
                },
                navigationIcon = {
                    if (!isSelecting) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.List,
                                contentDescription = "Add Shopping List"
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            isSelecting = false
                            selectedItems = emptyList()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = ""
                            )
                        }
                    }
                }
            )
        }
    ) { innerPaddling ->

        Column(
            modifier = Modifier
                .padding(innerPaddling)
        ) {

            BoxWithConstraints(
                modifier = Modifier.fillMaxSize()
            ) {

                val availableHeight =
                    this.maxHeight - (padding * (itemsOnView - 1)) - (padding * 2)
                val itemHeight = availableHeight / itemsOnView


                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(padding),
                    verticalArrangement = Arrangement.spacedBy(padding)
                ) {
                    items(filterList, key = { it.id }) { shoppingList ->
                        val modifier = Modifier.animateItem()
                        ShoppingListCard(
                            modifier = modifier,
                            isSelecting = isSelecting,
                            isSelected = selectedItems.contains(shoppingList),
                            shoppingList = shoppingList,
                            height = itemHeight,
                            onClick = {
                                if (isSelecting) {
                                    selectedItems =
                                        if (selectedItems.contains(shoppingList)) {
                                            selectedItems.filter { it != shoppingList }
                                        } else {
                                            selectedItems + shoppingList
                                        }
                                }
                            },
                            onLongClick = {
                                isSelecting = true
                                if (!selectedItems.contains(shoppingList)) selectedItems += shoppingList
                                vibratePhone(context)
                            }
                        )
                    }
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

