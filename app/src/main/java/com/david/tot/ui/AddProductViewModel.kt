package com.david.tot.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.david.tot.domain.AddProductUseCase
import com.david.tot.domain.GetRecipesUseCase
import com.david.tot.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(private val getRecipesUseCase: GetRecipesUseCase,private val addProductUseCase: AddProductUseCase) : ViewModel() {

    var responseCode by mutableStateOf<Int>(0)
    var productName by mutableStateOf<String>("")
    var productDescription by mutableStateOf<String>("")
    var productPrice by mutableStateOf<Int>(0)
    var backgroundColor: Color by mutableStateOf(Color.Transparent)

    fun addProduct(){
        if(productName.trim().length>1&&productDescription.trim().length>1&&productPrice>1){
            val product = Product(0,0,productName,"public/tot/product/product-disabled.png", productDescription,productPrice,0,0,1)
            CoroutineScope(Dispatchers.IO).launch {
                responseCode = addProductUseCase.invoke(product)
                if(responseCode == 201) {
                    backgroundColor = Color(0xFF8BE400)
                }else {
                    backgroundColor = Color(0xFFFFA4AE)
                }
            }
        }
    }

}




/*

class AddProductViewModel @Inject constructor(private val addProductUseCase: AddProductUseCase) : ViewModel() {

    var productName by mutableStateOf<String>("")
    var productDescription by mutableStateOf<String>("")
    var productPrice by mutableStateOf<Int>(0)
    fun addProduct(){
        if(productName.trim().length>1&&productDescription.trim().length>1&&productPrice>1){
            val product = Product(1001,productName,"public/tot/product/product-disabled.png", productDescription,productPrice,0,0,1)
            CoroutineScope(Dispatchers.IO).launch {
                addProductUseCase.invoke(product)
            }
        }
    }


    //var recipeModel by mutableStateOf<List<Product>>(emptyList())
    fun onCreate() {
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            //ProductRepository().requestProductList()
            val product = Product(999,"Espinaca","fruiit.jpg", "glu glu glu",10000,0,0,1)
            addProductUseCase.invoke(product)
        }
    }


    fun addProduct(product:Product){
        //TODO mostrar lo que retorna el producto creado  o por lo menos un aconfirmacion visual para  el usuario de que si se creo el producto
        CoroutineScope(Dispatchers.IO).launch {
            addProductUseCase.invoke(product)
        }
    }


}

 */