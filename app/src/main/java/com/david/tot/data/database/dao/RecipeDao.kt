package com.david.tot.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.tot.domain.model.Product

@Dao
interface RecipeDao {

    @Query("SELECT * FROM product_table")
    suspend fun getAllRecipes():List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes:List<Product>)

    @Query("DELETE FROM product_table")
    suspend fun deleteAllRecipes()

    @Query("SELECT * FROM product_table WHERE name LIKE :recipeHash ORDER BY name DESC LIMIT 10")
    fun getFilteredRecipes(recipeHash: String): List<Product>

}