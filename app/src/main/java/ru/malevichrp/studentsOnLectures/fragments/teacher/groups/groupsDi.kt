package ru.malevichrp.studentsOnLectures.fragments.teacher.groups

import ru.malevichrp.studentsOnLectures.core.di.Core
import ru.malevichrp.studentsOnLectures.core.di.Module
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.fragments.teacher.groups.data.GroupsRepository
import ru.malevichrp.studentsOnLectures.fragments.teacher.groups.presentation.GroupsUiObservable
import ru.malevichrp.studentsOnLectures.fragments.teacher.groups.presentation.GroupsViewModel

class ProvideGroupsViewModel(
    core: Core,
    nextLink: ProvideViewModel
) : ProvideViewModel.AbstractChainLink(
    core,
    nextLink,
    GroupsViewModel::class.java
) {
    override fun module(): Module<out MyViewModel> = GroupsModule(core)
}

class GroupsModule(
    private val core: Core
) : Module<GroupsViewModel> {
    override fun viewModel(): GroupsViewModel =
        GroupsViewModel(
            GroupsRepository.Base(
                targetTeacherId = core.sharedCollection.targetTeacherId,
                groupsDao = core.cacheModule.groupDao(),
                targetGroupId = core.sharedCollection.targetGroupId,
                targetGroupName = core.sharedCollection.targetGroupName,
                targetName = core.sharedCollection.targetName
            ),
            GroupsUiObservable.Base(),
            core.runAsync,
            core.clearViewModel
        )
}
