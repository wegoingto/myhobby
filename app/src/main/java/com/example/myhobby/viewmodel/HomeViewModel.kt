package com.example.myhobby.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.myhobby.DummyData
import com.example.myhobby.model.Article
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class HomeViewModel : ViewModel() {

    fun getAllNewsArticle(): List<Article> {
        val gson = Gson()
        val type = object : TypeToken<List<Article>>() {}.type
        return gson.fromJson(DummyData.dummyNews, type)
    }

    fun saveArticle(context: Context, article: Article): Boolean {
        val savedArticle = getAllHistoryReadingArticle(context).toMutableList()
        savedArticle.add(article)
        val gson = Gson()
        val sharedPref = context.getSharedPreferences("MyHobbyApp", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("historyReading", gson.toJson(savedArticle))
            apply()
        }
        return true
    }

    fun getAllHistoryReadingArticle(context: Context): List<Article> {
        val type = object : TypeToken<List<Article>>() {}.type
        val sharedPref = context.getSharedPreferences("MyHobbyApp", Context.MODE_PRIVATE)
        val historyReadingJson = sharedPref.getString("historyReading", null)
        return if (historyReadingJson != null) {
            val gson = Gson()
            gson.fromJson(historyReadingJson, type)
        } else {
            emptyList()
        }
    }

}