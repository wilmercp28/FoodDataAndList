package com.wilmer.fooddataandlist.data.model


sealed class Destinations(val route: String) {
    data object Home : Destinations("home")

    data class List(val index: Int) : Destinations("list/$index") {
        companion object {
            fun createRoute(index: Int) = "list/$index"
        }
    }
}
