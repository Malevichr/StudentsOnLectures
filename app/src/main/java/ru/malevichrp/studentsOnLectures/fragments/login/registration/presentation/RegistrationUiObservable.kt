package ru.malevichrp.studentsOnLectures.fragments.login.registration.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.UiObservable

interface RegistrationUiObservable : UiObservable<RegistrationUiState> {
    class Base : UiObservable.Abstract<RegistrationUiState>(), RegistrationUiObservable
}
