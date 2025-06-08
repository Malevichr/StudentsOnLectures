package ru.malevichrp.studentsOnLectures.fragments.teacher.session.data

import android.util.Log
import ru.malevichrp.studentsOnLectures.core.data.LongCache
import ru.malevichrp.studentsOnLectures.core.data.StringCache
import ru.malevichrp.studentsOnLectures.data.StudentCache
import ru.malevichrp.studentsOnLectures.data.StudentDao
import ru.malevichrp.studentsOnLectures.fragments.abstrstractions.data.ShowFullNameRepository
import ru.malevichrp.studentsOnLectures.fragments.teacher.abstractions.data.ShowGroupNameRepository
import ru.malevichrp.studentsOnLectures.views.recycler.TextItem

interface SessionRepository : ShowFullNameRepository, ShowGroupNameRepository {
    fun timestamp(): Long
    suspend fun students(): List<StudentData>
    fun status(): SessionStatus
    suspend fun startSession(
        updateStatus: suspend (SessionStatus) -> Unit,
        addStudent: suspend (StudentData) -> Unit
    )

    fun stopSession()

    class Base(
        targetGroupName: StringCache,
        targetName: StringCache,
        private val targetSessionTimestamp: LongCache,
        private val targetSessionId: LongCache,
        private val studentDao: StudentDao,
        private val sessionService: SessionService
    ) : SessionRepository,
        ShowFullNameRepository by ShowFullNameRepository.Base(targetName),
        ShowGroupNameRepository by ShowGroupNameRepository.Base(targetGroupName) {
        override fun timestamp(): Long = targetSessionTimestamp.read()

        override suspend fun students(): List<StudentData> {
            val studentsCache: List<StudentCache> =
                studentDao.studentBySessionId(id = targetSessionId.read())
            return studentsCache.map { it.toStudentData() }
        }

        override fun status(): SessionStatus =
            sessionService.status()

        override suspend fun startSession(
            updateStatus: suspend (SessionStatus) -> Unit,
            addStudent: suspend (StudentData) -> Unit
        ) {
            sessionService.startSession(
                updateStatus = { sessionStatus ->
                    updateStatus(sessionStatus)
                },
                updateStudents = {
                    Log.d("mlvc", "${targetSessionId.read()} ${it.name}")
                    val studentCache = StudentCache(
                        sessionId = targetSessionId.read(),
                        name = it.name
                    )
                    studentDao.addStudent(
                        studentCache
                    )
                    addStudent(studentCache.toStudentData())
                }
            )
        }

        override fun stopSession() {
            sessionService.offSession()
        }
    }
}

data class StudentData(
    val id: Long,
    val name: String
) {
    fun toTextItem() = TextItem(id = id, text = name)
}
