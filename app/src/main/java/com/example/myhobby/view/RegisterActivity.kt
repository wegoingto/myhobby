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
import com.example.myhobby.databinding.ActivityRegisterBinding
import com.example.myhobby.viewmodel.AuthViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()
            when {
                username.isEmpty() -> {
                    binding.etUsername.error = "Username is required"
                    binding.etUsername.requestFocus()
                }

                firstName.isEmpty() -> {
                    binding.etFirstName.error = "First Name is required"
                    binding.etFirstName.requestFocus()
                }

                lastName.isEmpty() -> {
                    binding.etLastName.error = "Last Name is required"
                    binding.etLastName.requestFocus()
                }

                email.isEmpty() -> {
                    binding.etEmail.error = "Email is required"
                    binding.etEmail.requestFocus()
                }

                password.isEmpty() -> {
                    binding.etPassword.error = "Password is required"
                    binding.etPassword.requestFocus()
                }

                confirmPassword.isEmpty() -> {
                    binding.etConfirmPassword.error = "Confirm Password is required"
                    binding.etConfirmPassword.requestFocus()
                }

                password != confirmPassword -> {
                    binding.etConfirmPassword.error =
                        "Password and Confirm Password must be the same"
                    binding.etConfirmPassword.requestFocus()
                }

                else -> {
                    if (viewModel.register(
                            this@RegisterActivity,
                            username,
                            email,
                            password,
                            firstName,
                            lastName
                        )
                    ) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Register is successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                }
            }
        }

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}