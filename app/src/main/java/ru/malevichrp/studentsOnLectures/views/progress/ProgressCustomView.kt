package ru.malevichrp.studentsOnLectures.views.progress

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.ProgressBar
import ru.malevichrp.studentsOnLectures.views.common.UpdateVisibility
import ru.malevichrp.studentsOnLectures.views.common.VisibilitySavedState
import ru.malevichrp.studentsOnLectures.views.common.VisibilityUiState


class ProgressCustomView : ProgressBar, UpdateVisibility {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var uiState: VisibilityUiState = VisibilityUiState.Visible

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = VisibilitySavedState(it)
            savedState.save(uiState)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as VisibilitySavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }

    override fun update(visibility: Int) {
        this.visibility = visibility
    }

    override fun update(visibilityUiState: VisibilityUiState) {
        uiState = visibilityUiState
        uiState.update(this)
    }
}

