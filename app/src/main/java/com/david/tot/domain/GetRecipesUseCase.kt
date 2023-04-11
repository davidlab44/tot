package com.david.tot.domain

import com.david.tot.data.RecipeRepository
import com.david.tot.domain.model.Recipe
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke(st: kotlin.String):List<Recipe>{
        var recipes = repository.getAllRecipesFromApi()
        return if(recipes.isNotEmpty()){
            //TODO check internet connection before to clear database
            repository.clearRecipes()
            repository.insertRecipes(recipes.map { it.toDatabase() })
            //recipes
            recipes = repository.getFilteredRecipesFromDatabase(st)
            recipes
        }else{
            repository.getFilteredRecipesFromDatabase(st)
        }
    }
}
