package ru.malevichrp.studentsOnLectures.fragments.login.authorization

import ru.malevichrp.studentsOnLectures.core.di.Core
import ru.malevichrp.studentsOnLectures.core.di.Module
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.fragments.login.authorization.data.AuthRepository
import ru.malevichrp.studentsOnLectures.fragments.login.authorization.presentation.AuthUiObservable
import ru.malevichrp.studentsOnLectures.fragments.login.authorization.presentation.AuthViewModel

class ProvideAuthViewModel(
    core: Core,
    nextLink: ProvideViewModel
) : ProvideViewModel.AbstractChainLink(
    core,
    nextLink,
    AuthViewModel::class.java
) {
    override fun module(): Module<out MyViewModel> = AuthModule(core)
}

class AuthModule(
    private val core: Core
) : Module<AuthViewModel> {
    override fun viewModel(): AuthViewModel {
        return AuthViewModel(
            AuthRepository.Base(
                isTeacher = core.sharedCollection.isTeacher,
                userDao = core.cacheModule.userDao(),
                targetTeacherId = core.sharedCollection.targetTeacherId,
                targetName = core.sharedCollection.targetName
            ),
            AuthUiObservable.Base(),
            core.runAsync,
            core.clearViewModel
        )
    }

}