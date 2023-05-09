package com.david.tot.ui

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.david.tot.domain.UpdateImageProductUseCase
import com.david.tot.domain.UpdateProductUseCase
import com.david.tot.domain.model.Product
import com.david.tot.util.ImageFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class UpdateProductViewModel @Inject constructor(
    private val updateProductUseCase: UpdateProductUseCase,
    private val updateImageProductUseCase: UpdateImageProductUseCase,
    ) : ViewModel() {

    var response by mutableStateOf<Int>(0)
    var productRemoteId by mutableStateOf<String>("")
    var productLocalId by mutableStateOf<String>("")
    var productName by mutableStateOf<String>("")
    var productDescription by mutableStateOf<String>("")
    var productImage by mutableStateOf<String>("")
    var productPrice by mutableStateOf<String>("")
    var backgroundColor: Color by mutableStateOf(Color.Transparent)

    fun updateProduct(){
        if(productName.trim().length>1&&productDescription.trim().length>1&&productPrice.toInt()>1) {
            val product = Product(
                productLocalId.toInt(),
                productRemoteId.toInt(),
                productName,
                productImage,
                productDescription,
                productPrice.toInt(),
                0,
                0,
                1
            )
            CoroutineScope(Dispatchers.IO).launch {
                response = updateProductUseCase.invoke(product)
                if (response == 1) {
                    backgroundColor = Color(0xFF8BE400)
                } else {
                    backgroundColor = Color(0xFFFFA4AE)
                }
            }
        }
    }

    fun updateProductImage(bitmap: Bitmap){
        CoroutineScope(Dispatchers.IO).launch {
            val inputStream = ImageFile().convertBitmapToInputStream(bitmap)
            updateImageProductUseCase.invoke(inputStream)
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