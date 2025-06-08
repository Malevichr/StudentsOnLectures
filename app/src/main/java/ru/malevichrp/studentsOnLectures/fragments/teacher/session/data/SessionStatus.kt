package ru.malevichrp.studentsOnLectures.fragments.teacher.session.data

interface SessionStatus {
    object IsOn : SessionStatus
    object IsOff : SessionStatus
    object IsLoading : SessionStatus
}