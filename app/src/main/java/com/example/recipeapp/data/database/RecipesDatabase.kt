package com.example.recipeapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipeapp.data.database.dao.RecipeDao
import com.example.recipeapp.data.database.entities.MealEntity

@Database(entities = [MealEntity::class], version = 1)
abstract class RecipesDatabase : RoomDatabase() {
    abstract val recipeDao: RecipeDao

//    companion object {
//        @Volatile
//        private var INSTANCE: RecipesDatabase? = null
//
//        fun getInstance(context: Context): RecipesDatabase {
//            synchronized(this) {
//                var instance = INSTANCE
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        RecipesDatabase::class.java,
//                        "recipes_database"
//                    ).allowMainThreadQueries().build()
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
//    }
}