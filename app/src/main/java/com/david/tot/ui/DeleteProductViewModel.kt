package com.david.tot.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.david.tot.domain.DeleteProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteProductViewModel @Inject constructor(private val deleteProductUseCase: DeleteProductUseCase) : ViewModel() {

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

    fun deleteProductImage(idProduct:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val responseCode = deleteProductUseCase.invoke(idProduct)
            if(responseCode == 200){
                activityDestroy = true
            }else{
                failedToast = true
            }
        }
    }
}