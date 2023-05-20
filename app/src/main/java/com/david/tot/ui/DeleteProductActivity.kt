package com.david.tot.ui

import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.david.tot.ui.theme.TotTheme
import com.david.tot.util.IMAGE_BASE_URL
import dagger.hilt.android.AndroidEntryPoint

import android.graphics.Bitmap

import android.net.Uri

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

@AndroidEntryPoint
class DeleteProductActivity : ComponentActivity() {
    private val deleteProductViewModel: DeleteProductViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        deleteProductViewModel.productLocalId = bundle!!.getString("id_local").toString()
        deleteProductViewModel.productRemoteId = bundle!!.getString("id_remote").toString()
        deleteProductViewModel.productName = bundle!!.getString("name").toString()
        deleteProductViewModel.productDescription = bundle!!.getString("description").toString()
        deleteProductViewModel.productImage = bundle!!.getString("image").toString()
        deleteProductViewModel.productPrice = bundle!!.getString("price").toString()

        setContent {
            TotTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = deleteProductViewModel.backgroundColor
                ) {
                    var enabledImage  by rememberSaveable { mutableStateOf(true) }
                    var enabledProduct  by rememberSaveable { mutableStateOf(true) }
                    val context = LocalContext.current

                    Column( horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .border(1.dp, Color.Gray, RectangleShape)
                            .fillMaxWidth()
                            .padding(20.dp)) {
                        Row(
                            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
                        ){
                            Text(text = ""+deleteProductViewModel.productName, fontSize = 30.sp,fontWeight = FontWeight.Bold,textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                                    .fillMaxWidth())
                        }
                        Row(
                            modifier = Modifier.padding(all = 2.dp),horizontalArrangement = Arrangement.Center
                        ){
                            OutlinedTextField(
                                value = deleteProductViewModel.productName ,
                                onValueChange = {
                                    deleteProductViewModel.productName = it },
                                label = { Text("Nombre:") },
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                                    .fillMaxWidth()
                            )
                        }
                        Row(
                            modifier = Modifier.padding(all = 2.dp),horizontalArrangement = Arrangement.Center
                        ){
                            OutlinedTextField(
                                value = deleteProductViewModel.productDescription,
                                onValueChange = {
                                    deleteProductViewModel.productDescription = it
                                    deleteProductViewModel.productDescription = deleteProductViewModel.productDescription},
                                label = { Text("Breve descripcion:") },
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                                    .fillMaxWidth()
                            )
                        }
                        Row(
                            modifier = Modifier.padding(all = 2.dp),horizontalArrangement = Arrangement.Center
                        ){
                            OutlinedTextField(
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.None,
                                    autoCorrect = true,
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Next
                                ),
                                value = ""+deleteProductViewModel.productPrice,
                                onValueChange = {
                                    deleteProductViewModel.productPrice = it
                                    deleteProductViewModel.productPrice = it},
                                label = { Text("Precio: "+deleteProductViewModel.productPrice ) },
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                                    .fillMaxWidth()
                            )
                        }
                        Row(
                            modifier = Modifier.padding(all = 2.dp).height(100.dp),
                            horizontalArrangement = Arrangement.Center
                        ){
                            Image(
                                painter = rememberImagePainter(IMAGE_BASE_URL+deleteProductViewModel.productImage),
                                contentDescription = null,
                                Modifier.height(100.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier.padding(all = 20.dp),horizontalArrangement = Arrangement.Center
                        ){
                            Button(
                                enabled = enabledProduct,
                                modifier = Modifier.padding(1.dp),
                                onClick = {
                                    enabledImage = false
                                    deleteProductViewModel.deleteProductImage(deleteProductViewModel.productRemoteId.toInt())
                                }) {
                                Text(text = "ELIMINAR")
                            }
                        }
                    }

                    if(deleteProductViewModel.activityDestroy){
                        deleteProductViewModel.activityDestroy = false
                        Toast.makeText(context, "Producto eliminado exitosamente", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    }
                    if(deleteProductViewModel.failedToast){
                        Toast.makeText(context, "No se pudo eliminar el producto", Toast.LENGTH_SHORT).show()
                        deleteProductViewModel.failedToast =false
                    }
                }
            }
        }
    }
}