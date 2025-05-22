package ru.malevichrp.studentsOnLectures.fragments.registration.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.UiState
import ru.malevichrp.studentsOnLectures.views.errorInput.ErrorInputUiState
import ru.malevichrp.studentsOnLectures.views.errorInput.UpdateError

interface RegistrationUiState : UiState {
    fun show(updateError: UpdateError) = Unit
    fun navigateToBack(): Boolean = false

    object Base : RegistrationUiState {
        override fun show(updateError: UpdateError) {
            updateError.update(ErrorInputUiState.Base)
        }
    }

    class Error(private val errorMessage: String) : RegistrationUiState {
        override fun show(updateError: UpdateError) {
            updateError.update(ErrorInputUiState.Error(errorMessage))
        }
    }

    object Success : RegistrationUiState {
        override fun navigateToBack(): Boolean = true
    }
}