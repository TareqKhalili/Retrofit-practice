package com.example.recipeapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.recipeapp.R
import com.example.recipeapp.data.database.RecipesDatabase
import com.example.recipeapp.data.database.dao.RecipeDao
import com.example.recipeapp.data.endPointApi.EndPointInterface
import com.example.recipeapp.repo.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRecipeDao(recipeDatabase: RecipesDatabase): RecipeDao {
        return recipeDatabase.recipeDao
    }

    @Provides
    @Singleton
    fun providesRecipesDataBase(
        @ApplicationContext context: Context
    ): RecipesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            RecipesDatabase::class.java,
            "recipes_database"
        ).allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Repository {
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EndPointInterface::class.java)
    }

    @Provides
    @Singleton
    fun providesSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(
            R.string.shared_preferences.toString(),
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideUsername(
        sharedPreferences: SharedPreferences
    ): String {
        return sharedPreferences.getString(
            R.string.saved_username_key.toString(), ""
        ).toString()
    }
}