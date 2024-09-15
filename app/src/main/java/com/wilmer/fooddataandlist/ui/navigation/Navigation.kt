package com.wilmer.fooddataandlist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wilmer.fooddataandlist.ui.screens.HomeScreen
import com.wilmer.fooddataandlist.viewmodel.FoodViewModel

@Composable
fun Navigation(viewModel: FoodViewModel, navController: NavHostController) {

    NavHost(navController = navController, startDestination = Destinations.Home.route) {
        composable(Destinations.Home.route) {
            HomeScreen(viewModel, navController)
        }
        composable(
            route = ,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId")

        }
    }
}