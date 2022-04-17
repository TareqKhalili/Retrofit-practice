package com.example.recipeapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.database.dao.RecipeDao
import com.example.recipeapp.data.database.entities.MealEntity
import com.example.recipeapp.data.endPointApi.EndPointInterface
import com.example.recipeapp.repo.Repository
import com.example.recipeapp.utlis.CONNECTED_TO_INTERNET
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val dao: RecipeDao,
    private val repo: Repository,
) : ViewModel() {

    @Inject
    lateinit var username: String

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
            val meals = repo.getRecipes(recipeName).meals
            if (!meals.isNullOrEmpty()) {
                dao.insertAll(meals)
            }
            meals
        } else {
            dao.getRecipes(recipeName)
        }
    }
}