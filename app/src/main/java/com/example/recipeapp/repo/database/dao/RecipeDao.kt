package com.example.recipeapp.repo.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipeapp.repo.database.entities.MealEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getAll(): List<MealEntity>

    @Query("SELECT * FROM recipes WHERE recipe_name LIKE '%' || :name || '%'")
    fun getRecipes(name: String): List<MealEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(recipes: List<MealEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOne(recipes: MealEntity)
}