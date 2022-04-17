package com.example.recipeapp.repo

import com.example.recipeapp.models.RecipeData

interface Repository {
    suspend fun getRecipes(recipeName: String): RecipeData
}