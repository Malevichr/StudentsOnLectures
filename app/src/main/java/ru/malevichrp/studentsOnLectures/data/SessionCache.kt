package ru.malevichrp.studentsOnLectures.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import ru.malevichrp.studentsOnLectures.fragments.teacher.group.data.SessionData

@Entity(
    tableName = "Sessions",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = GroupCache::class,
            parentColumns = ["id"],
            childColumns = ["group_id"],
            onDelete = CASCADE
        )
    ]
)
data class SessionCache(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long = 0,
    @ColumnInfo("group_id")
    val groupId: Long,
    @ColumnInfo("timestamp")
    val timestamp: Long
) {
    fun toSessionData() = SessionData(id, timestamp)
}
