package ru.malevichrp.studentsOnLectures.fragments.login.chooseProfile.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.Screen

object ChooseProfileScreen :
    Screen.ReplaceWithBackStack(ChooseProfileFragment::class.java, "ChooseProfileScreen")