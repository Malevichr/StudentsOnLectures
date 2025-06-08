package ru.malevichrp.studentsOnLectures.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import ru.malevichrp.studentsOnLectures.fragments.teacher.groups.data.GroupData

@Entity(
    tableName = "Groups",
    indices = [Index(
        value =
        ["teacher_id", "group_name"], unique = true
    )],
    foreignKeys = [
        ForeignKey(
            entity = UserCache::class,
            parentColumns = ["id"],
            childColumns = ["teacher_id"],
            onDelete = CASCADE
        )
    ]
)
data class GroupCache(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long = 0,
    @ColumnInfo("teacher_id")
    val teacherId: Long,
    @ColumnInfo("group_name")
    val groupName: String
) {
    fun toGroupData() = GroupData(
        id = id,
        name = groupName
    )
}
