package com.david.dfv.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.david.dfv.ui.Destinations.*

@Composable
fun NavigationHost(recipeViewModel:RecipeViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = RecipeListScreen.route) {

        composable(RecipeListScreen.route) {
            ScreenRecipeList(
                navegarPantalla2 = { idRecipe ->
                    navController.navigate(DetailScreen.createRoute(idRecipe))
                },recipeViewModel
            )
        }

        composable(DetailScreen.route,arguments = listOf(navArgument("idRecipe"){ defaultValue = "Pantalla 2" })) { navBackStackEntry ->
            var idRecipe = navBackStackEntry.arguments?.getString("idRecipe")
            requireNotNull(idRecipe)
            ScreenDetail(idRecipe,recipeViewModel)
        }
    }
}