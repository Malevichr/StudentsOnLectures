package ru.malevichrp.studentsOnLectures.fragments.teacher.abstractions.presentation

import ru.malevichrp.studentsOnLectures.fragments.teacher.abstractions.data.ShowGroupNameRepository

interface ShowGroupNameViewModel {
    fun groupName(): String
    class Base(private val repository: ShowGroupNameRepository) : ShowGroupNameViewModel {
        override fun groupName(): String = repository.groupName()
    }
}