package com.example.recipeapp.utlis

import androidx.recyclerview.widget.DiffUtil
import com.example.recipeapp.models.Meal

class RecipeDiffItemCallBack : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem == newItem
    }

}