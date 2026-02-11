package ru.malevichrp.studentsOnLectures.fragments.teacher.group.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.malevichrp.studentsOnLectures.core.di.ClearViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.RunAsync
import ru.malevichrp.studentsOnLectures.fragments.abstrstractions.presentation.ShowFullNameViewModel
import ru.malevichrp.studentsOnLectures.fragments.teacher.abstractions.presentation.ShowGroupNameViewModel
import ru.malevichrp.studentsOnLectures.fragments.teacher.group.data.GroupRepository
import ru.malevichrp.studentsOnLectures.fragments.teacher.group.data.SessionData
import ru.malevichrp.studentsOnLectures.views.recycler.TextItem

class GroupViewModel(
    private val repository: GroupRepository,
    runAsync: RunAsync,
    uiObservable: GroupUiObservable,
    clearViewModel: ClearViewModel,
    private val timeFormater: TimeFormater = TimeFormater.Base()
) : MyViewModel.Async.Abstract<GroupUiState>(
    runAsync,
    uiObservable,
    clearViewModel
), ShowFullNameViewModel by ShowFullNameViewModel.Base(repository),
    ShowGroupNameViewModel by ShowGroupNameViewModel.Base(repository) {
    fun sessions() {
        handleAsync {
            val data: List<SessionData> = repository.sessions()
            GroupUiState.Base(data.map {
                TextItem(
                    id = it.id,
                    text = timeFormater.format(it.time)
                )
            })
        }
    }

    fun createNewSession() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createNewSession()
        }
    }

    fun navigateToSession(sessionId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.navigateToSession(sessionId)
        }
    }
}