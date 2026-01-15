package com.learnkmp.newsapp.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getAndroidDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val dbFile = context.getDatabasePath("news_app.db")
    return Room.databaseBuilder<AppDatabase>(
        context = context,
        name = dbFile.absolutePath,
        factory = { AppDatabaseConstructor.initialize() }
    )
}
