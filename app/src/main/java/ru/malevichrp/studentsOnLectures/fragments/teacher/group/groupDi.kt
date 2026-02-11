package ru.malevichrp.studentsOnLectures.fragments.teacher.group

import ru.malevichrp.studentsOnLectures.core.di.Core
import ru.malevichrp.studentsOnLectures.core.di.Module
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.fragments.teacher.group.data.GroupRepository
import ru.malevichrp.studentsOnLectures.fragments.teacher.group.presentation.GroupUiObservable
import ru.malevichrp.studentsOnLectures.fragments.teacher.group.presentation.GroupViewModel

class ProvideGroupViewModel(
    core: Core,
    nextLink: ProvideViewModel
) : ProvideViewModel.AbstractChainLink(
    core,
    nextLink,
    GroupViewModel::class.java
) {
    override fun module(): Module<out MyViewModel> = GroupModule(core)
}

class GroupModule(
    private val core: Core
) : Module<GroupViewModel> {
    override fun viewModel(): GroupViewModel =
        GroupViewModel(
            GroupRepository.Base(
                targetName = core.sharedCollection.targetName,
                sessionDao = core.cacheModule.sessionDao(),
                targetGroupId = core.sharedCollection.targetGroupId,
                targetGroupName = core.sharedCollection.targetGroupName,
                targetSessionTimestamp = core.sharedCollection.targetSessionTimestamp,
                targetSessionId = core.sharedCollection.targetSessionId
            ),
            core.runAsync,
            GroupUiObservable.Base(),
            core.clearViewModel
        )
}