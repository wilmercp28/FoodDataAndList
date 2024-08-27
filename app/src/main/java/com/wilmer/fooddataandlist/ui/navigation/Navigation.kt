package com.wilmer.fooddataandlist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wilmer.fooddataandlist.data.model.Destinations
import com.wilmer.fooddataandlist.ui.screens.Home
import com.wilmer.fooddataandlist.viewmodel.FoodViewModel

@Composable
fun NavHostComposable(viewModel: FoodViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.Home.route) {

        composable(Destinations.Home.route) {
            Home(viewModel = viewModel, navController = navController)

        }

    }
}