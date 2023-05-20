package com.david.tot.data

import com.david.tot.data.database.dao.RecipeDao
import com.david.tot.data.network.ProductService
import com.david.tot.domain.model.Product
import com.david.tot.domain.model.toDomain
import com.david.tot.util.IsImageFile
import okhttp3.MultipartBody
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import java.io.File
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
        val responseCode = api.updateProduct(product)
        return responseCode
    }

    suspend fun deleteProduct(id:Int):Int{
        val responseCode = api.deleteProduct(id)
        return responseCode
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

    suspend fun updateImageProduct(idProduct:Int,file: File) :Int {
        val isFile = IsImageFile().accept(file)
        val ii = isFile
        val requestFile: RequestBody = RequestBody.create("image/jpg".toMediaType(),file)
        val multipartImage = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        val responseCode = api.uploadPicture(idProduct,multipartImage)
        val responseCode2 = responseCode
        return responseCode
    }
}
