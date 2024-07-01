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
import com.example.myhobby.view.MainActivity
import com.example.myhobby.R
import com.example.myhobby.databinding.ActivityLoginBinding
import com.example.myhobby.model.User
import com.example.myhobby.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        viewModel.isLoggedIn()
        viewModel.isUserHasLoggedIn.observe(this) { //lakukan cek
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                setContentView(binding.root)
                ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    v.setPadding(
                        systemBars.left,
                        systemBars.top,
                        systemBars.right,
                        systemBars.bottom
                    )
                    insets
                }
            }
        }

        val user = User("", "", "", false, "", "")
        binding.user = user

        binding.btnLogin.setOnClickListener { //handle proses login
            when {
                user.username.isEmpty() -> {
                    binding.etUsername.error = "Username is required"
                    binding.etUsername.requestFocus()
                }

                user.password.isEmpty() -> {
                    binding.etPassword.error = "Password is required"
                    binding.etPassword.requestFocus()
                }

                else -> {
                    viewModel.login(user.username, user.password)
                }
            }
        }

        viewModel.login.observe(this) {//observe proses login
            if (it) {
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

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
}