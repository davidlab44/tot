package com.david.tot.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.*
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.david.tot.ui.theme.TotTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay

@AndroidEntryPoint
class AddProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TotTheme(darkTheme = false) {
                val addProductViewModel = viewModel<AddProductViewModel>()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    //color = MaterialTheme.colors.background
                    color = addProductViewModel.backgroundColor
                ) {

                    var productName by rememberSaveable { mutableStateOf("") }
                    var productDescripcion by rememberSaveable { mutableStateOf("") }
                    var productPrice by rememberSaveable { mutableStateOf("") }
                    if(addProductViewModel.responseCode != 0){
                        if (addProductViewModel.responseCode == 201) {
                            Toast.makeText(LocalContext.current, "Producto creado exitosamente", Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(LocalContext.current, "No se pudo crear el producto", Toast.LENGTH_LONG).show()
                        }
                        Thread.sleep(1000)
                        startActivity(Intent(this@AddProductActivity,MainActivity::class.java))
                        finish()
                    }
                    Column( horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.border(1.dp, Color.Gray, RectangleShape).fillMaxWidth().padding(20.dp)) {
                        Row(
                            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
                        ){
                            Text(text = "AGREGAR PRODUCTO", fontSize = 30.sp,fontWeight = FontWeight.Bold,textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 30.dp, top = 20.dp)
                                    .fillMaxWidth())
                        }
                        Row(
                            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
                        ){
                            OutlinedTextField(
                                value = productName,
                                onValueChange = {
                                    addProductViewModel.productName = it
                                    productName = addProductViewModel.productName},
                                label = { Text("Nombre:") },
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                                    .fillMaxWidth()
                            )
                        }
                        Row(
                            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
                        ){
                            OutlinedTextField(
                                value = productDescripcion,
                                onValueChange = {
                                    addProductViewModel.productDescription = it
                                    productDescripcion = addProductViewModel.productDescription},
                                label = { Text("Breve descripcion:") },
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                                    .fillMaxWidth()
                            )
                        }
                        Row(
                            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
                        ){
                            OutlinedTextField(
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.None,
                                    autoCorrect = true,
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Next
                                ),
                                value = ""+productPrice,
                                onValueChange = {
                                    addProductViewModel.productPrice = it.toInt()
                                    productPrice = it},
                                label = { Text("Precio: "+productPrice) },
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                                    .fillMaxWidth()
                            )
                        }
                        Row(
                            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
                        ){





                            Button(onClick = {addProductViewModel.addProduct()},
                                modifier = Modifier
                                    .padding(bottom = 10.dp)
                                    .height(60.dp)
                            ) {
                                Text("GUARDAR")
                            }
                        }
                    }
                }
            }
        }
    }
}