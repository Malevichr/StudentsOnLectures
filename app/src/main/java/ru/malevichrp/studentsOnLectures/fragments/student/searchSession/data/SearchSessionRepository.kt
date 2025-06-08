package ru.malevichrp.studentsOnLectures.fragments.student.searchSession.data

import ru.malevichrp.studentsOnLectures.core.data.StringCache
import ru.malevichrp.studentsOnLectures.fragments.abstrstractions.data.ShowFullNameRepository

interface SearchSessionRepository : ShowFullNameRepository {
    class Fake(
        private val targetName: StringCache
    ) : SearchSessionRepository, ShowFullNameRepository by ShowFullNameRepository.Base(targetName)
}