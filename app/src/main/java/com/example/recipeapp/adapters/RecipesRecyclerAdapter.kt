package com.example.recipeapp.adapters

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recipeapp.models.Meal
import com.example.recipeapp.R
import com.example.recipeapp.databinding.RecipeListItemBinding
import com.example.recipeapp.utlis.RecipeDiffItemCallBack

class RecipesRecyclerAdapter :
    ListAdapter<Meal, RecipesRecyclerAdapter.RecipesViewHolder>(RecipeDiffItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.recipe_list_item,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RecipesViewHolder(itemView: RecipeListItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val recipeName = itemView.recipeName
        private val recipeImage = itemView.recipeImage
        private val cardRecipe = itemView.cardRecipe


        fun bind(meal: Meal) {
            recipeName.text = meal.strMeal

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(meal.strMealThumb)
                .into(recipeImage)

            cardRecipe.setOnClickListener {
                println(meal.strYoutube.substringAfterLast('='))
                val intentApp =
                    Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + meal.strYoutube.substringAfterLast('=')))
                val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(meal.strYoutube))

                try {
                    itemView.context.startActivity(intentApp)
                } catch (ex: ActivityNotFoundException) {
                    itemView.context.startActivity(intentBrowser)
                }
            }
        }
    }
}