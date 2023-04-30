package com.david.tot.domain

import com.david.tot.data.RecipeRepository
import com.david.tot.domain.model.Product
import com.david.tot.domain.model.Recipe
import com.david.tot.domain.model.toDatabase
import javax.inject.Inject

class AddProductUseCase @Inject constructor(private val repository: RecipeRepository) {


    /*
    suspend operator fun invoke(product: Product):Product{
        var product = repository.addProduct(product)
        return product
    }

     */
}
