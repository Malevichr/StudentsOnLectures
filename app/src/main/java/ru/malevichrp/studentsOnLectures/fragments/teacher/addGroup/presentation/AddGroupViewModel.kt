package ru.malevichrp.studentsOnLectures.fragments.teacher.addGroup.presentation

import ru.malevichrp.studentsOnLectures.core.di.ClearViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.RunAsync
import ru.malevichrp.studentsOnLectures.fragments.abstrstractions.presentation.ShowFullNameViewModel
import ru.malevichrp.studentsOnLectures.fragments.teacher.addGroup.data.AddGroupRepository

class AddGroupViewModel(
    private val repository: AddGroupRepository,
    runAsync: RunAsync,
    uiObservable: AddGroupUiObservable,
    clearViewModel: ClearViewModel
) : MyViewModel.Async.Abstract<AddGroupUiState>(
    runAsync,
    uiObservable,
    clearViewModel
), ShowFullNameViewModel by ShowFullNameViewModel.Base(repository) {

    fun addGroup(groupName: String) {
        handleAsync {
            try {
                repository.addGroup(groupName)
                AddGroupUiState.Success
            } catch (e: Exception) {
                AddGroupUiState.Error(e.message.toString())
            }
        }
    }
}