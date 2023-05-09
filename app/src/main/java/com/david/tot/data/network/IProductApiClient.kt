package com.david.tot.data.network

import com.david.tot.domain.model.Product
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


    @Multipart
    @POST("pictures")
    suspend fun uploadPicture(@Part part: MultipartBody.Part): Response<ResponseBody>
/*
    companion object {
        val instance: RecipeApiClient = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RecipeApiClient::class.java)
    }

    fun rawJSON() {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()

        // Create Service
        val service = retrofit.create(this::class.java)

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("name", "Mango")
        jsonObject.put("description", "fjasdjflkdj askldjflksdaj sadjf sdj jlksadjflkdjsa")
        jsonObject.put("image", "https://static9.depositphotos.com/1642482/1148/i/600/depositphotos_11489401-stock-photo-orange-fruit.jpg")
        jsonObject.put("price", 4000)
        jsonObject.put("requested_amount", 0)
        jsonObject.put("is_milligram", 0)
        jsonObject.put("is_unit", 1)

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = service.addProduct(requestBody)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.e("#01",""+response.toString())
                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    /*
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )
                    Log.d("Pretty Printed JSON :", prettyJson)

                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("json_results", prettyJson)
                    this@MainActivity.startActivity(intent)

                     */

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }

 */

}

