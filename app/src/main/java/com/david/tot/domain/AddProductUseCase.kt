package com.david.tot.domain

import com.david.tot.data.RecipeRepository
import com.david.tot.domain.model.Product
import javax.inject.Inject

class AddProductUseCase @Inject constructor(private val repository: RecipeRepository) {

    suspend operator fun invoke(product: Product) {
        var product = repository.addProduct(product)
    }

}
