package ru.malevichrp.studentsOnLectures.fragments.student.searchSession

import ru.malevichrp.studentsOnLectures.core.di.Core
import ru.malevichrp.studentsOnLectures.core.di.Module
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.fragments.student.searchSession.data.SearchSessionRepository
import ru.malevichrp.studentsOnLectures.fragments.student.searchSession.presentation.SearchSessionUiObservable
import ru.malevichrp.studentsOnLectures.fragments.student.searchSession.presentation.SearchSessionViewModel

class ProvideSearchSessionViewModel(
    core: Core,
    nextLink: ProvideViewModel
) : ProvideViewModel.AbstractChainLink(
    core,
    nextLink,
    SearchSessionViewModel::class.java
) {
    override fun module(): Module<out MyViewModel> = SearchSessionModule(core)
}

class SearchSessionModule(private val core: Core) : Module<SearchSessionViewModel> {
    override fun viewModel(): SearchSessionViewModel {
        return SearchSessionViewModel(
            SearchSessionRepository.Fake(core.sharedCollection.targetName),
            core.runAsync,
            SearchSessionUiObservable.Base(),
            core.clearViewModel
        )
    }
}