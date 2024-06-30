package com.example.myhobby.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myhobby.model.Article
import com.example.myhobby.model.User


@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Query("SELECT * FROM user WHERE username= :username")
    fun getUser(username: String): User?

    @Query("SELECT * FROM user WHERE is_logged_in = 1")
    fun getLoginUserInfo(): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHistoryReadingArticle(article: Article): Long

    @Query("SELECT * FROM article")
    fun getAllHistoryReadingArticle(): List<Article>

}