package ru.malevichrp.studentsOnLectures.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StudentDao {
    @Query("SELECT * FROM Students WHERE session_id = :id")
    suspend fun studentBySessionId(id: Long): List<StudentCache>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addStudent(student: StudentCache)
}