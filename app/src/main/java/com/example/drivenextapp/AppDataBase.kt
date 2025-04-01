package com.example.drivenextapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    version = 1,
    entities = [
        UserDbEntity::class
    ]
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getDao(): UserDao
    companion object {
        fun getDb(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "app.db"
            ).build()
        }

        fun resetDb(context: Context) {
            synchronized(this) {
                val dbFile = context.getDatabasePath("app.db")
                dbFile.delete()
            }
        }
    }
}