package ru.malevichrp.studentsOnLectures.fragments.teacher.addGroup.data

import ru.malevichrp.studentsOnLectures.core.data.LongCache
import ru.malevichrp.studentsOnLectures.data.GroupCache
import ru.malevichrp.studentsOnLectures.data.GroupsDao

interface AddGroupRepository {
    suspend fun addGroup(name: String)
    class Base(
        private val targetTeacherId: LongCache,
        private val groupsDao: GroupsDao
    ) : AddGroupRepository {
        override suspend fun addGroup(name: String) {
            if (name.isEmpty())
                throw IllegalStateException("Group name is empty")
            try{
                groupsDao.addGroup(
                    GroupCache(
                        teacherId = targetTeacherId.read(),
                        groupName = name
                    )
                )
            } catch (_:Exception){
                throw IllegalStateException("This group is exist")
            }
        }
    }
}
