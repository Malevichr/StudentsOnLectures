package ru.malevichrp.studentsOnLectures.views.errorInput

import android.content.Context
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import ru.malevichrp.studentsOnLectures.databinding.ErrorInputBinding

class ErrorInputCustomView : FrameLayout, UpdateError {
    private val binding = ErrorInputBinding.inflate(LayoutInflater.from(this.context), this, true)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            update(ErrorInputUiState.Base)
        }
    }

    init {
        binding.inputEditText.addTextChangedListener(textWatcher)
    }

    private var state: ErrorInputUiState = ErrorInputUiState.Base

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = ErrorInputSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as ErrorInputSavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }

    override fun update(uiState: ErrorInputUiState) {
        state = uiState
        state.update(this)
    }

    override fun update(isError: Boolean) {
        binding.inputLayout.isErrorEnabled = isError
    }

    override fun update(errorText: String?) {
        binding.inputLayout.error = errorText
    }

    fun inputText(): String = binding.inputEditText.text.toString()
}

interface UpdateError {
    fun update(uiState: ErrorInputUiState)
    fun update(isError: Boolean)
    fun update(errorText: String?)
}