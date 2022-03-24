package com.example.recipeapp.models

import com.example.recipeapp.repo.database.entities.MealEntity

data class RecipeData(
    val meals: List<MealEntity>,
)