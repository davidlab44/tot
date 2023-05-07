package com.david.tot.ui

import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.david.tot.domain.AddProductUseCase
import com.david.tot.domain.GetRecipesUseCase
import com.david.tot.domain.UpdateProductUseCase
import com.david.tot.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val getRecipesUseCase: GetRecipesUseCase,private val updateProductUseCase: UpdateProductUseCase) : ViewModel() {

    var recipeModel by mutableStateOf<List<Product>>(emptyList())

    /*
    fun onCreate() {
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            //ProductRepository().requestProductList()
            //val product = Product(999,"Espinaca","fruiit.jpg", "glu glu glu",10000,0,0,1)
            //val a = addProductUseCase.invoke(product)

            /*
            val id = Calendar.getInstance().time
            val product = Product(999,"Espinaca","https:\\/\\/static9.depositphotos.com\\/1642482\\/1148\\/i\\/600\\/depositphotos_11489401-stock-photo-orange-fruit.jpg", "glu glu glu",10000,0,0,1)
            val a = addProductUseCase.invoke(product)
            val ff =77
            */

            val result = getRecipesUseCase.invoke(st)
            if (!result.isNullOrEmpty()) {
                recipeModel =result
            }
        }
    }
    */

    fun updateRecipeList(hash:String){
        CoroutineScope(Dispatchers.IO).launch {
            val result = getRecipesUseCase.invoke("%$hash%")
            if (!result.isNullOrEmpty()) {
                recipeModel =result
            }
        }
    }

    /*
    fun addProduct(product:Product){
        //TODO mostrar lo que retorna el producto creado  o por lo menos un aconfirmacion visual para  el usuario de que si se creo el producto
        CoroutineScope(Dispatchers.IO).launch {
            addProductUseCase.invoke(product)
        }
    }
    */

    //Edit Product
    //the purpose of this group of variables is jus pass the necesary information to Screen Detail in order to create bundle
    var productLocalId by mutableStateOf<Int>(0)
    var productRemoteId by mutableStateOf<Int>(0)
    var productName by mutableStateOf<String>("")
    var productDescription by mutableStateOf<String>("")
    var productImage by mutableStateOf<String>("")
    var productPrice by mutableStateOf<Int>(0)
}