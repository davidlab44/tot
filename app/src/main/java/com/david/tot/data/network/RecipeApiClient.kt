package com.david.tot.data.network

import com.david.tot.domain.model.Product
import com.david.tot.domain.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RecipeApiClient {
    @GET("c57b730b-0764-4c0c-a1aa-da32b15982c9")
    suspend fun getAllRecipes(): Response<List<Recipe>>

    @POST("products")
    suspend fun addProduct(@Body product: Product): Response<Product>
}

