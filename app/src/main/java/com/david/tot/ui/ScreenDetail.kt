package com.david.tot.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.david.tot.util.IMAGE_BASE_URL

@Composable
fun ScreenDetail(remoteIdProduct: String, recipeViewModel:RecipeViewModel) {

    var launchUpdateProductActivity by rememberSaveable { mutableStateOf(false) }
    var launchDeleteProductActivity by rememberSaveable { mutableStateOf(false) }

    for(product in recipeViewModel.recipeModel){
        if (product.id == remoteIdProduct.toInt()){
            recipeViewModel.productLocalId = product.local_id
            recipeViewModel.productRemoteId= product.id
            recipeViewModel.productName= product.name
            recipeViewModel.productDescription= product.description
            recipeViewModel.productImage= product.image
            recipeViewModel.productPrice= product.price
            val p = 1
            val gg = 1+p
        }else{
            val p = 0
            val gg = 0+p
        }
    }

    if(launchUpdateProductActivity){
        launchUpdateProductActivity = false
        val context = LocalContext.current
        val intent = Intent(context,UpdateProductActivity::class.java)
        intent.putExtra("id_local", recipeViewModel.productLocalId.toString())
        intent.putExtra("id_remote", recipeViewModel.productRemoteId.toString())
        intent.putExtra("name", recipeViewModel.productName)
        intent.putExtra("description", recipeViewModel.productDescription)
        intent.putExtra("image", recipeViewModel.productImage)
        intent.putExtra("price", recipeViewModel.productPrice.toString())
        context.startActivity(intent)
    }

    if(launchDeleteProductActivity){
        launchDeleteProductActivity = false
        val context = LocalContext.current
        val intent = Intent(context,DeleteProductActivity::class.java)
        intent.putExtra("id_local", recipeViewModel.productLocalId.toString())
        intent.putExtra("id_remote", recipeViewModel.productRemoteId.toString())
        intent.putExtra("name", recipeViewModel.productName)
        intent.putExtra("description", recipeViewModel.productDescription)
        intent.putExtra("image", recipeViewModel.productImage)
        intent.putExtra("price", recipeViewModel.productPrice.toString())
        context.startActivity(intent)
    }

    Column( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .border(1.dp, Color.Gray, RectangleShape)
            .fillMaxWidth()
            .padding(20.dp)) {
        Row(
            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = recipeViewModel.productName,
                textAlign = TextAlign.Center, color = Color.Black, fontSize = 32.sp, fontWeight = FontWeight.Black)
        }
        Row(
            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = recipeViewModel.productDescription,
                textAlign = TextAlign.Center, color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Black)
        }
        Row(
            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Precio: "+recipeViewModel.productPrice,
                textAlign = TextAlign.Center, color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Black)
        }
        Row(
            modifier = Modifier
                .padding(all = 12.dp)
                .height(150.dp),
            horizontalArrangement = Arrangement.Center,
        ){
            Image(
                painter = rememberImagePainter(IMAGE_BASE_URL+recipeViewModel.productImage),
                contentDescription = null,
                Modifier
                    .fillMaxSize()
                    .height(50.dp)
            )
        }


        Row(
            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
        ){
            androidx.compose.material3.Button(
                onClick = { launchUpdateProductActivity=true},
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .height(60.dp)
            ) {
                androidx.compose.material3.Text("EDITAR")
            }
        }
        Row(
            modifier = Modifier.padding(all = 20.dp),horizontalArrangement = Arrangement.Center
        ){
            androidx.compose.material3.Button(
                onClick = { launchDeleteProductActivity=true},
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .height(60.dp)
            ) {
                androidx.compose.material3.Text("ELIMINAR")
            }
        }
    }

    /*
    Column( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.border(1.dp, Color.Gray, RectangleShape).fillMaxWidth().padding(20.dp)) {
        Row(
            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = recipe.name,
                textAlign = TextAlign.Center, color = Color.Black, fontSize = 32.sp, fontWeight = FontWeight.Black)
        }
        Row(
            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = recipe.description,
                textAlign = TextAlign.Center, color = Color.Black, fontSize = 32.sp, fontWeight = FontWeight.Black)
        }
        Row(
            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
        ){
            val bundle = Bundle()
            bundle.putDouble("longitude",recipe.coordinate.longitude)
            bundle.putDouble("latitude",recipe.coordinate.latitude)
            val context = LocalContext.current
            val intent = Intent(context,MapActivity::class.java)
            intent.putExtras(bundle)
            Button(onClick = {startActivity(context,intent,bundleOf())},elevation =  ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 15.dp,
                disabledElevation = 0.dp
            )) {
                Text(text = stringResource(R.string.map_button_text))
            }
        }
        Row(
            modifier = Modifier.padding(all = 12.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = rememberImagePainter(recipe.image),
                contentDescription = null,
                Modifier
                    .fillMaxSize()
                    .height(200.dp)
            )
        }
    }
    */
    //Thread.sleep(500)
    //recipeViewModel.intentLauncher
    val r =78
}
