package com.example.myhobby.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.myhobby.model.User
import com.google.gson.Gson


class AuthViewModel : ViewModel() {
    fun register(context: Context, username: String, email: String, password: String, firstName: String, lastName: String): Boolean {
        val user = User(username, email, password, false, firstName, lastName)
        val gson = Gson()
        val userJson = gson.toJson(user)
        val sharedPref = context.getSharedPreferences("MyHobbyApp", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("user", userJson)
            apply()
        }
        return true
    }

    fun login(context: Context, username: String, password: String): Boolean {
        val sharedPreferences = context.getSharedPreferences("MyHobbyApp", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val userJson = sharedPreferences.getString("user", null)
        userJson?.let {
            val gson = Gson()
            val user = gson.fromJson(it, User::class.java)
            if (username == user.username && password == user.password) {
                user.isLoggedIn = true
                with(editor) {
                    putString("user", gson.toJson(user))
                    apply()
                }
                return true
            }
        }
        return false
    }

    fun getUser(context: Context): User? {
        val sharedPreferences = context.getSharedPreferences("MyHobbyApp", Context.MODE_PRIVATE)
        val userJson = sharedPreferences.getString("user", null)
        userJson?.let {
            val gson = Gson()
            return gson.fromJson(it, User::class.java)
        }
        return null
    }

    fun editProfile(context: Context, password: String, firstName: String, lastName: String) {
        val sharedPreferences = context.getSharedPreferences("MyHobbyApp", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val userJson = sharedPreferences.getString("user", null)
        userJson?.let {
            val gson = Gson()
            val user = gson.fromJson(it, User::class.java)
            user.isLoggedIn = false
            user.password = password
            user.firstName = firstName
            user.lastName = lastName
            with(editor) {
                putString("user", gson.toJson(user))
                apply()
            }
        }
    }

    fun logout(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MyHobbyApp", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val userJson = sharedPreferences.getString("user", null)
        userJson?.let {
            val gson = Gson()
            val user = gson.fromJson(it, User::class.java)
            user.isLoggedIn = false
            with(editor) {
                putString("user", gson.toJson(user))
                apply()
            }
        }
    }

    fun isLoggedIn(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences("MyHobbyApp", Context.MODE_PRIVATE)
        val userJson = sharedPreferences.getString("user", null)
        userJson?.let {
            val gson = Gson()
            val user = gson.fromJson(it, User::class.java)
            if (user.isLoggedIn) {
                return true
            }
        }
        return false
    }

}