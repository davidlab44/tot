package com.david.tot.data.network

import com.david.tot.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
            /*
            val product:Product=Product(999, "kiwi","image.jpg","bal bla blazzzto",1000,0,0,1)

            val jsonObject = JSONObject()
            jsonObject.put("id", 999)
            jsonObject.put("name", "")
            jsonObject.put("image", "image.png")
            jsonObject.put("description", "jac kasdfdasfj sdakjf sadlf jasjdf ")
            jsonObject.put("price", 1000)
            jsonObject.put("requested_amount", 0)

            val jsonObjectString = jsonObject.toString()

            // usar Gson Converter osea saltarse la siguiente linea
            // NO ESTOY OBTENIENDO LA CADENA QUE RETORTNO DEL BAKEND
             val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
            // Do the POST request and get response
            */

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


    suspend fun updateProduct(product:Product){
        return withContext(Dispatchers.IO) {
            //val product:Product=Product(13, "mojarra","public/tot/product/product-disabled.png","bal bla blazzzto",1000,0,0,1)
            val response = api.updateProduct(product.id.toString(),product)
            val res = response
        }
    }












    /*
    fun rawJSON() {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()

        // Create Service
        val service = retrofit.create(api::class.java)

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

