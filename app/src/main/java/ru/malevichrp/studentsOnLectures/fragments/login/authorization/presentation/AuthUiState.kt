package ru.malevichrp.studentsOnLectures.fragments.login.authorization.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.UiState
import ru.malevichrp.studentsOnLectures.fragments.student.searchSession.presentation.NavigateToStudentSearch
import ru.malevichrp.studentsOnLectures.fragments.teacher.groups.presentation.NavigateToTeacherGroups
import ru.malevichrp.studentsOnLectures.views.errorInput.ErrorInputUiState
import ru.malevichrp.studentsOnLectures.views.errorInput.UpdateError
import ru.malevichrp.studentsOnLectures.views.text.UpdateText

interface AuthUiState : UiState {
    fun show(
        updateError: UpdateError,
        updateText: UpdateText
    ) = Unit

    fun navigateTeacher(navigate: NavigateToTeacherGroups) = Unit
    fun navigateStudent(navigate: NavigateToStudentSearch) = Unit

    object Base : AuthUiState {
        override fun show(updateError: UpdateError, updateText: UpdateText) {
            updateError.update(ErrorInputUiState.Base)
        }
    }

    class Error(private val errorMessage: String) : AuthUiState {
        override fun show(updateError: UpdateError, updateText: UpdateText) {
            updateError.update(ErrorInputUiState.Error(errorMessage))
        }
    }

    object SuccessTeacher : AuthUiState {
        override fun navigateTeacher(navigate: NavigateToTeacherGroups) {
            navigate.navigateToTeacherGroups()
        }
    }

    object SuccessStudent : AuthUiState {
        override fun navigateStudent(navigate: NavigateToStudentSearch) {
            navigate.navigateToStudentSearch()
        }
    }

    object Teacher : AuthUiState {
        override fun show(updateError: UpdateError, updateText: UpdateText) {
            updateText.update("Teacher")
        }
    }

    object Student : AuthUiState {
        override fun show(updateError: UpdateError, updateText: UpdateText) {
            updateText.update("Student")
        }
    }
}