package ru.malevichrp.studentsOnLectures.fragments.teacher.groups.presentation

import ru.malevichrp.studentsOnLectures.core.presentation.UiState
import ru.malevichrp.studentsOnLectures.views.recycler.TextItem
import ru.malevichrp.studentsOnLectures.views.recycler.TextRecyclerAdapter

interface GroupsUiState : UiState {
    fun show(adapter: TextRecyclerAdapter)
    class Base(private val textItems: List<TextItem>) : GroupsUiState {
        override fun show(adapter: TextRecyclerAdapter) {
            adapter.update(textItems)
        }
    }
}