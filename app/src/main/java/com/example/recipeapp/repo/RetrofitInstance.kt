package com.example.recipeapp.repo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: RecipeApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApiInterface::class.java)
    }
}