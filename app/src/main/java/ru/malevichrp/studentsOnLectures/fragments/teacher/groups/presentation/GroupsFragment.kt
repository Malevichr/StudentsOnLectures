package ru.malevichrp.studentsOnLectures.fragments.teacher.groups.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.AbstractFragment
import ru.malevichrp.studentsOnLectures.core.presentation.BackAction
import ru.malevichrp.studentsOnLectures.databinding.FragmentGroupsBinding
import ru.malevichrp.studentsOnLectures.fragments.teacher.addGroup.presentation.NavigateToAddGroup
import ru.malevichrp.studentsOnLectures.fragments.teacher.group.presentation.NavigateToGroup
import ru.malevichrp.studentsOnLectures.views.recycler.TextRecyclerAdapter

class GroupsFragment :
    AbstractFragment.Async<GroupsUiState, GroupsViewModel, FragmentGroupsBinding>() {

    override val update: (GroupsUiState) -> Unit = { uiState ->
        uiState.show(adapter)
    }
    private val adapter = TextRecyclerAdapter { groupId ->
        viewModel.navigateToGroup(groupId)
        (requireActivity() as NavigateToGroup).navigateToGroup()
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentGroupsBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).provideViewModel(GroupsViewModel::class.java)
        binding.groupsRecycler.adapter = adapter

        binding.exitButton.setOnClickListener {
            (requireActivity() as BackAction).back()
        }
        binding.addGroupButton.setOnClickListener {
            viewModel.clear()
            (requireActivity() as NavigateToAddGroup).navigateToAddGroup()
        }
        viewModel.groups()

        binding.name.text = viewModel.fullName()

        (requireActivity() as BackAction).addCallback(viewLifecycleOwner) {
            viewModel.clear()
            parentFragmentManager.popBackStack()
        }
    }
}