package com.example.recipeapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class MealEntity(
    @PrimaryKey
    @ColumnInfo(name = "recipe_id")
    val idMeal: String,

    @ColumnInfo(name = "recipe_name")
    val strMeal: String,

    @ColumnInfo(name = "recipe_video")
    val strYoutube: String,

    @ColumnInfo(name = "recipe_image")
    val strMealThumb: String,
)