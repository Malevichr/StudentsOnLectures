package ru.malevichrp.studentsOnLectures.fragments.teacher.session.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.UiState
import ru.malevichrp.studentsOnLectures.views.common.UpdateVisibility
import ru.malevichrp.studentsOnLectures.views.common.VisibilityUiState
import ru.malevichrp.studentsOnLectures.views.recycler.TextItem
import ru.malevichrp.studentsOnLectures.views.recycler.TextRecyclerAdapter
import ru.malevichrp.studentsOnLectures.views.text.UpdateText

interface SessionUiState : UiState {
    fun show(
        adapter: TextRecyclerAdapter,
        updateStatusText: UpdateText,
        updateProgressBar: UpdateVisibility,
        updateStartButton: UpdateVisibility,
        updateStopButton: UpdateVisibility,
    ) = Unit


    abstract class Abstract(
        private val textItems: List<TextItem> = listOf(),
        private val statusText: String,
        private val progressVisibility: VisibilityUiState = VisibilityUiState.Gone,
        private val startVisibility: VisibilityUiState = VisibilityUiState.Gone,
        private val stopVisibility: VisibilityUiState = VisibilityUiState.Gone
    ) : SessionUiState {
        override fun show(
            adapter: TextRecyclerAdapter,
            updateStatusText: UpdateText,
            updateProgressBar: UpdateVisibility,
            updateStartButton: UpdateVisibility,
            updateStopButton: UpdateVisibility,
        ) {
            adapter.update(textItems)
            updateStatusText.update(statusText)
            updateProgressBar.update(progressVisibility)
            updateStartButton.update(startVisibility)
            updateStopButton.update(stopVisibility)
        }
    }

    class Inactive(textItems: List<TextItem>) : Abstract(
        textItems = textItems,
        statusText = "Session is inactive",
        startVisibility = VisibilityUiState.Visible,
    )

    class Error(errorText: String) : Abstract(
        textItems = listOf(),
        statusText = errorText,
        startVisibility = VisibilityUiState.Visible,
    )

    class Active(textItems: List<TextItem>) : Abstract(
        textItems = textItems,
        statusText = "Session is active",
        stopVisibility = VisibilityUiState.Visible
    )

    class Progress(textItems: List<TextItem>) : Abstract(
        textItems = textItems,
        statusText = "Session is starting",
        progressVisibility = VisibilityUiState.Visible,
    )

    object Empty : SessionUiState
}