package ru.malevichrp.studentsOnLectures.core.presentation

import ru.malevichrp.studentsOnLectures.fragments.login.authorization.presentation.AuthScreen
import ru.malevichrp.studentsOnLectures.fragments.login.authorization.presentation.NavigateToAuth
import ru.malevichrp.studentsOnLectures.fragments.login.chooseProfile.presentation.ChooseProfileScreen
import ru.malevichrp.studentsOnLectures.fragments.login.chooseProfile.presentation.NavigateToChooseProfile
import ru.malevichrp.studentsOnLectures.fragments.login.registration.presentation.NavigateToRegistration
import ru.malevichrp.studentsOnLectures.fragments.login.registration.presentation.RegistrationScreen
import ru.malevichrp.studentsOnLectures.fragments.student.searchSession.presentation.NavigateToStudentSearch
import ru.malevichrp.studentsOnLectures.fragments.teacher.NavigateToTeacherGroups

interface Navigate : NavigateToChooseProfile, NavigateToRegistration,
    NavigateToAuth, NavigateToStudentSearch, NavigateToTeacherGroups {
    fun navigate(screen: Screen)
    override fun navigateToChooseProfile() {
        navigate(ChooseProfileScreen)
    }

    override fun navigateToRegistration() {
        navigate(RegistrationScreen)
    }

    override fun navigateToAuth() {
        navigate(AuthScreen)
    }

    override fun navigateToTeacherGroups() {
        TODO("Not yet implemented")
    }

    override fun navigateToStudentSearch() {
        TODO("Not yet implemented")
    }
}