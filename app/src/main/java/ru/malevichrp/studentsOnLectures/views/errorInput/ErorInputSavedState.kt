package ru.malevichrp.studentsOnLectures.views.errorInput


import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.os.Build


class ErrorInputSavedState : View.BaseSavedState {

    private lateinit var state: ErrorInputUiState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                ErrorInputUiState::class.java.classLoader,
                ErrorInputUiState::class.java
            ) as ErrorInputUiState
        } else {
            parcelIn.readSerializable() as ErrorInputUiState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): ErrorInputUiState = state

    fun save(uiState: ErrorInputUiState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ErrorInputSavedState> {
        override fun createFromParcel(parcel: Parcel): ErrorInputSavedState =
            ErrorInputSavedState(parcel)

        override fun newArray(size: Int): Array<ErrorInputSavedState?> =
            arrayOfNulls(size)
    }
}