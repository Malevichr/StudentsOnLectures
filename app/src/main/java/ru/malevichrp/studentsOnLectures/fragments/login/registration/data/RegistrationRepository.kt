package ru.malevichrp.studentsOnLectures.fragments.login.registration.data

import ru.malevichrp.studentsOnLectures.data.UserCache
import ru.malevichrp.studentsOnLectures.data.UserDao

interface RegistrationRepository {
    suspend fun register(registrationData: RegistrationData)

    class Base(
        private val userDao: UserDao
    ) : RegistrationRepository {
        override suspend fun register(registrationData: RegistrationData) {
            if (registrationData.fullName.isEmpty())
                throw IllegalStateException("Empty name")
            try {
                userDao.saveUser(
                    UserCache(
                        fullName = registrationData.fullName,
                        isTeacher = registrationData.isTeacher
                    )
                )
            } catch (e: Exception) {
                throw IllegalStateException("User is exist")
            }
        }
    }
}
