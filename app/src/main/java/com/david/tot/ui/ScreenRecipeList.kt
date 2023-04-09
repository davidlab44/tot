package com.david.tot.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.david.tot.domain.model.Recipe

@Composable
fun ScreenRecipeList(navegarPantalla2: (String) -> Unit,recipeViewModel:RecipeViewModel) {
    recipeViewModel.onCreate()
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val listModifier = Modifier.fillMaxSize().background(Color.White).padding(10.dp).align(Alignment.CenterHorizontally)
        LazyColumn(modifier = listModifier) {
            items(recipeViewModel.recipeModel) { recipe ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(16.dp).clickable{ navegarPantalla2(recipe.id.toString()) },
                    elevation = 10.dp,
                    content = {
                        Column( horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.border(1.dp, Color.Gray, RectangleShape).fillMaxWidth().padding(20.dp)) {
                            Row(
                                modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
                            ){
                                Text(
                                    text = recipe.name,
                                    textAlign =TextAlign.Center, color = Color.Black, fontSize = 32.sp, fontWeight = FontWeight.Black)
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
                    }
                )
            }
        }
    }
}