package com.david.tot.data

 import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.david.tot.data.network.RecipeApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository() {

    private val apiService = RecipeApiClient.instance

    //TODO hacer una peticion GET para comprobar que este camino funciona
    suspend fun requestProductList() {
        apiService.getAllRecipes().body()
        val tt =87
    }
}

