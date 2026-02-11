package ru.malevichrp.studentsOnLectures.fragments.student.searchSession.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.UiObservable

interface SearchSessionUiObservable : UiObservable<SearchSessionUiState> {
    class Base : UiObservable.Abstract<SearchSessionUiState>(), SearchSessionUiObservable
}