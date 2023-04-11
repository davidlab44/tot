package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class Recipe (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "description") val description: String,
    @Embedded val coordinate: Coordinate
    )
fun Recipe.toDomain() = Recipe(id,name,image,description,coordinate)
fun Recipe.toDatabase() = Recipe(name = name, image =  image, description = description, coordinate = coordinate)
//fun RecipeEntity.toDomain() = Recipe(id,name,image,description,coordinate)