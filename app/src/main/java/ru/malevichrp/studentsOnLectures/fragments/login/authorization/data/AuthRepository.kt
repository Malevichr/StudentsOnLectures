package ru.malevichrp.studentsOnLectures.fragments.login.authorization.data

import ru.malevichrp.studentsOnLectures.core.data.BooleanCache
import ru.malevichrp.studentsOnLectures.core.data.LongCache
import ru.malevichrp.studentsOnLectures.core.data.StringCache
import ru.malevichrp.studentsOnLectures.data.UserDao

interface AuthRepository {
    suspend fun login(authData: AuthData): AuthResponse
    fun isTeacher(): Boolean

    class Base(
        private val isTeacher: BooleanCache,
        private val userDao: UserDao,
        private val targetTeacherId: LongCache,
        private val targetName: StringCache
    ) : AuthRepository {
        override suspend fun login(authData: AuthData): AuthResponse {
            val user = userDao.findUser(
                authData.fullName,
                isTeacher = isTeacher.read()
            ) ?: throw IllegalStateException("User isn't exist")
            targetTeacherId.save(user.id)
            targetName.save(authData.fullName)
            return AuthResponse(isTeacher.read())
        }

        override fun isTeacher(): Boolean = isTeacher.read()
    }
}

data class AuthData(
    val fullName: String
)

data class AuthResponse(
    val isTeacher: Boolean
)