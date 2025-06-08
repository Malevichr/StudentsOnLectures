package ru.malevichrp.studentsOnLectures.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SessionDao {
    @Query("SELECT * FROM Sessions WHERE group_id = :id")
    suspend fun sessionsByGroup(id: Long): List<SessionCache>
    @Query("SELECT * FROM Sessions WHERE id = :id")
    suspend fun sessionById(id: Long): SessionCache

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSession(sessionCache: SessionCache) : Long
}