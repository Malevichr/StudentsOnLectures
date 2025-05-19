package ru.malevichrp.studentsOnLectures.data

import android.content.Context
import androidx.room.Room

interface CacheModule {
    fun userDao(): UserDao
    class Base(applicationContext: Context) : CacheModule {
        private val database by lazy {
            Room.databaseBuilder(
                applicationContext,
                klass = SOLDatabase::class.java,
                name = "sol-database"
            )
                .fallbackToDestructiveMigration(true)
                .build()
        }

        override fun userDao(): UserDao {
            return database.userDao()
        }
    }
}