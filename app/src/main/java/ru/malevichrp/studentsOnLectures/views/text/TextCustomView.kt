package ru.malevichrp.studentsOnLectures.views.text

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import ru.malevichrp.studentsOnLectures.views.common.UpdateVisibility
import ru.malevichrp.studentsOnLectures.views.common.VisibilitySavedState
import ru.malevichrp.studentsOnLectures.views.common.VisibilityUiState

class TextCustomView : androidx.appcompat.widget.AppCompatTextView, UpdateCustomText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var state: VisibilityUiState = VisibilityUiState.Gone

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = VisibilitySavedState(it)
            savedState.save(state)
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
        state = visibilityUiState
        state.update(this)
    }

    override fun update(text: String) {
        this.text = text
    }
}

interface UpdateCustomText : UpdateVisibility, UpdateText
interface UpdateText {
    fun update(text: String)
}