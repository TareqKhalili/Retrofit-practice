package com.example.recipeapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.repo.Api
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {
    private val _recipes = MutableLiveData<List<Meal>>()
    val recipes: LiveData<List<Meal>>
        get() = _recipes

    var recipeName: String = ""

    fun getRecipe() {
        println(recipeName)
        if (recipeName.trim() == "") {
            _recipes.value = listOf()
        } else {
            viewModelScope.launch {
                Api.getRecipes(recipeName).let {
                    _recipes.value = it
                }
            }
        }
    }
}