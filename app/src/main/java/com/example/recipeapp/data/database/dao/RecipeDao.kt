package com.example.recipeapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipeapp.data.database.entities.MealEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes WHERE recipe_name LIKE '%' || :name || '%'")
    fun getRecipes(name: String): List<MealEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(recipes: List<MealEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(recipes: MealEntity)
}