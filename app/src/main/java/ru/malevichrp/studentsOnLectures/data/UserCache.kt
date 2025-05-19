package ru.malevichrp.studentsOnLectures.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserCache(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,
    @ColumnInfo("full_name")
    val fullName: String,
    @ColumnInfo("is_teacher")
    val isTeacher: Boolean
)