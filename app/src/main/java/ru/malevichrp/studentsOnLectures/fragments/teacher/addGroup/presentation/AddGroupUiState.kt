package ru.malevichrp.studentsOnLectures.fragments.teacher.addGroup.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.UiState
import ru.malevichrp.studentsOnLectures.views.errorInput.ErrorInputUiState
import ru.malevichrp.studentsOnLectures.views.errorInput.UpdateErrorInput

interface AddGroupUiState : UiState {
    fun show(updateErrorInput: UpdateErrorInput) = Unit
    fun navigateToBack(): Boolean = false

    object Base : AddGroupUiState {
        override fun show(updateErrorInput: UpdateErrorInput) {
            updateErrorInput.update(ErrorInputUiState.Base)
        }
    }

    class Error(private val errorMessage: String) : AddGroupUiState {
        override fun show(updateErrorInput: UpdateErrorInput) {
            updateErrorInput.update(ErrorInputUiState.Error(errorMessage))
        }
    }

    object Success : AddGroupUiState {
        override fun navigateToBack(): Boolean = true
    }
}