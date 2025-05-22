package ru.malevichrp.studentsOnLectures.fragments.registration.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.RunAsync
import ru.malevichrp.studentsOnLectures.fragments.registration.data.RegistrationData
import ru.malevichrp.studentsOnLectures.fragments.registration.data.RegistrationRepository

class RegistrationViewModel(
    private val repository: RegistrationRepository,
    uiObservable: RegistrationUiObservable,
    runAsync: RunAsync
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
}

