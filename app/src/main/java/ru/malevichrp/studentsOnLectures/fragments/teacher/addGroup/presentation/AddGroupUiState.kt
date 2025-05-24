package ru.malevichrp.studentsOnLectures.fragments.teacher.addGroup.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.UiState
import ru.malevichrp.studentsOnLectures.views.errorInput.ErrorInputUiState
import ru.malevichrp.studentsOnLectures.views.errorInput.UpdateError

interface AddGroupUiState : UiState {
    fun show(updateError: UpdateError) = Unit
    fun navigateToBack(): Boolean = false

    object Base : AddGroupUiState {
        override fun show(updateError: UpdateError) {
            updateError.update(ErrorInputUiState.Base)
        }
    }

    class Error(private val errorMessage: String) : AddGroupUiState {
        override fun show(updateError: UpdateError) {
            updateError.update(ErrorInputUiState.Error(errorMessage))
        }
    }

    object Success : AddGroupUiState {
        override fun navigateToBack(): Boolean = true
    }
}