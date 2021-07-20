package com.vytautasdev.recipesappmvvm.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.vytautasdev.recipesappmvvm.data.DataStoreRepository
import com.vytautasdev.recipesappmvvm.data.MealDietCuisineType
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.API_KEY
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.DEFAULT_CUISINE_TYPE
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.DEFAULT_RECIPES_NUMBER
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.QUERY_API_KEY
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.QUERY_CUISINE
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.QUERY_DIET
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.QUERY_NUMBER
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.QUERY_SEARCH
import com.vytautasdev.recipesappmvvm.util.Constants.Companion.QUERY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE
    private var cuisineType = DEFAULT_CUISINE_TYPE

    private lateinit var mealAndDietAndCuisine: MealDietCuisineType

    var networkStatus = false
    var backOnline = false

    val readMealDietCuisineType = dataStoreRepository.readMealDietCuisineType
    val readBackOnline = dataStoreRepository.readBackOnline.asLiveData()

    fun saveMealDietCuisineType(
    ) = viewModelScope.launch(Dispatchers.IO) {
        if (this@RecipesViewModel::mealAndDietAndCuisine.isInitialized) {
            dataStoreRepository.saveMealDietCuisineType(
                mealAndDietAndCuisine.selectedMealType,
                mealAndDietAndCuisine.selectedMealTypeId,
                mealAndDietAndCuisine.selectedDietType,
                mealAndDietAndCuisine.selectedDietTypeId,
                mealAndDietAndCuisine.selectedCuisineType,
                mealAndDietAndCuisine.selectedCuisineTypeId)
        }
    }

    fun saveMealDietCuisineTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int,
        cuisineType: String,
        cuisineTypeId: Int
    ) {
        mealAndDietAndCuisine = MealDietCuisineType(
            mealType,
            mealTypeId,
            dietType,
            dietTypeId,
            cuisineType,
            cuisineTypeId
        )
    }

    fun saveBackOnline(backOnline: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.saveBackOnline(backOnline)
    }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        if (this@RecipesViewModel::mealAndDietAndCuisine.isInitialized) {
            queries[QUERY_TYPE] = mealAndDietAndCuisine.selectedMealType
            queries[QUERY_DIET] = mealAndDietAndCuisine.selectedDietType
            queries[QUERY_CUISINE] = mealAndDietAndCuisine.selectedCuisineType
        } else {
            queries[QUERY_TYPE] = DEFAULT_MEAL_TYPE
            queries[QUERY_DIET] = DEFAULT_DIET_TYPE
            queries[QUERY_CUISINE] = DEFAULT_CUISINE_TYPE
        }

        return queries
    }


    fun applySearchQuery(searchQuery: String): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

//        viewModelScope.launch {
//            readMealDietCuisineType.collect { value ->
//                mealType = value.selectedMealType
//                dietType = value.selectedDietType
//                cuisineType = value.selectedCuisineType
//            }
//        }

        queries[QUERY_SEARCH] = searchQuery
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }

    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No internet connection", Toast.LENGTH_LONG).show()
            saveBackOnline(true)
        } else if (networkStatus) {
            if (backOnline) {
                Toast.makeText(getApplication(), "You're back online", Toast.LENGTH_LONG).show()
                saveBackOnline(false)
            }
        }
    }
}

