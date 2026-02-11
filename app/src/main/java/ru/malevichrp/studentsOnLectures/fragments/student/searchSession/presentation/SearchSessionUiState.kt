package ru.malevichrp.studentsOnLectures.fragments.student.searchSession.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.UiState
import ru.malevichrp.studentsOnLectures.views.common.UpdateVisibility
import ru.malevichrp.studentsOnLectures.views.common.VisibilityUiState
import ru.malevichrp.studentsOnLectures.views.text.UpdateCustomText
import ru.malevichrp.studentsOnLectures.views.text.UpdateText

interface SearchSessionUiState : UiState {
    fun show(
        searchButton: UpdateVisibility,
        progress: UpdateVisibility,
        teacherName: UpdateCustomText,
        connectButton: UpdateVisibility,
        statusText: UpdateCustomText,
        retryButton: UpdateVisibility
    )

    abstract class Abstract(
        private val searchVisibility: VisibilityUiState = VisibilityUiState.Gone,
        private val progressVisibility: VisibilityUiState = VisibilityUiState.Gone,
        private val teacherNameText: String = "",
        private val teacherVisibility: VisibilityUiState = VisibilityUiState.Gone,
        private val connectVisibility: VisibilityUiState = VisibilityUiState.Gone,
        private val statusText: String = "",
        private val statusVisibility: VisibilityUiState = VisibilityUiState.Gone,
        private val retryVisibility: VisibilityUiState = VisibilityUiState.Gone
    ) : SearchSessionUiState {
        override fun show(
            searchButton: UpdateVisibility,
            progress: UpdateVisibility,
            teacherName: UpdateCustomText,
            connectButton: UpdateVisibility,
            statusText: UpdateCustomText,
            retryButton: UpdateVisibility
        ) {
            searchButton.update(searchVisibility)
            progress.update(progressVisibility)
            teacherName.update(teacherVisibility)
            teacherName.update(teacherNameText)
            connectButton.update(connectVisibility)
            statusText.update(statusVisibility)
            statusText.update(this.statusText)
            retryButton.update(retryVisibility)
        }
    }

    object Initial : Abstract(
        searchVisibility = VisibilityUiState.Visible
    )

    class Progress(teacherName: String) : Abstract(
        teacherNameText = teacherName,
        teacherVisibility = VisibilityUiState.Visible,
        progressVisibility = VisibilityUiState.Visible
    )

    class ConnectToSession(teacherNameText: String) : Abstract(
        teacherNameText = teacherNameText,
        teacherVisibility = VisibilityUiState.Visible,
        connectVisibility = VisibilityUiState.Visible,
        searchVisibility = VisibilityUiState.Visible,
    )

    class ErrorFind(errorText: String = "Error") : Abstract(
        statusVisibility = VisibilityUiState.Visible,
        statusText = errorText,
        searchVisibility = VisibilityUiState.Visible
    )

    class ErrorConnect(
        teacherNameText: String,
        errorText: String
    ) : Abstract(
        teacherNameText = teacherNameText,
        teacherVisibility = VisibilityUiState.Visible,
        statusText = errorText,
        statusVisibility = VisibilityUiState.Visible,
        searchVisibility = VisibilityUiState.Visible,
        retryVisibility = VisibilityUiState.Visible
    )
}