package com.wilmer.fooddataandlist.ui.navigation

sealed class Destinations(val route: String) {
    data object Home : Destinations("home")
}