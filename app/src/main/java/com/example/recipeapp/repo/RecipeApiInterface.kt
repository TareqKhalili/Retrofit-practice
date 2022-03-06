package com.example.recipeapp.repo

import com.example.recipeapp.models.RecipeData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiInterface {
    @GET("/api/json/v1/1/search.php")
    suspend fun getRecipe(@Query("s") recipeName: String): Response<RecipeData>
}