package ru.malevichrp.studentsOnLectures.fragments.login.authorization.data

import ru.malevichrp.studentsOnLectures.core.data.BooleanCache
import ru.malevichrp.studentsOnLectures.data.UserDao

interface AuthRepository {
    suspend fun login(authData: AuthData): AuthResponse
    fun isTeacher(): Boolean

    class Base(
        private val isTeacher: BooleanCache,
        private val userDao: UserDao
    ) : AuthRepository {
        override suspend fun login(authData: AuthData): AuthResponse {
            userDao.findUser(
                authData.fullName,
                isTeacher = isTeacher.read()
            ) ?: throw IllegalStateException("Wrong data")
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