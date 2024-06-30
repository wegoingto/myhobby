package com.example.myhobby.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Article(
    @androidx.room.PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,
    @ColumnInfo(name = "shortContent")
    val shortContent: String,
    @ColumnInfo(name = "writer")
    val writer: String,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "isRead")
    val isRead: Boolean = false
) : Parcelable