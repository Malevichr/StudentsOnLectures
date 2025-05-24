package ru.malevichrp.studentsOnLectures.fragments.login.registration.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.Screen

object RegistrationScreen :
    Screen.ReplaceWithBackStack(RegistrationFragment::class.java, "RegistrationScreen")