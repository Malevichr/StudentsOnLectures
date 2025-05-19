package ru.malevichrp.studentsOnLectures.core.presentation

import ru.malevichrp.studentsOnLectures.fragments.chooseProfile.ChooseProfileScreen
import ru.malevichrp.studentsOnLectures.fragments.chooseProfile.NavigateToChooseProfile
import ru.malevichrp.studentsOnLectures.fragments.registration.NavigateToRegistration
import ru.malevichrp.studentsOnLectures.fragments.registration.RegistrationScreen

interface Navigate : NavigateToChooseProfile, NavigateToRegistration {
    fun navigate(screen: Screen)
    override fun navigateToChooseProfile() {
        navigate(ChooseProfileScreen)
    }

    override fun navigateToRegistration() {
        navigate(RegistrationScreen)
    }
}