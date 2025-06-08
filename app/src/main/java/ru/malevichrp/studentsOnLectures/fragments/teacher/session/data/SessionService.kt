package ru.malevichrp.studentsOnLectures.fragments.teacher.session.data

import kotlinx.coroutines.delay

interface SessionService {
    fun status(): SessionStatus
    suspend fun startSession(
        updateStatus: suspend (SessionStatus) -> Unit,
        updateStudents: suspend (StudentInService) -> Unit
    )

    fun offSession()

    class Fake : SessionService {
        @Volatile
        private var update: ( suspend (StudentInService) -> Unit)? = null
        private var status: SessionStatus = SessionStatus.IsOff
        override fun status(): SessionStatus {
            return status
        }

        private var counter = 0

        override suspend fun startSession(
            updateStatus: suspend (SessionStatus) -> Unit,
            updateStudents: suspend (StudentInService) -> Unit
        ) {
            if (status != SessionStatus.IsOff) return

            update = updateStudents
            status = SessionStatus.IsLoading
            updateStatus(status)

            delay(2000)

            status = SessionStatus.IsOn
            updateStatus(status)

            try {
                while (update != null) {
                    delay(1000)
                    update?.invoke(StudentInService((counter++).toString()))
                }
            } finally {
                status = SessionStatus.IsOff
                update = null
                updateStatus(status)
            }
        }

        override fun offSession() {
            this.update = null
        }
    }
}

data class StudentInService(
    val name: String
)
