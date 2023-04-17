package com.david.dfv.data.network

import com.david.dfv.domain.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeService @Inject constructor(private val api:RecipeApiClient) {
    suspend fun getRecipes(): List<Recipe> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllRecipes()
            response.body() ?: emptyList()
        }
    }
}

