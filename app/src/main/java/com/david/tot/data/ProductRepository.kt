package com.david.tot.data

import com.david.tot.data.database.dao.RecipeDao
import com.david.tot.data.network.ProductService
import com.david.tot.domain.model.Product
import com.david.tot.domain.model.toDomain
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.*
import java.io.File
import java.io.InputStream
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductService,
    private val recipeDao: RecipeDao
) {

    suspend fun getAllRecipesFromApi(): List<Product> {
        val response: List<Product> = api.getRecipes()
        return response.map { it.toDomain() }
    }

    suspend fun addProduct(product:Product):Int{
        return api.addProduct(product)
    }

    suspend fun updateProduct(product:Product):Int{
        api.updateProduct(product)
        return 1
    }

    suspend fun getAllRecipesFromDatabase():List<Product>{
        val response: List<Product> = recipeDao.getAllRecipes()
        return response.map { it.toDomain() }
    }

    suspend fun getFilteredRecipesFromDatabase(st: String): List<Product> {
        val response: List<Product> = recipeDao.getFilteredRecipes(st)
        response.map { it.toDomain() }
        return recipeDao.getFilteredRecipes(st)
    }

    suspend fun insertRecipes(recipes:List<Product>){
        recipeDao.insertAll(recipes)
    }

    suspend fun clearRecipes(){
        recipeDao.deleteAllRecipes()
    }

    suspend fun updateImageProduct(file: File) {
        /*
        val part = MultipartBody.Part.createFormData(
            "pic", "myPic", RequestBody.create("//com.android.providers.media.documents/document/image%3A1000059320".toMediaTypeOrNull(),inputStream.readBytes()
            )
        )
        */

        val part = MultipartBody.Part.createFormData("gato","perro",RequestBody.create("image/*".toMediaTypeOrNull(),file))
        api.uploadPicture(part)
    }
}
