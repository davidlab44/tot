package com.david.tot.data.network

import com.david.tot.domain.model.Product
import com.david.tot.domain.model.Recipe
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

    suspend fun addProduct(product: Product): Product {
        return withContext(Dispatchers.IO) {
            val response = api.addProduct(product)
            response.body()!!
        }
    }
}

