package com.david.tot.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.david.tot.data.database.dao.RecipeDao
import com.david.tot.data.database.entities.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 6)
abstract class RecipeDatabase: RoomDatabase() {

    abstract fun getRecipeDao(): RecipeDao
}
