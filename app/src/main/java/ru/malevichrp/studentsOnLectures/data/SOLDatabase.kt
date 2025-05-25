package ru.malevichrp.studentsOnLectures.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserCache::class, GroupCache::class, SessionCache::class], version = 8)
abstract class SOLDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun groupDao(): GroupsDao
    abstract fun sessionDao(): SessionDao
}
