package ru.malevichrp.studentsOnLectures.fragments.teacher.groups.data

import ru.malevichrp.studentsOnLectures.core.data.LongCache
import ru.malevichrp.studentsOnLectures.core.data.StringCache
import ru.malevichrp.studentsOnLectures.data.GroupsDao
import ru.malevichrp.studentsOnLectures.fragments.abstrstractions.data.ShowFullNameRepository
import ru.malevichrp.studentsOnLectures.views.recycler.TextItem

interface GroupsRepository : ShowFullNameRepository {
    suspend fun loadGroups(): List<GroupData>
    suspend fun changeTargetGroup(id: Long)

    class Base(
        private val targetTeacherId: LongCache,
        private val groupsDao: GroupsDao,
        private val targetGroupId: LongCache,
        private val targetGroupName: StringCache,
        targetName: StringCache
    ) : GroupsRepository,
        ShowFullNameRepository by ShowFullNameRepository.Base(targetName) {
        override suspend fun loadGroups(): List<GroupData> {
            val data = groupsDao.groupsByTeacherId(targetTeacherId.read())
            return data.map { it.toGroupData() }
        }

        override suspend fun changeTargetGroup(id: Long) {
            targetGroupId.save(id)
            targetGroupName.save(groupsDao.groupById(id).groupName)
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