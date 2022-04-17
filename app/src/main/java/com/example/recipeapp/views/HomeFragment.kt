package com.example.recipeapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.recipeapp.adapters.RecipesRecyclerAdapter
import com.example.recipeapp.databinding.FragmentHomeBinding
import com.example.recipeapp.models.RecipesViewModel
import com.example.recipeapp.utlis.CONNECTED_TO_INTERNET
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RecipesRecyclerAdapter

    private val viewModel: RecipesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setUpWelcomeMessage()

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
                Toast.makeText(context, "Internet connected successfully", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(context, "Internet connection is lost", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }


    private fun setUpWelcomeMessage() {
        val welcomeMessage = "Hey ${viewModel.username}, what do you want to eat today?"
        binding.welcomingText.text = welcomeMessage
    }
}