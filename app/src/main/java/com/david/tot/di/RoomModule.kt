package com.david.tot.di

import android.content.Context
import androidx.room.Room
import com.david.tot.data.database.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val RECIPE_DATABASE_NAME = "product_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RecipeDatabase::class.java, RECIPE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideRecipeDao(db: RecipeDatabase) = db.getRecipeDao()
}

