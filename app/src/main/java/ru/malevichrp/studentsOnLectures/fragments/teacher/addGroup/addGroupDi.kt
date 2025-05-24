package ru.malevichrp.studentsOnLectures.fragments.teacher.addGroup

import ru.malevichrp.studentsOnLectures.core.di.Core
import ru.malevichrp.studentsOnLectures.core.di.Module
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.fragments.teacher.addGroup.data.AddGroupRepository
import ru.malevichrp.studentsOnLectures.fragments.teacher.addGroup.presentation.AddGroupUiObservable
import ru.malevichrp.studentsOnLectures.fragments.teacher.addGroup.presentation.AddGroupViewModel

class ProvideAddGroupViewModel(
    core: Core,
    nextLink: ProvideViewModel
) : ProvideViewModel.AbstractChainLink(
    core,
    nextLink,
    AddGroupViewModel::class.java
) {
    override fun module(): Module<out MyViewModel> = AddGroupModule(core)
}

class AddGroupModule(
    private val core: Core
) : Module<AddGroupViewModel> {
    override fun viewModel(): AddGroupViewModel = AddGroupViewModel(
        AddGroupRepository.Base(
            targetTeacherId = core.sharedCollection.targetTeacherId,
            groupsDao = core.cacheModule.groupDao()
        ),
        runAsync = core.runAsync,
        uiObservable = AddGroupUiObservable.Base(),
        clearViewModel = core.clearViewModel
    )
}