package ru.malevichrp.studentsOnLectures.fragments.registration

import ru.malevichrp.studentsOnLectures.data.UserCache
import ru.malevichrp.studentsOnLectures.data.UserDao

interface RegistrationRepository {
    suspend fun register(registrationData: RegistrationData)

    class Base(
        private val userDao: UserDao
    ) : RegistrationRepository {
        override suspend fun register(registrationData: RegistrationData) {
            userDao.saveUser(
                UserCache(
                    fullName = registrationData.fullName,
                    isTeacher = registrationData.isTeacher
                )
            )
        }
    }
}
