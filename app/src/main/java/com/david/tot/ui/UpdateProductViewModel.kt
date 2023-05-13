package com.david.tot.ui

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
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
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
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

    @RequiresApi(Build.VERSION_CODES.R)
    fun updateProductImage(bitmap: Bitmap){
        //val byteArray = convertBitmapToFile(bitmap)
        CoroutineScope(Dispatchers.IO).launch {
            //val byteArray: ByteArray? = ImageFile().encodeToBase64(bitmap)
            val file = bitmapToFile(bitmap,"gato.png")

            if (file != null) {
                updateImageProductUseCase.invoke(file)
            }else{
                Log.e("TAG","bytearray es null")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun bitmapToFile(bitmap: Bitmap, fileNameToSave: String): File? { // File name like "image.png"
        //create a file to write bitmap data
        var file: File? = null
        var bitmapdata = byteArrayOf(0x2E, 0x38)
        return try {
            //file = File(Environment.getExternalStorageDirectory().toString() + File.separator + fileNameToSave)
            //TODO ojo Environment.getStorageDirectory() requires min sdk 30
            //file = File(Environment.getStorageDirectory().toString() + File.separator + fileNameToSave)
            val path = System.getProperty("user.dir")
            Log.e("Actual directory",path )
            file = File("/storage/self/primary/Download" + File.separator + fileNameToSave)
            Log.e("#001",Environment.getStorageDirectory().toString() + File.separator + fileNameToSave)
            Log.e("Environment.getStorageDirectory().toString()",Environment.getStorageDirectory().toString() + File.separator + fileNameToSave)
            Log.e("File.separator",File.separator)
            Log.e("filename",fileNameToSave)
            Log.e("Environment.getExternalStorageDirectory().toString() ",Environment.getExternalStorageDirectory().toString() )

            file.createNewFile()


            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            bitmapdata = bos.toByteArray()

            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            //file
                //val isAnImage = ImageFile.decodeBase64("")
            val isAnImage = ImageFile().isAnImage("/storage/self/primary/Download/gato.png")
            val isAnImage2 = isAnImage
            file
        } catch (e: Exception) {
            e.printStackTrace()
            file // it will return null
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