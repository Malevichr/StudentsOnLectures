package ru.malevichrp.studentsOnLectures.fragments.abstrstractions.presentation

import ru.malevichrp.studentsOnLectures.fragments.abstrstractions.data.ShowFullNameRepository

interface ShowFullNameViewModel {
    fun fullName(): String
    class Base(
        private val repository: ShowFullNameRepository
    ) : ShowFullNameViewModel {
        override fun fullName(): String = repository.fullName()
    }
}