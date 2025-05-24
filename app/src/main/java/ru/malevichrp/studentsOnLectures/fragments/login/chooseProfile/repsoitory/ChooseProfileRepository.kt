package ru.malevichrp.studentsOnLectures.fragments.login.chooseProfile.repsoitory

import ru.malevichrp.studentsOnLectures.core.data.BooleanCache

interface ChooseProfileRepository {
    fun changeStudentProfile()
    fun changeTeacherProfile()
    class Base(
        private val isTeacherProfile: BooleanCache
    ) : ChooseProfileRepository {
        override fun changeStudentProfile() {
            isTeacherProfile.save(false)
        }

        override fun changeTeacherProfile() {
            isTeacherProfile.save(true)
        }
    }
}
