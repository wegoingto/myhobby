package com.example.myhobby.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myhobby.MIGRATION_1_2
import com.example.myhobby.model.Article
import com.example.myhobby.model.User


@Database(entities = [User::class, Article::class], version = 2)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDao

    companion object {

        private var instance: com.example.myhobby.data.RoomDatabase? = null
        private val LOCK = Any()
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                com.example.myhobby.data.RoomDatabase::class.java,
                "db"
            )
                .addMigrations(MIGRATION_1_2)
                .build()

        operator fun invoke(context: Context) {
            if (instance != null) {
                synchronized(LOCK) {
                    instance = buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}