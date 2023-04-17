package com.david.dfv.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.david.dfv.data.database.dao.RecipeDao
import com.david.dfv.domain.model.Recipe

@Database(entities = [Recipe::class], version = 1)
abstract class RecipeDatabase: RoomDatabase() {

    abstract fun getRecipeDao(): RecipeDao
}
