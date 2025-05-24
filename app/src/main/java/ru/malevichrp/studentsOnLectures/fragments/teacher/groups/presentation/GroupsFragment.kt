package ru.malevichrp.studentsOnLectures.fragments.teacher.groups.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.AbstractFragment
import ru.malevichrp.studentsOnLectures.core.presentation.BackAction
import ru.malevichrp.studentsOnLectures.databinding.FragmentGroupsBinding
import ru.malevichrp.studentsOnLectures.views.recycler.TextRecyclerAdapter

class GroupsFragment :
    AbstractFragment.Async<GroupsUiState, GroupsViewModel, FragmentGroupsBinding>() {

    override val update: (GroupsUiState) -> Unit = { uiState ->
        uiState.show(adapter)
    }
    private val adapter = TextRecyclerAdapter { groupId ->
        viewModel.navigateToGroup(groupId)
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

        }
        viewModel.groups()

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.clear()
                    parentFragmentManager.popBackStack()
                }
            })
    }
}