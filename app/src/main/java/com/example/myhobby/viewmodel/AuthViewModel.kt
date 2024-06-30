package com.example.myhobby.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myhobby.data.RoomDatabase
import com.example.myhobby.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class AuthViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    val register = MutableLiveData<Long>()
    val login = MutableLiveData<Boolean>()
    val userLogin = MutableLiveData<User?>()
    val isEditProfile = MutableLiveData<Boolean>()
    val logout = MutableLiveData<Boolean>()
    val isUserHasLoggedIn = MutableLiveData<Boolean>()
    private var job = Job()
    private val db = RoomDatabase.buildDatabase(application)

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun register(user: User) {
        launch {
            val insert = db.roomDao().insert(user)
            register.postValue(insert)
        }
    }

    fun login(username: String, password: String) {
        launch {
            val user = db.roomDao().getUser(username)
            if (user != null && username == user.username && password == user.password) {
                user.isLoggedIn = true
                db.roomDao().insert(user)
                login.postValue(true)
            } else {
                login.postValue(false)
            }
        }
    }

    fun getUserLogin() {
        launch {
            val user = db.roomDao().getLoginUserInfo()
            if (user?.isLoggedIn == true) {
                userLogin.postValue(user)
            } else {
                userLogin.postValue(null)
            }
        }
    }

    fun editProfile(password: String, firstName: String, lastName: String) {
        launch {
            val user = userLogin.value
            if (user != null) {
                userLogin.value?.password = password
                userLogin.value?.firstName = firstName
                userLogin.value?.lastName = lastName
                val save = db.roomDao().updateUser(user)
                isEditProfile.postValue(save > 0)
            }
        }
    }

    fun logout() {
        launch {
            val user = userLogin.value
            if (user != null) {
                user.isLoggedIn = false
                val save = db.roomDao().updateUser(user)
                logout.postValue(save > 0)
            }
        }
    }

    fun isLoggedIn() {
        launch {
            val isLogin = db.roomDao().getLoginUserInfo()
            if (isLogin?.isLoggedIn == true) {
                isUserHasLoggedIn.postValue(true)
            } else {
                isUserHasLoggedIn.postValue(false)
            }
        }
    }

}