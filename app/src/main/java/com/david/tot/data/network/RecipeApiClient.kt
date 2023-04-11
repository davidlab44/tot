package com.david.tot.data.network

import com.david.tot.domain.model.Recipe
import retrofit2.Response
import retrofit2.http.GET

interface RecipeApiClient {
    @GET("c57b730b-0764-4c0c-a1aa-da32b15982c9")
    suspend fun getAllRecipes(): Response<List<Recipe>>
}

