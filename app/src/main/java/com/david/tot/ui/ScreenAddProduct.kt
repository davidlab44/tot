package com.david.tot.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun ScreenAddProduct(addProductViewModel:AddProductViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //var productPrice: Int by rememberSaveable { mutableStateOf(0) }
        OutlinedTextField(
            value = "",
            onValueChange = {addProductViewModel.productName = it},
            label = { Text("Nombre:") },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = {addProductViewModel.productDescription = it},
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
            value = "",
            onValueChange = {addProductViewModel.productPrice = it.toInt()},
            label = { Text("Precio: "+addProductViewModel.productPrice) },
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