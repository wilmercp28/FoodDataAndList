package com.wilmer.fooddataandlist

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wilmer.fooddataandlist.data.remote.ApiService
import com.wilmer.fooddataandlist.data.remote.FoodRepository
import com.wilmer.fooddataandlist.data.remote.RetrofitInstance
import com.wilmer.fooddataandlist.ui.theme.FoodDataAndListTheme
import com.wilmer.fooddataandlist.viewmodel.FoodViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiService = RetrofitInstance.api
        val repository = FoodRepository(apiService)
        val viewModel = FoodViewModel(repository)
        enableEdgeToEdge()
        setContent {
            FoodDataAndListTheme {
                viewModel.fetchFoodSearch("apple")
                val response by viewModel.foodDetails.collectAsState(initial = "")
                Text(text = response)

            }
        }
    }
}
