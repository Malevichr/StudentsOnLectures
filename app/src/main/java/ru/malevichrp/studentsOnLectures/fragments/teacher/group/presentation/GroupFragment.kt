package ru.malevichrp.studentsOnLectures.fragments.teacher.group.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.AbstractFragment
import ru.malevichrp.studentsOnLectures.core.presentation.BackAction
import ru.malevichrp.studentsOnLectures.databinding.FragmentGroupBinding
import ru.malevichrp.studentsOnLectures.views.recycler.TextRecyclerAdapter

class GroupFragment : AbstractFragment.Async<GroupUiState, GroupViewModel, FragmentGroupBinding>() {
    override val update: (GroupUiState) -> Unit = { uiState ->
        uiState.show(adapter)
    }
    private val adapter = TextRecyclerAdapter {

    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentGroupBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).provideViewModel(GroupViewModel::class.java)
        binding.name.text = viewModel.fullName()
        binding.groupName.text =  "Group: ${viewModel.groupName()}"

        binding.backButton.setOnClickListener {
            (requireActivity() as BackAction).back()
        }

        binding.startSessionButton.setOnClickListener {

        }

        binding.groupsRecycler.adapter = adapter

        (requireActivity() as BackAction).addCallback(viewLifecycleOwner) {
            viewModel.clear()
            parentFragmentManager.popBackStack()
        }

        viewModel.sessions()
    }
}