package ru.malevichrp.studentsOnLectures.fragments.teacher.group.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.UiState
import ru.malevichrp.studentsOnLectures.views.recycler.TextItem
import ru.malevichrp.studentsOnLectures.views.recycler.TextRecyclerAdapter

interface GroupUiState : UiState {
    fun show(textRecyclerAdapter: TextRecyclerAdapter)

    class Base(private val sessions: List<TextItem>) : GroupUiState {
        override fun show(textRecyclerAdapter: TextRecyclerAdapter) {
            textRecyclerAdapter.update(sessions)
        }
    }
}