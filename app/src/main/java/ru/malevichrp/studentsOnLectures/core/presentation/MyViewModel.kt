package ru.malevichrp.studentsOnLectures.core.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.malevichrp.studentsOnLectures.core.di.ClearViewModel


interface MyViewModel {
    fun clear()
    interface Async<T : UiState> : MyViewModel {
        fun startUpdates(observer: (T) -> Unit)
        fun stopUpdates()
        fun handleAsync(heavyOperation: suspend () -> T)

        abstract class Abstract<T : UiState>(
            private val runAsync: RunAsync,
            protected val observable: UiObservable<T>,
            private val clearViewModel: ClearViewModel
        ) : Async<T> {
            protected val viewModelScope =
                CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

            private val updateUi = { uiState: T ->
                observable.postUiState(uiState)
            }

            override fun handleAsync(heavyOperation: suspend () -> T) {
                runAsync.runAsync(
                    coroutineScope = viewModelScope,
                    heavyOperation = heavyOperation,
                    uiUpdate = updateUi
                )
            }

            override fun startUpdates(observer: (T) -> Unit) {
                observable.register(observer)
            }

            override fun stopUpdates() {
                observable.unregister()
            }

            override fun clear() {
                clearViewModel.clear(this.javaClass)
            }
        }
    }
}