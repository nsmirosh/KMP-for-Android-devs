package com.learnkmp.newsapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

const val DATABASE_NAME = "news_app.db"

fun getAndroidDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val dbFile = context.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<AppDatabase>(
        context = context,
        name = dbFile.absolutePath,
        factory = { AppDatabaseConstructor.initialize() }
    )
}
