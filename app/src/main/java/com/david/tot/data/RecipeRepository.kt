package com.david.tot.data

import com.david.tot.data.database.dao.RecipeDao
import com.david.tot.data.database.entities.RecipeEntity
import com.david.tot.data.model.RecipeModel
import com.david.tot.data.network.RecipeService
import com.david.tot.domain.model.Recipe
import com.david.tot.domain.model.toDomain
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val api: RecipeService,
    private val recipeDao: RecipeDao
) {

    suspend fun getAllRecipesFromApi(): List<Recipe> {
        val response: List<RecipeModel> = api.getRecipes()
        return response.map { it.toDomain() }
    }

    suspend fun getAllRecipesFromDatabase():List<Recipe>{
        val response: List<RecipeEntity> = recipeDao.getAllRecipes()
        return response.map { it.toDomain() }
    }

    suspend fun insertRecipes(recipes:List<RecipeEntity>){
        recipeDao.insertAll(recipes)
    }

    suspend fun clearRecipes(){
        recipeDao.deleteAllRecipes()
    }
}
