package ru.malevichrp.studentsOnLectures.views.common

import android.view.View
import java.io.Serializable


interface VisibilityUiState : Serializable {
    fun update(updateVisibility: UpdateVisibility)
    abstract class Abstract(private val visibility: Int) : VisibilityUiState {
        override fun update(updateVisibility: UpdateVisibility) {
            updateVisibility.update(visibility)
        }
    }

    object Visible : Abstract(View.VISIBLE) {
        private fun readResolve(): Any = Visible
    }

    object Invisible : Abstract(View.INVISIBLE) {
        private fun readResolve(): Any = Invisible
    }

    object Gone : Abstract(View.GONE)
}