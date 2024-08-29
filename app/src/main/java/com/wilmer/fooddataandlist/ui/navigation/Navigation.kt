package com.wilmer.fooddataandlist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wilmer.fooddataandlist.data.model.Destinations
import com.wilmer.fooddataandlist.ui.screens.Home
import com.wilmer.fooddataandlist.ui.screens.ListShow
import com.wilmer.fooddataandlist.viewmodel.FoodViewModel

@Composable
fun NavHostComposable(viewModel: FoodViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.Home.route) {

        composable(Destinations.Home.route) {
            Home(viewModel = viewModel, navController = navController)

        }

        composable(
            route = "list/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            ListShow(viewModel = viewModel, navController = navController, listIndex = index)
        }

    }
}