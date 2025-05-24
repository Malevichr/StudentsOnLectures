package ru.malevichrp.studentsOnLectures.core.di

import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.fragments.login.authorization.ProvideAuthViewModel
import ru.malevichrp.studentsOnLectures.fragments.login.chooseProfile.ProvideChooseProfileViewModel
import ru.malevichrp.studentsOnLectures.fragments.login.registration.ProvideRegistrationViewModel

interface ProvideViewModel {
    fun <T : MyViewModel> provideViewModel(clazz: Class<T>): T
    class Make(
        core: Core
    ) : ProvideViewModel {
        private var chain: ProvideViewModel

        init {
            chain = Error()
            chain = ProvideRegistrationViewModel(core, chain)
            chain = ProvideChooseProfileViewModel(core, chain)
            chain = ProvideAuthViewModel(core, chain)
        }

        override fun <T : MyViewModel> provideViewModel(clazz: Class<T>): T =
            chain.provideViewModel(clazz)

    }

    class Error : ProvideViewModel {
        override fun <T : MyViewModel> provideViewModel(clazz: Class<T>): T {
            throw IllegalStateException("Unknown class: $clazz")
        }
    }

    abstract class AbstractChainLink(
        protected val core: Core,
        private val nextLink: ProvideViewModel,
        private val viewModelClass: Class<out MyViewModel>
    ) : ProvideViewModel {
        override fun <T : MyViewModel> provideViewModel(clazz: Class<T>): T {
            return if (viewModelClass == clazz)
                module().viewModel() as T
            else
                nextLink.provideViewModel(clazz)
        }

        protected abstract fun module(): Module<out MyViewModel>
    }
}