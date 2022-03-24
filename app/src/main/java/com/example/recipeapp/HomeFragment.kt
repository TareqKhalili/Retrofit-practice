package com.example.recipeapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.adapters.RecipesRecyclerAdapter
import com.example.recipeapp.databinding.FragmentHomeBinding
import com.example.recipeapp.models.RecipesViewModel
import com.example.recipeapp.models.RecipesViewModelFactory
import com.example.recipeapp.repo.database.RecipesDatabase
import com.example.recipeapp.utlis.CONNECTED_TO_INTERNET

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RecipesViewModel
    private lateinit var adapter: RecipesRecyclerAdapter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dao = RecipesDatabase.getInstance(application).recipeDao

        val viewModelFactory = RecipesViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory)[RecipesViewModel::class.java]
        binding.viewModel = viewModel

        adapter = RecipesRecyclerAdapter()
        binding.recipesList.adapter = adapter

        viewModel.recipes.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        CONNECTED_TO_INTERNET.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Internet connected successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Internet connection is lost", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}