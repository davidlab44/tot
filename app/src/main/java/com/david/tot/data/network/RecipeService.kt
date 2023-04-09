package com.david.tot.data.network

import com.david.tot.data.model.RecipeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeService @Inject constructor(private val api:RecipeApiClient) {
    suspend fun getRecipes(): List<RecipeModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllRecipes()
            response.body() ?: emptyList()
        }
    }
}

