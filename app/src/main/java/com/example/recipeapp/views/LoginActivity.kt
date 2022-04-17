package com.example.recipeapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ActivityLoginBinding
import com.example.recipeapp.models.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_login
        )

        val username = binding.usernameField
        val password = binding.passwordField

        binding.loginButton.setOnClickListener {
            try {
                viewModel.saveUserInfo(
                    username.text.toString(),
                    password.text.toString()
                )


                Intent(this, MainActivity::class.java).also {
                    it.putExtra(getString(R.string.saved_username_key), username.text.toString())
                    startActivity(it)
                    finish()
                }
            } catch (error: IllegalArgumentException) {
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.usernameField.setText(
            viewModel.getUsername()
        )

        binding.passwordField.setText(
            viewModel.getPassword()
        )
    }
}