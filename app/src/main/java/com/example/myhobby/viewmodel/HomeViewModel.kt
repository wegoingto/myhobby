package com.example.myhobby.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myhobby.DummyData
import com.example.myhobby.data.RoomDatabase
import com.example.myhobby.model.Article
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class HomeViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    private val db = RoomDatabase.buildDatabase(getApplication())
    val historyReadingArticle = MutableLiveData<List<Article>>()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun getAllNewsArticle(): List<Article> {
        val gson = Gson()
        val type = object : TypeToken<List<Article>>() {}.type
        return gson.fromJson(DummyData.dummyNews, type)
    }

    fun saveArticle(article: Article) {
        launch {
            db.roomDao().saveHistoryReadingArticle(article)
        }
    }

    fun getAllHistoryReadingArticle() {
        launch {
            val data = db.roomDao().getAllHistoryReadingArticle()
            historyReadingArticle.postValue(data)
        }
    }
}