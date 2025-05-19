package ru.malevichrp.studentsOnLectures.fragments.registration

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel

class RegistrationViewModel(
    private val repository: RegistrationRepository
) : MyViewModel {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun register(registrationData: RegistrationData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.register(registrationData)
        }
    }
}

