package com.example.recipeapp

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.recipeapp.utlis.networkCallback
import com.example.recipeapp.utlis.networkRequest

class MainActivity : AppCompatActivity() {
    companion object {
        var USERNAME = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        USERNAME = intent.getStringExtra(getString(R.string.saved_username_key)).toString()

        val connectivityManager = ContextCompat.getSystemService(
            this.applicationContext,
            ConnectivityManager::class.java
        ) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }
}