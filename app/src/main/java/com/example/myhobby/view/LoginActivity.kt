package com.example.myhobby.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myhobby.R
import com.example.myhobby.databinding.ActivityLoginBinding
import com.example.myhobby.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        if (viewModel.isLoggedIn(this@LoginActivity)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            setContentView(binding.root)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            when {
                username.isEmpty() -> {
                    binding.etUsername.error = "Username is required"
                    binding.etUsername.requestFocus()
                }

                password.isEmpty() -> {
                    binding.etPassword.error = "Password is required"
                    binding.etPassword.requestFocus()
                }

                else -> {
                    if (viewModel.login(this@LoginActivity, username, password)) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Username/password is wrong!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
}