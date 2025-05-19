package ru.malevichrp.studentsOnLectures.core.di

import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel

interface ClearViewModel {
    fun clear(viewModelClass: Class<out MyViewModel>)
}