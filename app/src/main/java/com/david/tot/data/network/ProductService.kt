package com.david.tot.data.network

import com.david.tot.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import javax.inject.Inject

class ProductService @Inject constructor(private val api:IProductApiClient) {
    suspend fun getRecipes(): List<Product> {
        //api.rawJSON()
        return withContext(Dispatchers.IO) {
            val response = api.getAllRecipes()
            response.body() ?: emptyList()
        }
    }

    suspend fun addProduct(product:Product):Int{
        return withContext(Dispatchers.IO) {
            val respuesta = api.addProduct(product)
            val respuestaBody =respuesta.body().toString()
            val respuestaBody2 = respuesta.body()
            val respuestaBody3 =respuesta.errorBody()
            val respuestaBody4 =respuesta.isSuccessful
            val respuestaBody5 =respuesta.code()
            val respuestaRaw =respuesta.raw()
            val respuestaBody7 =respuesta.headers()
            val respuestaBody8 =respuestaBody2
            val respuestaBody11 =respuestaBody3
            val respuestaBod12 =respuestaRaw
            val respuestaBody13=respuestaBody4
            val respuestaBody14 =respuestaBody7
            val respuestaBod15 =respuestaBody5
            val respuestaBody9 =respuestaBody
            //if (response.isSuccessful) { }
            respuesta.code()
        }
    }

    suspend fun updateProduct(product:Product):Int{
        return withContext(Dispatchers.IO) {
            val response = api.updateProduct(product.id.toString(),product)
            val res = response
            response.code()
        }
    }

    suspend fun deleteProduct(id:Int):Int{
        return withContext(Dispatchers.IO) {
            val response = api.deleteProduct(id)
            val res = response
            response.code()
        }
    }
    suspend fun uploadPicture(idProduct:Int,part:MultipartBody.Part):Int{
        return withContext(Dispatchers.IO) {
            val response = api.uploadPicture(part,idProduct)
            val res = response
            response.code()
        }
    }

}

