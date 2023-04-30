package com.david.tot.data

import com.david.tot.data.database.dao.RecipeDao
import com.david.tot.data.network.RecipeService
import com.david.tot.domain.model.Product
import com.david.tot.domain.model.Recipe
import com.david.tot.domain.model.toDomain
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val api: RecipeService,
    private val recipeDao: RecipeDao
) {

    suspend fun getAllRecipesFromApi(): List<Recipe> {
        val response: List<Recipe> = api.getRecipes()
        return response.map { it.toDomain() }
    }



    suspend fun getAllRecipesFromDatabase():List<Recipe>{
        val response: List<Recipe> = recipeDao.getAllRecipes()
        return response.map { it.toDomain() }
    }

    suspend fun getFilteredRecipesFromDatabase(st: String): List<Recipe> {
        val response: List<Recipe> = recipeDao.getFilteredRecipes(st)
        response.map { it.toDomain() }
        return recipeDao.getFilteredRecipes(st)
    }

    suspend fun insertRecipes(recipes:List<Recipe>){
        recipeDao.insertAll(recipes)
    }

    suspend fun clearRecipes(){
        recipeDao.deleteAllRecipes()
    }
}
