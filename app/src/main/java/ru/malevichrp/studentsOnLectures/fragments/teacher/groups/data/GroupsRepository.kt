package ru.malevichrp.studentsOnLectures.fragments.teacher.groups.data

import ru.malevichrp.studentsOnLectures.core.data.LongCache
import ru.malevichrp.studentsOnLectures.data.GroupsDao
import ru.malevichrp.studentsOnLectures.views.recycler.TextItem

interface GroupsRepository {
    suspend fun loadGroups(): List<GroupData>
    fun changeTargetGroup(id: Long)
    class Base(
        private val targetTeacherId: LongCache,
        private val groupsDao: GroupsDao,
        private val targetGroupId: LongCache
    ) : GroupsRepository {
        override suspend fun loadGroups(): List<GroupData> {
            val data = groupsDao.groupsByTeacherId(targetTeacherId.read())
            return data.map { it.toGroupData() }
        }

        override fun changeTargetGroup(id: Long) {
            targetGroupId.save(id)
        }
    }
}

data class GroupData(
    val id: Long,
    val name: String
) {
    fun toTextItem() = TextItem(
        text = name,
        id = id
    )
}