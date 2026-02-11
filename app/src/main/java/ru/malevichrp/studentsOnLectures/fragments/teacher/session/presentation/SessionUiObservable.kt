package ru.malevichrp.studentsOnLectures.fragments.teacher.session.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.UiObservable

interface SessionUiObservable : UiObservable<SessionUiState> {
    class Base : UiObservable.Abstract<SessionUiState>(), SessionUiObservable
}