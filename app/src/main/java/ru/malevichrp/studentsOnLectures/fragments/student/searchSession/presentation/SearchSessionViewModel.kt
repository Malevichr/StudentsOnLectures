package ru.malevichrp.studentsOnLectures.fragments.student.searchSession.presentation

import ru.malevichrp.studentsOnLectures.core.di.ClearViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.RunAsync
import ru.malevichrp.studentsOnLectures.fragments.abstrstractions.presentation.ShowFullNameViewModel
import ru.malevichrp.studentsOnLectures.fragments.student.searchSession.data.SearchSessionRepository

class SearchSessionViewModel(
    private val repository: SearchSessionRepository,
    runAsync: RunAsync,
    uiObservable: SearchSessionUiObservable,
    clearViewModel: ClearViewModel
) : MyViewModel.Async.Abstract<SearchSessionUiState>(
    runAsync,
    uiObservable,
    clearViewModel
), ShowFullNameViewModel by ShowFullNameViewModel.Base(repository) {
    fun findSession() {

    }

    fun connectToSession() {

    }

    fun init(isFirstRun: Boolean) {
        if (isFirstRun) {
            observable.postUiState(SearchSessionUiState.Initial)
        }
    }

}