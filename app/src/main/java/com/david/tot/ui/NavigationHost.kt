package com.david.tot.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.david.tot.ui.Destinations.*

@Composable
fun NavigationHost(recipeViewModel:RecipeViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = RecipeListScreen.route) {

        composable(RecipeListScreen.route) {
            ScreenRecipeList(
                navegarPantalla2 = { localIdProduct ->
                    navController.navigate(DetailScreen.createRoute(localIdProduct))
                },recipeViewModel
            )
        }

        composable(DetailScreen.route,arguments = listOf(navArgument("localIdProduct"){ defaultValue = "Pantalla 2" })) { navBackStackEntry ->
            var localIdProduct = navBackStackEntry.arguments?.getString("localIdProduct")
            requireNotNull(localIdProduct)
            ScreenDetail(localIdProduct,recipeViewModel)
        }
    }
}