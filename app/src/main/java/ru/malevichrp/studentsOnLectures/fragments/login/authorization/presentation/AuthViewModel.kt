package ru.malevichrp.studentsOnLectures.fragments.login.authorization.presentation

import ru.malevichrp.studentsOnLectures.core.di.ClearViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.RunAsync
import ru.malevichrp.studentsOnLectures.fragments.login.authorization.data.AuthData
import ru.malevichrp.studentsOnLectures.fragments.login.authorization.data.AuthRepository

class AuthViewModel(
    private val repository: AuthRepository,
    observable: AuthUiObservable,
    runAsync: RunAsync,
    clearViewModel: ClearViewModel
) : MyViewModel.Async.Abstract<AuthUiState>(
    runAsync,
    observable,
    clearViewModel
) {
    fun login(authData: AuthData) {
        handleAsync {
            try {
                val response = repository.login(authData)
                clear()
                if (response.isTeacher)
                    AuthUiState.SuccessTeacher
                else
                    AuthUiState.SuccessStudent
            } catch (e: Exception) {
                AuthUiState.Error(e.message.toString())
            }
        }
    }

    fun init(): AuthUiState {
        return if (repository.isTeacher())
            AuthUiState.Teacher
        else
            AuthUiState.Student
    }
}