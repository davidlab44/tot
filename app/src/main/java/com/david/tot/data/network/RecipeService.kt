package com.david.tot.data.network

import android.content.Intent
import android.util.Log
import com.david.tot.domain.model.Product
import com.david.tot.domain.model.Recipe
import com.david.tot.util.BASE_URL
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import javax.inject.Inject

class RecipeService @Inject constructor(private val api:RecipeApiClient) {
    suspend fun getRecipes(): List<Recipe> {
        //api.rawJSON()
        return withContext(Dispatchers.IO) {
            val response = api.getAllRecipes()
            response.body() ?: emptyList()
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

