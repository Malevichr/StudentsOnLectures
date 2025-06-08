package ru.malevichrp.studentsOnLectures.views.errorInput

import java.io.Serializable

interface ErrorInputUiState : Serializable {
    fun update(updateErrorInput: UpdateErrorInput)
    abstract class Abstract(
        private val isError: Boolean,
        private val errorMessage: String = "",
    ) : ErrorInputUiState {
        override fun update(updateErrorInput: UpdateErrorInput) {
            updateErrorInput.update(isError)
            updateErrorInput.update(
                if (isError)
                    errorMessage
                else
                    null
            )

        }
    }

    object Base : Abstract(false)
    class Error(errorText: String) : Abstract(true, errorText)
}