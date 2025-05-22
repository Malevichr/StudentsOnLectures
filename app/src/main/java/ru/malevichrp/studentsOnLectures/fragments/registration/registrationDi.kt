package ru.malevichrp.studentsOnLectures.fragments.registration

import ru.malevichrp.studentsOnLectures.core.di.Core
import ru.malevichrp.studentsOnLectures.core.di.Module
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.fragments.registration.data.RegistrationRepository
import ru.malevichrp.studentsOnLectures.fragments.registration.presentation.RegistrationUiObservable
import ru.malevichrp.studentsOnLectures.fragments.registration.presentation.RegistrationViewModel

class ProvideRegistrationViewModel(
    core: Core,
    nextChainLink: ProvideViewModel
) : ProvideViewModel.AbstractChainLink(
    core,
    nextChainLink,
    RegistrationViewModel::class.java
) {
    override fun module(): Module<out MyViewModel> {
        return RegistrationModule(core)
    }
}

class RegistrationModule(
    private val core: Core
) : Module<RegistrationViewModel> {
    override fun viewModel(): RegistrationViewModel {
        return RegistrationViewModel(
            RegistrationRepository.Base(core.cacheModule.userDao()),
            RegistrationUiObservable.Base(),
            core.runAsync
        )
    }
}