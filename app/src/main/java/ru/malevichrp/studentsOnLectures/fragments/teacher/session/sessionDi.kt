package ru.malevichrp.studentsOnLectures.fragments.teacher.session

import ru.malevichrp.studentsOnLectures.core.di.Core
import ru.malevichrp.studentsOnLectures.core.di.Module
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.fragments.teacher.session.data.SessionRepository
import ru.malevichrp.studentsOnLectures.fragments.teacher.session.data.SessionService
import ru.malevichrp.studentsOnLectures.fragments.teacher.session.presentation.SessionUiObservable
import ru.malevichrp.studentsOnLectures.fragments.teacher.session.presentation.SessionViewModel

class ProvideSessionViewModel(
    core: Core,
    nextLink: ProvideViewModel
) : ProvideViewModel.AbstractChainLink(
    core,
    nextLink,
    SessionViewModel::class.java
) {
    override fun module(): Module<out MyViewModel> = SessionModule(core)
}

class SessionModule(private val core: Core) : Module<SessionViewModel> {
    override fun viewModel(): SessionViewModel {
        val repository = with(core.sharedCollection) {
            SessionRepository.Base(
                targetGroupName = targetGroupName,
                targetName = targetName,
                targetSessionTimestamp = targetSessionTimestamp,
                targetSessionId = targetSessionId,
                studentDao = core.cacheModule.studentDao(),
                sessionService = SessionService.Fake()
            )
        }

        return with(core) {
            SessionViewModel(
                repository,
                runAsync,
                SessionUiObservable.Base(),
                clearViewModel
            )
        }
    }
}