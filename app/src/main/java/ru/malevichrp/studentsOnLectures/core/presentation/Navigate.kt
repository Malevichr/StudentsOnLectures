package ru.malevichrp.studentsOnLectures.core.presentation

import ru.malevichrp.studentsOnLectures.fragments.chooseProfile.ChooseProfileScreen
import ru.malevichrp.studentsOnLectures.fragments.chooseProfile.NavigateToChooseProfile

interface Navigate : NavigateToChooseProfile {
    fun navigate(screen: Screen)
    override fun navigateToChooseProfile() {
        navigate(ChooseProfileScreen)
    }
}