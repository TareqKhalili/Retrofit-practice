package com.example.recipeapp.utlis

import androidx.recyclerview.widget.DiffUtil
import com.example.recipeapp.data.database.entities.MealEntity

class RecipeDiffItemCallBack : DiffUtil.ItemCallback<MealEntity>() {
    override fun areItemsTheSame(oldItem: MealEntity, newItem: MealEntity): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: MealEntity, newItem: MealEntity): Boolean {
        return oldItem == newItem
    }

}