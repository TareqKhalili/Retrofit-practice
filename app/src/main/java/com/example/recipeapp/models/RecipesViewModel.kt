package com.example.recipeapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.repo.database.dao.RecipeDao
import com.example.recipeapp.repo.database.entities.MealEntity
import com.example.recipeapp.repo.endPointApi.EndPointInterface
import com.example.recipeapp.utlis.CONNECTED_TO_INTERNET
import kotlinx.coroutines.launch

class RecipesViewModel(private val dao: RecipeDao) : ViewModel() {
    private val _recipes = MutableLiveData<List<MealEntity>>()
    val recipes: LiveData<List<MealEntity>>
        get() = _recipes

    var recipeName: String = ""

    fun getRecipe() {
        viewModelScope.launch {
            _recipes.value = if (recipeName.isNotEmpty()) {
                getMeals()
            } else {
                emptyList()
            }
        }
    }

    private suspend fun getMeals(): List<MealEntity> {
        return if (CONNECTED_TO_INTERNET.value == true) {
            val meals = EndPointInterface.create().getRecipe(recipeName).meals
            if (!meals.isNullOrEmpty()) {
                dao.insertAll(meals)
            }
            meals
        } else {
            dao.getRecipes(recipeName)
        }
    }
}