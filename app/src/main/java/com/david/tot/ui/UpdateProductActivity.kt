package com.david.tot.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
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
import coil.compose.rememberImagePainter
import com.david.tot.ui.theme.TotTheme
import com.david.tot.util.IMAGE_BASE_URL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay

@AndroidEntryPoint
class UpdateProductActivity : ComponentActivity() {
    private val updateProductViewModel: UpdateProductViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        val bundle = intent.extras
        val id = bundle?.getString("id")
        val name = bundle?.getString("name")
        val description = bundle?.getString("description")
        val image = bundle?.getString("image")
        val price = bundle?.getString("price")
        val rer = id+name+description+image+price
        val rerds = id+name+description+image+price
        */
        val bundle = intent.extras
        updateProductViewModel.productLocalId = bundle!!.getString("id_local").toString()
        updateProductViewModel.productRemoteId = bundle!!.getString("id_remote").toString()
        updateProductViewModel.productName = bundle!!.getString("name").toString()
        updateProductViewModel.productDescription = bundle!!.getString("description").toString()
        updateProductViewModel.productImage = bundle!!.getString("image").toString()
        updateProductViewModel.productPrice = bundle!!.getString("price").toString()

        setContent {
            TotTheme(darkTheme = false) {
                //val updateProductViewModel = viewModel<UpdateProductViewModel>()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    //color = MaterialTheme.colors.background
                    color = updateProductViewModel.backgroundColor
                ) {
                    if(updateProductViewModel.responseCode != 0){
                        if (updateProductViewModel.responseCode == 201) {
                            Toast.makeText(LocalContext.current, "Producto editado exitosamente", Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(LocalContext.current, "No se pudo editar el producto", Toast.LENGTH_LONG).show()
                        }
                        Thread.sleep(500)
                        startActivity(Intent(this@UpdateProductActivity,MainActivity::class.java))
                        finish()
                    }
                    Column( horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.border(1.dp, Color.Gray, RectangleShape).fillMaxWidth().padding(20.dp)) {
                        Row(
                            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
                        ){
                            Text(text = ""+updateProductViewModel.productName, fontSize = 30.sp,fontWeight = FontWeight.Bold,textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                                    .fillMaxWidth())
                        }
                        Row(
                            modifier = Modifier.padding(all = 2.dp),horizontalArrangement = Arrangement.Center
                        ){
                            OutlinedTextField(
                                value = updateProductViewModel.productName ,
                                onValueChange = {
                                    updateProductViewModel.productName = it },
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
                                value = updateProductViewModel.productDescription,
                                onValueChange = {
                                    updateProductViewModel.productDescription = it
                                    updateProductViewModel.productDescription = updateProductViewModel.productDescription},
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
                                value = ""+updateProductViewModel.productPrice,
                                onValueChange = {
                                    updateProductViewModel.productPrice = it
                                    updateProductViewModel.productPrice = it},
                                label = { Text("Precio: "+updateProductViewModel.productPrice ) },
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                                    .fillMaxWidth()
                            )
                        }
                        Row(
                            modifier = Modifier.padding(all = 2.dp).height(100.dp).clickable {
                                                                               //TODO launch bundle to get image, la imagen que se va a adjuntar
                            },horizontalArrangement = Arrangement.Center
                        ){
                            Row(
                                modifier = Modifier.padding(all = 2.dp),
                                horizontalArrangement = Arrangement.Center
                            ){
                                Image(
                                    painter = rememberImagePainter(IMAGE_BASE_URL+updateProductViewModel.productImage),
                                    contentDescription = null,
                                    Modifier
                                        .height(100.dp)
                                )
                            }
                        }
                        Row(
                            modifier = Modifier.padding(all = 2.dp),horizontalArrangement = Arrangement.Center
                        ){
                            Button(onClick = {updateProductViewModel.updateProduct()},
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