package ru.malevichrp.studentsOnLectures.fragments.teacher.groups.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.UiObservable

interface GroupsUiObservable : UiObservable<GroupsUiState> {
    class Base : UiObservable.Abstract<GroupsUiState>(), GroupsUiObservable
}