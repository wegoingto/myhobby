package com.example.myhobby

import com.example.myhobby.model.Article

interface ArticleListener {
    fun onArticleClicked(article: Article)
}