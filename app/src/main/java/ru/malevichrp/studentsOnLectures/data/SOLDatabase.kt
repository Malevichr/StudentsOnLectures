package ru.malevichrp.studentsOnLectures.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserCache::class], version = 3)
abstract class SOLDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
