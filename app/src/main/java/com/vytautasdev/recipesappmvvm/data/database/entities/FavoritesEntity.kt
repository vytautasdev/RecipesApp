package com.vytautasdev.recipesappmvvm.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vytautasdev.recipesappmvvm.models.Result
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.FAVORITE_RECIPES_TABLE

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)