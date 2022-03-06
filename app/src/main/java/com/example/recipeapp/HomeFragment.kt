package com.example.recipeapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
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

        viewModel = ViewModelProvider(this)[RecipesViewModel::class.java]
        binding.viewModel = viewModel

        adapter = RecipesRecyclerAdapter()
        binding.recipesList.adapter = adapter

        viewModel.recipes.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        if (!isOnline(this.requireContext())) {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}