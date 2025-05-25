package ru.malevichrp.studentsOnLectures.fragments.teacher.addGroup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.AbstractFragment
import ru.malevichrp.studentsOnLectures.core.presentation.BackAction
import ru.malevichrp.studentsOnLectures.databinding.FragmentAddGroupBinding

class AddGroupFragment :
    AbstractFragment.Async<AddGroupUiState, AddGroupViewModel, FragmentAddGroupBinding>() {
    override val update: (AddGroupUiState) -> Unit = { uiState ->
        uiState.show(binding.groupInput)
        if (uiState.navigateToBack())
            (requireActivity() as BackAction).back()
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentAddGroupBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).provideViewModel(AddGroupViewModel::class.java)

        binding.addGroupButton.setOnClickListener {
            val groupName = binding.groupInput.inputText()
            viewModel.addGroup(groupName)
        }

        binding.backButton.setOnClickListener {
            (requireActivity() as BackAction).back()
        }
        binding.groupInput.addHint("Group name")
        binding.teacherNameText.text = viewModel.fullName()

        (requireActivity() as BackAction).addCallback(viewLifecycleOwner) {
            viewModel.clear()
            parentFragmentManager.popBackStack()
        }
    }
}