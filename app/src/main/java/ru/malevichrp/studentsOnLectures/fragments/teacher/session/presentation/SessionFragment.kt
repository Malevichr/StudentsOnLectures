package ru.malevichrp.studentsOnLectures.fragments.teacher.session.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.AbstractFragment
import ru.malevichrp.studentsOnLectures.core.presentation.BackAction
import ru.malevichrp.studentsOnLectures.databinding.FragmentSessionBinding
import ru.malevichrp.studentsOnLectures.views.recycler.TextRecyclerAdapter

class SessionFragment :
    AbstractFragment.Async<SessionUiState, SessionViewModel, FragmentSessionBinding>() {
    override val update: (SessionUiState) -> Unit = { uiState ->
        uiState.show(
            adapter = adapter,
            binding.statusText,
            binding.progress,
            binding.startSessionButton,
            binding.stopSessionButton
        )
    }
    private val adapter: TextRecyclerAdapter = TextRecyclerAdapter { }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSessionBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).provideViewModel(SessionViewModel::class.java)

        binding.name.text = viewModel.fullName()
        binding.groupName.text = "Group: ${viewModel.groupName()}"
        binding.sessionTimestamp.text = viewModel.timestamp()

        binding.startSessionButton.setOnClickListener {
            viewModel.startSession()
        }

        binding.stopSessionButton.setOnClickListener {
            viewModel.stopSession()
        }
        binding.backButton.setOnClickListener {
            (requireActivity() as BackAction).back()
        }
        binding.groupsRecycler.adapter = adapter
        (requireActivity() as BackAction).addCallback(viewLifecycleOwner) {
            viewModel.clear()
            parentFragmentManager.popBackStack()
        }
        viewModel.init()
    }
}