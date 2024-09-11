package com.wilmer.fooddataandlist

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
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
                LaunchedEffect(Unit) {
                    viewModel.getAccessToken()
                }
            }
        }
    }
}

