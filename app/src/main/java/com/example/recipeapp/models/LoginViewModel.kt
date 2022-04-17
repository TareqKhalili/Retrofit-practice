package com.example.recipeapp.models

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.recipeapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun getUsername(): String? {
        return sharedPreferences.getString(
            R.string.saved_username_key.toString(), ""
        )
    }


    fun getPassword(): String? {
        return sharedPreferences.getString(
            R.string.saved_password_key.toString(), ""
        )
    }


    fun saveUserInfo(
        username: String,
        password: String
    ) {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            with(sharedPreferences.edit()) {
                putString(R.string.saved_username_key.toString(), username.trim())
                putString(R.string.saved_password_key.toString(), password.trim())
                apply()
            }
        } else {
            throw IllegalArgumentException("Invalid username or password")
        }
    }
}