package ru.malevichrp.studentsOnLectures.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveUser(userCache: UserCache)

    @Query("SELECT * FROM Users WHERE full_name = :fullName AND is_teacher = :isTeacher")
    suspend fun findUser(
        fullName: String,
        isTeacher: Boolean
    ): UserCache?
}