package ru.malevichrp.studentsOnLectures.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Users",
    indices = [Index(
        value =
        ["full_name", "is_teacher"], unique = true
    )]
)
data class UserCache(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long = 0,
    @ColumnInfo("full_name")
    val fullName: String,
    @ColumnInfo("is_teacher")
    val isTeacher: Boolean
)