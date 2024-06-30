package com.example.myhobby.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myhobby.R
import com.example.myhobby.databinding.ActivityRegisterBinding
import com.example.myhobby.model.User
import com.example.myhobby.viewmodel.AuthViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val user = User("", "", "", false, "", "")
        binding.user = user


        binding.btnRegister.setOnClickListener {
            when {
                user.username.isEmpty() -> {
                    binding.etUsername.error = "Username is required"
                    binding.etUsername.requestFocus()
                }

                user.firstName.isEmpty() -> {
                    binding.etFirstName.error = "First Name is required"
                    binding.etFirstName.requestFocus()
                }

                user.lastName.isEmpty() -> {
                    binding.etLastName.error = "Last Name is required"
                    binding.etLastName.requestFocus()
                }

                user.email.isEmpty() -> {
                    binding.etEmail.error = "Email is required"
                    binding.etEmail.requestFocus()
                }

                user.password.isEmpty() -> {
                    binding.etPassword.error = "Password is required"
                    binding.etPassword.requestFocus()
                }

                else -> {
                    viewModel.register(user)
                }
            }
        }

        viewModel.register.observe(this) {
            if (it != -1L) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Register is successfully",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this@RegisterActivity, "Something went wrong!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}