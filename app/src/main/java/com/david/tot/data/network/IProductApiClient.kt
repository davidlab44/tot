package com.david.tot.data.network

import com.david.tot.domain.model.Product
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
//import retrofit2.Retrofit

interface IProductApiClient {
    @GET("products")
    suspend fun getAllRecipes(): Response<List<Product>>

    @POST("products")
    suspend fun addProduct(@Body product: Product): Response<ResponseBody>

    @PUT("products/{id}")
    suspend fun updateProduct(@Path("id") id:String, @Body product:Product): Response<ResponseBody>

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id:Int): Response<ResponseBody>

    @Multipart
    @POST("pictures")
    suspend fun uploadPicture(@Part part: MultipartBody.Part,@Part("id_product") id_product:Int): Response<ResponseBody>
}

