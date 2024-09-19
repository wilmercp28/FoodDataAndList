package com.wilmer.fooddataandlist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wilmer.fooddataandlist.ui.screens.HomeScreen
import com.wilmer.fooddataandlist.ui.screens.ListView
import com.wilmer.fooddataandlist.viewmodel.FoodViewModel

@Composable
fun Navigation(viewModel: FoodViewModel, navController: NavHostController) {

    NavHost(navController = navController, startDestination = Destinations.Home.route) {
        composable(Destinations.Home.route) {
            HomeScreen(viewModel, navController)
        }
        composable(
            route = Destinations.ListView.route.plus("/{listId}"),
            arguments = listOf(navArgument("listId") { type = NavType.IntType })
        ) { backStackEntry ->
            val listId = backStackEntry.arguments?.getInt("listId")
            ListView(viewModel,navController,listId)

        }
    }
}