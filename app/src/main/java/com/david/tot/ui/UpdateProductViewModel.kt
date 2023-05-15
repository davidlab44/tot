package com.david.tot.ui

import android.graphics.Bitmap
import android.os.Build
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class UpdateProductViewModel @Inject constructor(
    private val updateProductUseCase: UpdateProductUseCase,
    private val updateImageProductUseCase: UpdateImageProductUseCase,
    ) : ViewModel() {

    var responseCode by mutableStateOf<Int>(0)
    var productRemoteId by mutableStateOf<String>("")
    var productLocalId by mutableStateOf<String>("")
    var productName by mutableStateOf<String>("")
    var productDescription by mutableStateOf<String>("")
    var productImage by mutableStateOf<String>("")
    var productPrice by mutableStateOf<String>("")
    var backgroundColor: Color by mutableStateOf(Color.Transparent)
    var activityDestroy by mutableStateOf<Boolean>(false)
    var failedToast by mutableStateOf<Boolean>(false)

    fun updateProduct(){
        if(productName.trim().length>1&&productDescription.trim().length>1&&productPrice.toInt()>1) {
            val product = Product(productLocalId.toInt(),productRemoteId.toInt(),productName,productImage,productDescription,
                productPrice.toInt(),0,0,1
            )
            CoroutineScope(Dispatchers.IO).launch {
                responseCode = updateProductUseCase.invoke(product)
                if (responseCode == 200) {
                    backgroundColor = Color(0xFF8BE400)
                    activityDestroy = true
                } else {
                    backgroundColor = Color(0xFFFFA4AE)
                    failedToast = true
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun updateProductImage(idProduct:Int,bitmap: Bitmap){
        CoroutineScope(Dispatchers.IO).launch {
            val time = System.currentTimeMillis()
            val file = bitmapToFile(bitmap,"gato"+time+"gato.png")
            if (file != null) {
                val responseCode = updateImageProductUseCase.invoke(idProduct,file)
                if(responseCode == 200){
                    activityDestroy = true
                }else{
                    failedToast = true
                }
            }else{
                Log.e("TAG","file is null")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun bitmapToFile(bitmap: Bitmap, fileNameToSave: String): File? {
        var file: File? = null
        var bitmapdata = byteArrayOf(0x2E, 0x38)
        return try {
            file = File("/storage/self/primary/Download" + File.separator + fileNameToSave)
            file.createNewFile()
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
            bitmapdata = bos.toByteArray()
            val fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            file
        }
    }
}