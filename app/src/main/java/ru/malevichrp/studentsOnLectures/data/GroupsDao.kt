package ru.malevichrp.studentsOnLectures.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GroupsDao {
    @Query("SELECT * FROM Groups WHERE teacher_id=:id")
    suspend fun groupsByTeacherId(id: Long): List<GroupCache>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addGroup(groupCache: GroupCache)
}