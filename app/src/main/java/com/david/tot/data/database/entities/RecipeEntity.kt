package com.david.tot.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.david.tot.domain.model.Coordinate
import com.david.tot.domain.model.Recipe


@Entity(tableName = "recipe_table")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "description") val description: String,
    @Embedded val coordinate: Coordinate
)


fun Recipe.toDatabase() = RecipeEntity(name = name, image =  image, description = description, coordinate = coordinate)