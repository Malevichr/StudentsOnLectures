package ru.malevichrp.studentsOnLectures.views.errorInput

import java.io.Serializable

interface ErrorInputUiState : Serializable {
    fun update(updateError: UpdateError)
    abstract class Abstract(
        private val isError: Boolean,
        private val errorMessage: String = "",
    ) : ErrorInputUiState {
        override fun update(updateError: UpdateError) {
            updateError.update(isError)
            updateError.update(
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