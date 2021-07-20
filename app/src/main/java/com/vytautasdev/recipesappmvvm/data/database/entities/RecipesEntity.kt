package com.vytautasdev.recipesappmvvm.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vytautasdev.recipesappmvvm.models.FoodRecipe
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}