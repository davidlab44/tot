package com.david.tot.ui

sealed class Destinations(
    val route: String
) {
    object RecipeListScreen: Destinations("pantalla1")
    object DetailScreen: Destinations("pantalla2/?localIdProduct={localIdProduct}") {
        fun createRoute(localIdProduct: String) = "pantalla2/?localIdProduct=$localIdProduct"
    }
}
