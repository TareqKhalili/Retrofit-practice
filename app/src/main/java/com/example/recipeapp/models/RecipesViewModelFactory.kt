package com.example.recipeapp.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.repo.database.dao.RecipeDao

@Suppress("UNCHECKED_CAST")
class RecipesViewModelFactory(private val dao: RecipeDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipesViewModel::class.java)) {
            return RecipesViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}