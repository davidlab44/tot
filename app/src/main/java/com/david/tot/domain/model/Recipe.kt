package com.david.tot.domain.model

import com.david.tot.data.database.entities.RecipeEntity
import com.david.tot.data.model.RecipeModel

data class Recipe (val id:Int, val name:String, val image:String, val description:String, val coordinate:Coordinate)
fun RecipeModel.toDomain() = Recipe(id,name,image,description,coordinate)
fun RecipeEntity.toDomain() = Recipe(id,name,image,description,coordinate)