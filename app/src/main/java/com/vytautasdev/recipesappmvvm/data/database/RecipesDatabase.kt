package com.vytautasdev.recipesappmvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vytautasdev.recipesappmvvm.data.database.entities.FavoritesEntity
import com.vytautasdev.recipesappmvvm.data.database.entities.FoodJokeEntity
import com.vytautasdev.recipesappmvvm.data.database.entities.RecipesEntity

@Database(
    entities = [RecipesEntity::class, FavoritesEntity::class, FoodJokeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase : RoomDatabase() {

    abstract fun recipesDao(): RecipesDao
}


