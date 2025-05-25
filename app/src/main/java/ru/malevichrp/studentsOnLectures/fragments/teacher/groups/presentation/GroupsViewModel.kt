package ru.malevichrp.studentsOnLectures.fragments.teacher.groups.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.malevichrp.studentsOnLectures.core.di.ClearViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.RunAsync
import ru.malevichrp.studentsOnLectures.fragments.abstrstractions.presentation.ShowFullNameViewModel
import ru.malevichrp.studentsOnLectures.fragments.teacher.groups.data.GroupsRepository

class GroupsViewModel(
    private val repository: GroupsRepository,
    uiObservable: GroupsUiObservable,
    runAsync: RunAsync,
    clearViewModel: ClearViewModel
) : MyViewModel.Async.Abstract<GroupsUiState>(
    runAsync,
    uiObservable,
    clearViewModel
), ShowFullNameViewModel by ShowFullNameViewModel.Base(repository) {
    fun navigateToGroup(groupId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeTargetGroup(id = groupId)
        }
    }

    fun groups() {
        handleAsync {
            val themes = repository.loadGroups()
            GroupsUiState.Base(
                themes.map { it.toTextItem() }
            )
        }
    }
}
