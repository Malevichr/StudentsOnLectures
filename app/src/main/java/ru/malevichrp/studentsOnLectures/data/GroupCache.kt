package ru.malevichrp.studentsOnLectures.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.malevichrp.studentsOnLectures.fragments.teacher.groups.data.GroupData

@Entity(tableName = "Groups")
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
