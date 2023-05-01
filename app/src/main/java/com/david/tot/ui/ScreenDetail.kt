package com.david.tot.ui

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import coil.compose.rememberImagePainter
import com.david.tot.R

@Composable
fun ScreenDetail(idRecipe: String,recipeViewModel:RecipeViewModel) {
    /*
    var recipe = recipeViewModel.recipeModel[0]
    recipeViewModel.recipeModel.forEach{
        if(it.id.equals(idRecipe.toInt())) recipe=it
    }
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
}
