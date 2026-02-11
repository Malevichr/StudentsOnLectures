package ru.malevichrp.studentsOnLectures.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserCache::class, GroupCache::class, SessionCache::class, StudentCache::class],
    version = 9
)
abstract class SOLDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun groupDao(): GroupsDao
    abstract fun sessionDao(): SessionDao
    abstract fun studentsDao(): StudentDao
}
