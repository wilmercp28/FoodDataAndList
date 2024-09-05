package com.wilmer.fooddataandlist.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.wilmer.fooddataandlist.ui.components.BackButton
import com.wilmer.fooddataandlist.ui.components.ItemCard
import com.wilmer.fooddataandlist.viewmodel.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListShow(
    viewModel: FoodViewModel,
    navController: NavController,
    listIndex: Int,
) {
    val list = viewModel.foodList.collectAsState().value[listIndex]
    val foodDetails by viewModel.foodDetails.collectAsState()

    LaunchedEffect(Unit) {
        list.items.forEach { item ->
            if (foodDetails[item.id] == null) viewModel.fetchFoodDetails(item.id)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = list.name) },
                navigationIcon = {
                    BackButton(navController = navController)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            LazyColumn {
                items(list.items) { item ->
                    ItemCard(
                        viewModel,
                        item,
                        onCardClick = {
                            viewModel.checkItem(item, listIndex)

                        }
                    )
                }
            }
        }
    }
}