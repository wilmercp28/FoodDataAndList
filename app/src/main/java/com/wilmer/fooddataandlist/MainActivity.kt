package com.wilmer.fooddataandlist

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.wilmer.fooddataandlist.data.model.Food
import com.wilmer.fooddataandlist.data.model.Resource
import com.wilmer.fooddataandlist.ui.theme.FoodDataAndListTheme
import com.wilmer.fooddataandlist.viewmodel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: FoodViewModel by viewModels()
            FoodDataAndListTheme {
                Test(viewModel = viewModel)

            }
        }
    }
}



@Composable
fun Test(
    viewModel: FoodViewModel
) {
    var query by remember { mutableStateOf("") }
    val results by viewModel.foodSearchResult.collectAsState()
    Column {
        TextField(value = query, onValueChange = {
            query = it
            viewModel.searchFoods(query, maxResults = 1)
        })

        if (results is Resource.Success) {
            val foods = (results as Resource.Success).data.foodsSearch?.results?.food
            LazyColumn {
                items(foods?.size ?:0) { index ->
                    val food = foods?.get(index)
                    Text(text = food?.foodName ?: "No name")
                }


            }
        }

        if (results is Resource.Loading){
            CircularProgressIndicator()
        }
    }
}
