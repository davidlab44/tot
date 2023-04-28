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

    suspend fun addProduct(product:Product): Product {
        val recipe = Product(999,"Espinaca","https:\\/\\/static9.depositphotos.com\\/1642482\\/1148\\/i\\/600\\/depositphotos_11489401-stock-photo-orange-fruit.jpg", "glu glu glu",10000,0,0,1)
        return api.addProduct(product)
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
