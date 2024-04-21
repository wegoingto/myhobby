package com.example.myhobby.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val shortContent: String,
    val writer: String,
    val content: String
) : Parcelable