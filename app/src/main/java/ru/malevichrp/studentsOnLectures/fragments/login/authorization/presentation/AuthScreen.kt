package ru.malevichrp.studentsOnLectures.fragments.login.authorization.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.Screen

object AuthScreen : Screen.ReplaceWithBackStack(AuthFragment::class.java, "AuthScreen")