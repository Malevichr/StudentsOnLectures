package ru.malevichrp.studentsOnLectures.fragments.teacher.groups.presentation

import ru.malevichrp.studentsOnLectures.core.di.ClearViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.RunAsync
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
) {
    fun navigateToGroup(groupId: Long) {
        repository.changeTargetGroup(id = groupId)
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