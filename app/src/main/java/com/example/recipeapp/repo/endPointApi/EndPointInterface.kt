package com.example.recipeapp.repo.endPointApi

import com.example.recipeapp.models.RecipeData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPointInterface {
    @GET("/api/json/v1/1/search.php")
    suspend fun getRecipe(@Query("s") recipeName: String): RecipeData

    companion object {
        fun create(): EndPointInterface {
            return Retrofit.Builder()
                .baseUrl("https://www.themealdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EndPointInterface::class.java)
        }
    }
}