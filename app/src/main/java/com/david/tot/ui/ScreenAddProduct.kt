package com.david.tot.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.david.tot.domain.model.Product


@Composable
fun ScreenAddProduct(recipeViewModel:RecipeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var product: Product by rememberSaveable { mutableStateOf(Product(0,"","","",0,0,0,0)) }
        var productPrice: Int by rememberSaveable { mutableStateOf(0) }
        OutlinedTextField(
            value = "",
            onValueChange = {product.name = it},
            label = { Text("Nombre:") },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = {product.description = it},
            label = { Text("Breve descripcion:") },
            modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 20.dp)
            .fillMaxWidth()
        )
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = "",
            onValueChange = {productPrice = it.toInt()},
            label = { Text("Precio: "+productPrice) },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                .fillMaxWidth()
            )
        Button(onClick = {
            product.price = productPrice
            recipeViewModel.addProduct(product) },
            shape = RoundedCornerShape(50)) {
            Text("GUARDAR")
        }
    }
}