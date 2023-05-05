package com.david.tot.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScreenAddProduct(addProductViewModel:AddProductViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var productName by rememberSaveable { mutableStateOf("") }
        var productDescripcion by rememberSaveable { mutableStateOf("") }
        var productPrice by rememberSaveable { mutableStateOf("") }
        Text(text = "AGREGAR PRODUCTO", fontSize = 30.sp,fontWeight = FontWeight.Bold,textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 16.dp, end = 30.dp, top = 20.dp).fillMaxWidth())

        //var productPrice: Int by rememberSaveable { mutableStateOf(0) }

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
        OutlinedTextField(
            value = productDescripcion,
            onValueChange = {
                addProductViewModel.productDescription = it
                productDescripcion = addProductViewModel.productDescription},
            label = { Text("Breve descripcion:") },
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 20.dp).fillMaxWidth()
        )
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
        Button(onClick = {
            addProductViewModel.addProduct() },
            shape = RoundedCornerShape(50)) {
            Text("GUARDAR")
        }
    }
}