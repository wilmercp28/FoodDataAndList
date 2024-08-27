package com.wilmer.fooddataandlist

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import com.wilmer.fooddataandlist.dependency.NetworkModule
import com.wilmer.fooddataandlist.ui.navigation.NavHostComposable
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
                NavHostComposable(viewModel = viewModel)
            }
        }
    }
}

