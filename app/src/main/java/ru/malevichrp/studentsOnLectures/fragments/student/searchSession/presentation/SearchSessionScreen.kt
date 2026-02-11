package ru.malevichrp.studentsOnLectures.fragments.student.searchSession.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.Screen

object SearchSessionScreen :
    Screen.ReplaceWithBackStack(SearchSessionFragment::class.java, "SearchSessionScreen")
