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
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
                    /*
                    //TODO OJO refactor este IF pasa actualizandose constantemente despues de selccionar la imagen despues de lanzar este metodo rememberLauncherForActivityResult()
                    if (updateProductViewModel.response == 1) {
                        Toast.makeText(LocalContext.current, "Producto editado exitosamente", Toast.LENGTH_LONG).show()
                        Thread.sleep(500)
                        startActivity(Intent(this@UpdateProductActivity,MainActivity::class.java))
                        finish()
                    }
                    */
                    Column( horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .border(1.dp, Color.Gray, RectangleShape)
                            .fillMaxWidth()
                            .padding(20.dp)) {
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
                            modifier = Modifier
                                .padding(all = 2.dp)
                                .height(100.dp)
                                .clickable {
                                    //TODO launch bundle to get image, la imagen que se va a adjuntar
                                },horizontalArrangement = Arrangement.Center
                        ){
                            Row(
                                modifier = Modifier
                                    .padding(all = 2.dp),
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
                            modifier = Modifier
                                .padding(all = 2.dp),
                            horizontalArrangement = Arrangement.Center
                        ){
                            var imageUri by remember {mutableStateOf<Uri?>(null)}
                            val context = LocalContext.current
                            val bitmap =  remember {
                                mutableStateOf<Bitmap?>(null)
                            }

                            val launcher = rememberLauncherForActivityResult(contract =
                            ActivityResultContracts.GetContent()) { uri: Uri? ->
                                imageUri = uri
                            }

                            Column() {
                                Button(onClick = {
                                    launcher.launch("image/*")
                                }) {
                                    Text(text = "Pick image")
                                }
                                Spacer(modifier = Modifier.height(22.dp))
                                Button(onClick = { bitmap.value?.let {
                                    updateProductViewModel.updateProductImage(
                                        it
                                    )
                                } }) {
                                    Text(text = "ENVIAR IMAGEN")
                                }
                                Spacer(modifier = Modifier.height(22.dp))

                                imageUri?.let {
                                    if (Build.VERSION.SDK_INT < 28) {
                                        bitmap.value = MediaStore.Images
                                            .Media.getBitmap(context.contentResolver,it)

                                    } else {
                                        val source = ImageDecoder
                                            .createSource(context.contentResolver,it)
                                        bitmap.value = ImageDecoder.decodeBitmap(source)
                                    }

                                    bitmap.value?.let {  btm ->
                                        Image(bitmap = btm.asImageBitmap(),
                                            contentDescription =null,
                                            modifier = Modifier.size(400.dp))
                                    }
                                }

                            }
                        }
                        Row(
                            modifier = Modifier.padding(all = 2.dp),horizontalArrangement = Arrangement.Center
                        ){
                            /*
                            Button(onClick = {updateProductViewModel.updateProduct()},
                                modifier = Modifier
                                    .padding(bottom = 10.dp)
                                    .height(60.dp)
                            ) {
                                Text("GUARDAR")
                            }

                             */
                        }
                    }
                }
            }
        }
    }
}