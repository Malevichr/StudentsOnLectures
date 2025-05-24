package ru.malevichrp.studentsOnLectures.core.presentation

import androidx.activity.OnBackPressedCallback

interface BackAction {
    fun back()
    fun addBackAction(action: OnBackPressedCallback)
}