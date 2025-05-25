package ru.malevichrp.studentsOnLectures.fragments.teacher.group.data

import ru.malevichrp.studentsOnLectures.core.data.LongCache
import ru.malevichrp.studentsOnLectures.core.data.StringCache
import ru.malevichrp.studentsOnLectures.data.SessionDao
import ru.malevichrp.studentsOnLectures.fragments.abstrstractions.data.ShowFullNameRepository
import ru.malevichrp.studentsOnLectures.fragments.teacher.abstractions.data.ShowGroupNameRepository

interface GroupRepository : ShowFullNameRepository, ShowGroupNameRepository {
    suspend fun sessions(): List<SessionData>

    class Base(
        targetName: StringCache,
        private val sessionDao: SessionDao,
        private val targetGroupId: LongCache,
        targetGroupName: StringCache
    ) : GroupRepository,
        ShowFullNameRepository by ShowFullNameRepository.Base(targetName),
        ShowGroupNameRepository by ShowGroupNameRepository.Base(targetGroupName) {

        override suspend fun sessions(): List<SessionData> {
            val data = sessionDao.sessionsByGroup(targetGroupId.read())
            return data.map { it.toSessionData() }
        }
    }
}

data class SessionData(
    val id: Long,
    val time: Long,
)
