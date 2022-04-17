package com.example.recipeapp.data.endPointApi

import com.example.recipeapp.models.RecipeData
import com.example.recipeapp.repo.Repository
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPointInterface: Repository {
    @GET("/api/json/v1/1/search.php")
    override suspend fun getRecipes(@Query("s") recipeName: String): RecipeData
}