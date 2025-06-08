package ru.malevichrp.studentsOnLectures.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import ru.malevichrp.studentsOnLectures.fragments.teacher.session.data.StudentData

@Entity(
    tableName = "Students",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = SessionCache::class,
            parentColumns = ["id"],
            childColumns = ["session_id"],
            onDelete = CASCADE
        )
    ]
)
data class StudentCache(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long = 0,
    @ColumnInfo("session_id")
    val sessionId: Long,
    @ColumnInfo("name")
    val name: String
) {
    fun toStudentData() = StudentData(id, name)
}
