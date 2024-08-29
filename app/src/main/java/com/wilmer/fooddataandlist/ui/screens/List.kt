package com.wilmer.fooddataandlist.ui.screens

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.wilmer.fooddataandlist.data.model.FoodDetail
import com.wilmer.fooddataandlist.data.model.FoodList
import com.wilmer.fooddataandlist.viewmodel.FoodViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListShow(
    viewModel: FoodViewModel,
    navController: NavController,
    listIndex: Int,
) {
    val scope = rememberCoroutineScope()
    val list = viewModel.foodList.collectAsState().value[listIndex]
    var foodDetails by remember { mutableStateOf<Map<Int, FoodDetail?>>(emptyMap()) }

    LaunchedEffect(Unit) {
        val newMap = mutableMapOf<Int, FoodDetail?>()
        list.items.forEach {
            if (foodDetails[it] == null) {
                newMap[it] = viewModel.fetchFoodDetails(it)
            }
        }
        foodDetails = newMap
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = list.name) })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            LazyColumn {
                items(list.items){ id ->
                    val foodDetail = foodDetails[id]
                    if (foodDetail != null) {
                        Text(text = foodDetail.description)
                    }
                }
            }
        }
    }
}