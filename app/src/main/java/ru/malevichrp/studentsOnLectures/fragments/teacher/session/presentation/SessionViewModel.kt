package ru.malevichrp.studentsOnLectures.fragments.teacher.session.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.malevichrp.studentsOnLectures.core.di.ClearViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.RunAsync
import ru.malevichrp.studentsOnLectures.fragments.abstrstractions.presentation.ShowFullNameViewModel
import ru.malevichrp.studentsOnLectures.fragments.teacher.abstractions.presentation.ShowGroupNameViewModel
import ru.malevichrp.studentsOnLectures.fragments.teacher.group.presentation.TimeFormater
import ru.malevichrp.studentsOnLectures.fragments.teacher.session.data.SessionRepository
import ru.malevichrp.studentsOnLectures.fragments.teacher.session.data.SessionStatus
import ru.malevichrp.studentsOnLectures.fragments.teacher.session.data.StudentData
import ru.malevichrp.studentsOnLectures.views.recycler.TextItem

class SessionViewModel(
    private val repository: SessionRepository,
    runAsync: RunAsync,
    uiObservable: SessionUiObservable,
    clearViewModel: ClearViewModel,
    private val timeFormater: TimeFormater = TimeFormater.Base()
) : MyViewModel.Async.Abstract<SessionUiState>(
    runAsync,
    uiObservable,
    clearViewModel
), ShowFullNameViewModel by ShowFullNameViewModel.Base(repository),
    ShowGroupNameViewModel by ShowGroupNameViewModel.Base(repository) {
    private var currentSessionStatus: SessionStatus = SessionStatus.IsOff
    private val students: MutableList<TextItem> = mutableListOf()

    fun timestamp() = timeFormater.format(repository.timestamp())
    fun init() {
        handleAsync {
            try {
                students.clear()
                students.addAll(repository.students().map { it.toTextItem() })
                currentSessionStatus = repository.status()
                updateUi()
            } catch (e: Exception) {
                SessionUiState.Error(e.message.toString())
            }
        }
    }

    private fun updateUi(): SessionUiState {
        return try {
            when (currentSessionStatus) {
                SessionStatus.IsOff -> SessionUiState.Inactive(students)
                SessionStatus.IsOn -> SessionUiState.Active(students)
                SessionStatus.IsLoading -> SessionUiState.Progress(students)
                else -> SessionUiState.Empty
            }
        } catch (e: Exception) {
            SessionUiState.Error(e.message.toString())
        }
    }

    fun startSession() {
        handleAsync {
            repository.startSession(
                updateStatus = { sessionStatus ->
                    currentSessionStatus = sessionStatus
                    withContext(Dispatchers.Main){
                        observable.postUiState(updateUi())
                    }
                },
                addStudent = { studentData ->
                    synchronized(this) {
                        students.add(studentData.toTextItem())
                    }
                    withContext(Dispatchers.Main) {
                        observable.postUiState(updateUi())
                    }
                }
            )
            SessionUiState.Empty
        }
    }

    fun stopSession() {
        repository.stopSession()
    }
}

