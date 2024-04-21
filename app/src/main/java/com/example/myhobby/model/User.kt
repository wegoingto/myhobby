package com.example.myhobby.model

data class User(
    val username: String,
    val email: String,
    var password: String,
    var isLoggedIn: Boolean,
    var firstName: String,
    var lastName: String
)