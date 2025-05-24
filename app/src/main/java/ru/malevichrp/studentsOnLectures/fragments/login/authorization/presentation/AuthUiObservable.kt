package ru.malevichrp.studentsOnLectures.fragments.login.authorization.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.UiObservable

interface AuthUiObservable : UiObservable<AuthUiState> {
    class Base : UiObservable.Abstract<AuthUiState>(), AuthUiObservable
}