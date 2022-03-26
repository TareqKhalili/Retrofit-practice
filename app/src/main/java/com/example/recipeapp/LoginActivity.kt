package com.example.recipeapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.recipeapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_login
        )

        val sharedPreferences = this.getPreferences(Context.MODE_PRIVATE)


        val username = binding.usernameField
        val password = binding.passwordField


        binding.loginButton.setOnClickListener {
            saveUserInfo(
                sharedPreferences,
                username.text.toString(),
                password.text.toString()
            )

            Intent(this, MainActivity::class.java).also {
                it.putExtra(getString(R.string.saved_username_key), username.text.toString())
                startActivity(it)
                finish()
            }

        }

        binding.usernameField.setText(
            sharedPreferences.getString(
                getString(R.string.saved_username_key),
                ""
            )
        )

        binding.passwordField.setText(
            sharedPreferences.getString(
                getString(R.string.saved_password_key),
                ""
            )
        )
    }

    private fun saveUserInfo(
        sharedPreferences: SharedPreferences,
        username: String,
        password: String
    ) {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            with(sharedPreferences.edit()) {
                putString(getString(R.string.saved_username_key), username.trim())
                putString(getString(R.string.saved_password_key), password.trim())
                apply()
            }
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
        }
    }
}