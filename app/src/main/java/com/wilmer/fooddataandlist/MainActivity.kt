package com.wilmer.fooddataandlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.wilmer.fooddataandlist.ui.navigation.Navigation
import com.wilmer.fooddataandlist.ui.theme.FoodDataAndListTheme
import com.wilmer.fooddataandlist.viewmodel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: FoodViewModel by viewModels()
            val navController = rememberNavController()
            FoodDataAndListTheme {
                Navigation(viewModel, navController)
            }
        }
    }
}
