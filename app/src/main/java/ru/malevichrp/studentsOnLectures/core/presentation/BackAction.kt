package ru.malevichrp.studentsOnLectures.core.presentation

import androidx.lifecycle.LifecycleOwner

interface BackAction {
    fun back()
    fun addCallback(lifecycleOwner: LifecycleOwner, action: () -> Unit)
}