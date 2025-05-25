package ru.malevichrp.studentsOnLectures.fragments.teacher.group.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.UiObservable

interface GroupUiObservable : UiObservable<GroupUiState> {
    class Base : UiObservable.Abstract<GroupUiState>(), GroupUiObservable
}