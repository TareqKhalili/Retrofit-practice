package com.example.recipeapp.views

import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.recipeapp.R
import com.example.recipeapp.utlis.networkCallback
import com.example.recipeapp.utlis.networkRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connectivityManager = ContextCompat.getSystemService(
            this.applicationContext,
            ConnectivityManager::class.java
        ) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }
}