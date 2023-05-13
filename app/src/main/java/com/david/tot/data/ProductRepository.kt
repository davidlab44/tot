package com.david.tot.data

import com.david.tot.data.database.dao.RecipeDao
import com.david.tot.data.network.ProductService
import com.david.tot.domain.model.Product
import com.david.tot.domain.model.toDomain
import com.david.tot.util.ImageFile
import com.david.tot.util.IsImageFile
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
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

    //suspend fun updateImageProduct(byteArray: ByteArray) {
    suspend fun updateImageProduct(file: File) {

        val isFile = IsImageFile().accept(file)
        val ii = isFile
        val requestFile: RequestBody = RequestBody.create(
            "image/jpg".toMediaType(),
            file
        )
        val multipartImage = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        //val imageUrl = "/storage/self/primary/Download/gato.png"


        //var propertyImagePart: MultipartBody.Part? = null
        //imageUrl?.let {
            //val propertyImageFile = File(imageUrl)
            //val propertyImage: RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(), propertyImageFile)
           // propertyImagePart =MultipartBody.Part.createFormData("userImage", propertyImageFile.name, propertyImage)
        //}

        /*
        val part = MultipartBody.Part.createFormData(
            "pic", "myPic", RequestBody.create("//com.android.providers.media.documents/document/image%3A1000059320".toMediaTypeOrNull(),inputStream.readBytes()
            )
        )
        */

        //val part = MultipartBody.Part.createFormData("gato","perro",RequestBody.create("image/*".toMediaTypeOrNull(),byteArray))
        //val part = MultipartBody.Part.createFormData("/storage/self/primary/Download/gato.png","")
        //val part = MultipartBody.Part.createFormData(".png",".png",byteArray.toRequestBody("image/*".toMediaTypeOrNull(), 0, byteArray.size))
        //val part = MultipartBody.Part.createFormData("gato","perro",file.asRequestBody())
        //val part = MultipartBody.Part.createFormData("gato","perro",RequestBody.create("text/plain".toMediaTypeOrNull(),file))
        //val part = MultipartBody.Part.createFormData("gato","perro",RequestBody.create("text/plain".toMediaTypeOrNull(),file))
        //val part = MultipartBody.Part.createFormData("gato","perro",p)
        //  // // val p = ImageFile().asignMultipart(file)
        api.uploadPicture(multipartImage)
    }
}
