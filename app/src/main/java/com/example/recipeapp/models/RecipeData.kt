package com.example.recipeapp.models

import com.example.recipeapp.data.database.entities.MealEntity

data class RecipeData(
    val meals: List<MealEntity>,
)