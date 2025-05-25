package ru.malevichrp.studentsOnLectures.core.presentation

import ru.malevichrp.studentsOnLectures.fragments.login.authorization.presentation.AuthScreen
import ru.malevichrp.studentsOnLectures.fragments.login.authorization.presentation.NavigateToAuth
import ru.malevichrp.studentsOnLectures.fragments.login.chooseProfile.presentation.ChooseProfileScreen
import ru.malevichrp.studentsOnLectures.fragments.login.chooseProfile.presentation.NavigateToChooseProfile
import ru.malevichrp.studentsOnLectures.fragments.login.registration.presentation.NavigateToRegistration
import ru.malevichrp.studentsOnLectures.fragments.login.registration.presentation.RegistrationScreen
import ru.malevichrp.studentsOnLectures.fragments.student.searchSession.presentation.NavigateToStudentSearch
import ru.malevichrp.studentsOnLectures.fragments.teacher.addGroup.presentation.AddGroupScreen
import ru.malevichrp.studentsOnLectures.fragments.teacher.addGroup.presentation.NavigateToAddGroup
import ru.malevichrp.studentsOnLectures.fragments.teacher.group.presentation.GroupScreen
import ru.malevichrp.studentsOnLectures.fragments.teacher.group.presentation.NavigateToGroup
import ru.malevichrp.studentsOnLectures.fragments.teacher.groups.presentation.GroupsScreen
import ru.malevichrp.studentsOnLectures.fragments.teacher.groups.presentation.NavigateToTeacherGroups
import ru.malevichrp.studentsOnLectures.fragments.teacher.session.presentation.NavigateToSession

interface Navigate : NavigateToChooseProfile, NavigateToRegistration,
    NavigateToAuth, NavigateToStudentSearch, NavigateToTeacherGroups,
    NavigateToAddGroup, NavigateToGroup, NavigateToSession {
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
        navigate(GroupsScreen)
    }

    override fun navigateToStudentSearch() {
        TODO("Not yet implemented")
    }

    override fun navigateToAddGroup() {
        navigate(AddGroupScreen)
    }

    override fun navigateToGroup() {
        navigate(GroupScreen)
    }

    override fun navigateToSession() {
        TODO("Not yet implemented")
    }
}