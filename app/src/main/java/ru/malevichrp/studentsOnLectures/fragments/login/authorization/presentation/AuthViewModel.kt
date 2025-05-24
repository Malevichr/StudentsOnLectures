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
    private val clearViewModel: ClearViewModel
) : MyViewModel.Async.Abstract<AuthUiState>(
    runAsync,
    observable
) {
    fun login(authData: AuthData) {
        handleAsync {
            try {
                val response = repository.login(authData)
                if (response.isTeacher)
                    AuthUiState.SuccessTeacher.also { clear() }
                else
                    AuthUiState.SuccessStudent.also { clear() }
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

    fun clear() {
        clearViewModel.clear(this.javaClass)
    }
}