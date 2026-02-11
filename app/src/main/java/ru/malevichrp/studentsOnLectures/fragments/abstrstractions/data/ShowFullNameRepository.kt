package ru.malevichrp.studentsOnLectures.fragments.abstrstractions.data

import ru.malevichrp.studentsOnLectures.core.data.StringCache

interface ShowFullNameRepository {
    fun fullName(): String
    class Base(
        private val targetName: StringCache
    ) : ShowFullNameRepository {
        override fun fullName() = targetName.read()
    }
}