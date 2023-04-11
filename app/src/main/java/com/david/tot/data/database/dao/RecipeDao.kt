package com.david.tot.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.tot.data.database.entities.RecipeEntity

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe_table")
    suspend fun getAllRecipes():List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes:List<RecipeEntity>)

    @Query("DELETE FROM recipe_table")
    suspend fun deleteAllRecipes()

    @Query("SELECT * FROM recipe_table WHERE name LIKE :recipeHash ORDER BY name DESC LIMIT 10")
    fun getFilteredRecipes(recipeHash: String): List<RecipeEntity>

}