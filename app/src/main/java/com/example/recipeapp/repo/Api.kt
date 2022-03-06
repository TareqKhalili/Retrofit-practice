package com.example.recipeapp.repo

import com.example.recipeapp.models.Meal
import com.example.recipeapp.models.RecipeData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

object Api {
    suspend fun getRecipes(recipeName: String): List<Meal> {
        val scope = CoroutineScope(Dispatchers.IO)
        var data: Response<RecipeData>? = null
        val routine = scope.launch {
            data = try {
                RetrofitInstance.api.getRecipe(recipeName)
            } catch (error: IOException) {
                null
            }
        }
        routine.join()

        return if (data != null && data!!.isSuccessful) {
            data?.body()!!.meals
        } else {
            listOf()
        }
    }
}
