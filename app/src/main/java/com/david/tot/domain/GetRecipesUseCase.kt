package com.david.tot.domain

import com.david.tot.data.ProductRepository
import com.david.tot.domain.model.Product
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend operator fun invoke(st: kotlin.String):List<Product>{
        var recipes = repository.getAllRecipesFromApi()
        return if(recipes.isNotEmpty()){
            //TODO check internet connection before to clear database
            repository.clearRecipes()
            repository.insertRecipes(recipes.map { it.toDatabase() })
            //recipes
            recipes = repository.getFilteredRecipesFromDatabase(st)
            //recipes = repository.getAllRecipesFromDatabase()
            recipes
        }else{
            repository.getFilteredRecipesFromDatabase(st)
        }
    }
}
