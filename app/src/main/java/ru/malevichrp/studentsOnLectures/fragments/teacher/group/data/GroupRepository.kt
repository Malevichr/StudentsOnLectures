package ru.malevichrp.studentsOnLectures.fragments.teacher.group.data

import ru.malevichrp.studentsOnLectures.core.data.LongCache
import ru.malevichrp.studentsOnLectures.core.data.StringCache
import ru.malevichrp.studentsOnLectures.data.SessionCache
import ru.malevichrp.studentsOnLectures.data.SessionDao
import ru.malevichrp.studentsOnLectures.fragments.abstrstractions.data.ShowFullNameRepository
import ru.malevichrp.studentsOnLectures.fragments.teacher.abstractions.data.ShowGroupNameRepository

interface GroupRepository : ShowFullNameRepository, ShowGroupNameRepository {
    suspend fun sessions(): List<SessionData>
    suspend fun createNewSession()
    suspend fun navigateToSession(sessionId: Long)

    class Base(
        targetName: StringCache,
        private val sessionDao: SessionDao,
        private val targetGroupId: LongCache,
        targetGroupName: StringCache,
        private val targetSessionId: LongCache,
        private val targetSessionTimestamp: LongCache
    ) : GroupRepository,
        ShowFullNameRepository by ShowFullNameRepository.Base(targetName),
        ShowGroupNameRepository by ShowGroupNameRepository.Base(targetGroupName) {

        override suspend fun sessions(): List<SessionData> {
            val data = sessionDao.sessionsByGroup(targetGroupId.read())
            return data.map { it.toSessionData() }
        }

        override suspend fun createNewSession() {
            val newSession = SessionCache(
                groupId = targetGroupId.read(),
                timestamp = System.currentTimeMillis()
            )
            val id = sessionDao.addSession(newSession)
            targetSessionId.save(id)
        }

        override suspend fun navigateToSession(sessionId: Long) {
            targetSessionId.save(sessionId)
            val timestamp = sessionDao.sessionById(sessionId).timestamp
            targetSessionTimestamp.save(timestamp)
        }
    }
}

data class SessionData(
    val id: Long,
    val time: Long,
)
