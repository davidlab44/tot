package com.david.tot.domain

import android.graphics.Bitmap
import com.david.tot.data.ProductRepository
import com.david.tot.domain.model.Product
import java.io.File
import java.io.InputStream
import javax.inject.Inject

class UpdateImageProductUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke(idProduct:Int,file:File):Int{
        return repository.updateImageProduct(idProduct,file)
    }
}
