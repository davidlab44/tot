package com.david.tot.domain

import com.david.tot.data.ProductRepository
import com.david.tot.domain.model.Product
import java.io.InputStream
import javax.inject.Inject

class UpdateImageProductUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke(inputStream:InputStream){
        return repository.updateImageProduct(inputStream)
    }
}
