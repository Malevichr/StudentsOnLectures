package ru.malevichrp.studentsOnLectures.fragments.teacher.addGroup.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.UiObservable

interface AddGroupUiObservable : UiObservable<AddGroupUiState> {
    class Base : UiObservable.Abstract<AddGroupUiState>(), AddGroupUiObservable
}