package com.david.tot.domain

import com.david.tot.data.RecipeRepository
import com.david.tot.data.database.entities.toDatabase
import com.david.tot.domain.model.Recipe
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke():List<Recipe>{
        val recipes = repository.getAllRecipesFromApi()
        return if(recipes.isNotEmpty()){
            repository.clearRecipes()
            repository.insertRecipes(recipes.map { it.toDatabase() })
            recipes
        }else{
            repository.getAllRecipesFromDatabase()
        }
    }
}
