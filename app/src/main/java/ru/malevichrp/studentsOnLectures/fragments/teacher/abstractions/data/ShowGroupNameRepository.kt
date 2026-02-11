package ru.malevichrp.studentsOnLectures.fragments.teacher.abstractions.data

import ru.malevichrp.studentsOnLectures.core.data.StringCache

interface ShowGroupNameRepository {
    fun groupName(): String
    class Base(
        private val targetGroupName: StringCache
    ) : ShowGroupNameRepository {
        override fun groupName(): String = targetGroupName.read()
    }
}