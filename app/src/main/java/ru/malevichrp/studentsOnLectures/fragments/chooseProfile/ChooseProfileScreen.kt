package ru.malevichrp.studentsOnLectures.fragments.chooseProfile

import ru.malevichrp.studentsOnLectures.core.presentation.Screen

object ChooseProfileScreen :
    Screen.ReplaceWithBackStack(ChooseProfileFragment::class.java, "ChooseProfileScreen")