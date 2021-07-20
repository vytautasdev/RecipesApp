package com.vytautasdev.recipesappmvvm.util
import com.vytautasdev.recipesappmvvm.BuildConfig

class Constants {

    companion object {

        const val BASE_URL = "https://api.spoonacular.com/"
        const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"
        const val API_KEY = BuildConfig.API_KEY

        const val RECIPE_RESULT_KEY = "recipeBundle"

        // API Query Keys
        const val QUERY_SEARCH = "query"
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_CUISINE = "cuisine"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"

        // ROOM Database
        const val DATABASE_NAME = "recipes_database"
        const val RECIPES_TABLE = "recipes_table"
        const val FAVORITE_RECIPES_TABLE = "favorite_recipes_table"
        const val FOOD_JOKE_TABLE = "food_joke_table"

        // Bottom Sheet & Preferences
        const val DEFAULT_RECIPES_NUMBER = "50"
        const val DEFAULT_MEAL_TYPE = "main course"
        const val DEFAULT_DIET_TYPE = "gluten free"
        const val DEFAULT_CUISINE_TYPE = "american"
        const val PREFERENCES_DATASTORE_NAME = "recipes_app_preferences"
        const val PREFERENCES_MEAL_TYPE = "mealType"
        const val PREFERENCES_MEAL_TYPE_ID = "mealTypeId"
        const val PREFERENCES_DIET_TYPE = "dietType"
        const val PREFERENCES_DIET_TYPE_ID = "dietTypeId"
        const val PREFERENCES_CUISINE_TYPE = "cuisineType"
        const val PREFERENCES_CUISINE_TYPE_ID = "cuisineTypeId"
        const val PREFERENCES_BACK_ONLINE = "backOnline"

    }
}