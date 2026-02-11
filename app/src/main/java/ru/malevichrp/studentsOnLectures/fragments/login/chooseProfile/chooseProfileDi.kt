package ru.malevichrp.studentsOnLectures.fragments.login.chooseProfile

import ru.malevichrp.studentsOnLectures.core.di.Core
import ru.malevichrp.studentsOnLectures.core.di.Module
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.fragments.login.chooseProfile.presentation.ChooseProfileViewModel
import ru.malevichrp.studentsOnLectures.fragments.login.chooseProfile.repsoitory.ChooseProfileRepository

class ProvideChooseProfileViewModel(
    core: Core,
    nextLink: ProvideViewModel
) : ProvideViewModel.AbstractChainLink(
    core,
    nextLink,
    ChooseProfileViewModel::class.java
) {
    override fun module(): Module<out MyViewModel> = ChooseProfileModule(core)
}

class ChooseProfileModule(
    private val core: Core
) : Module<ChooseProfileViewModel> {
    override fun viewModel(): ChooseProfileViewModel {
        return ChooseProfileViewModel(
            ChooseProfileRepository.Base(
                core.sharedCollection.isTeacher,
            ),
            core.clearViewModel
        )
    }
}