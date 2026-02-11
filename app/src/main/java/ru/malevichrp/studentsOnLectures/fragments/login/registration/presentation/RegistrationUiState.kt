package ru.malevichrp.studentsOnLectures.fragments.login.registration.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.UiState
import ru.malevichrp.studentsOnLectures.views.errorInput.ErrorInputUiState
import ru.malevichrp.studentsOnLectures.views.errorInput.UpdateErrorInput

interface RegistrationUiState : UiState {
    fun show(updateErrorInput: UpdateErrorInput) = Unit
    fun navigateToBack(): Boolean = false

    object Base : RegistrationUiState {
        override fun show(updateErrorInput: UpdateErrorInput) {
            updateErrorInput.update(ErrorInputUiState.Base)
        }
    }

    class Error(private val errorMessage: String) : RegistrationUiState {
        override fun show(updateErrorInput: UpdateErrorInput) {
            updateErrorInput.update(ErrorInputUiState.Error(errorMessage))
        }
    }

    object Success : RegistrationUiState {
        override fun navigateToBack(): Boolean = true
    }
}