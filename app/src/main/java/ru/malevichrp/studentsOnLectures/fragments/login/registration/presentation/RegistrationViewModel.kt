package ru.malevichrp.studentsOnLectures.fragments.login.registration.presentation

import ru.malevichrp.studentsOnLectures.core.di.ClearViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.RunAsync
import ru.malevichrp.studentsOnLectures.fragments.login.registration.data.RegistrationData
import ru.malevichrp.studentsOnLectures.fragments.login.registration.data.RegistrationRepository

class RegistrationViewModel(
    private val repository: RegistrationRepository,
    uiObservable: RegistrationUiObservable,
    runAsync: RunAsync,
    private val clearViewModel: ClearViewModel
) : MyViewModel.Async.Abstract<RegistrationUiState>(
    runAsync,
    uiObservable
) {
    fun register(registrationData: RegistrationData) {
        handleAsync {
            try {
                repository.register(registrationData)
                RegistrationUiState.Success
            } catch (e: Exception) {
                RegistrationUiState.Error(e.message.toString())
            }
        }
    }

    fun clear() {
        clearViewModel.clear(this.javaClass)
    }
}

