package ru.malevichrp.studentsOnLectures.fragments.student.searchSession.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.AbstractFragment
import ru.malevichrp.studentsOnLectures.core.presentation.BackAction
import ru.malevichrp.studentsOnLectures.databinding.FragmentSearchSessionBinding

class SearchSessionFragment :
    AbstractFragment.Async<SearchSessionUiState, SearchSessionViewModel, FragmentSearchSessionBinding>() {
    override val update: (SearchSessionUiState) -> Unit = { uiState ->
        uiState.show(
            searchButton = binding.findSessionButton,
            progress = binding.progress,
            teacherName = binding.teacherName,
            connectButton = binding.connectButton,
            statusText = binding.statusText,
            retryButton = binding.retryButton
        )
    }

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchSessionBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).provideViewModel(SearchSessionViewModel::class.java)
        binding.name.text = viewModel.fullName()

        binding.exitButton.setOnClickListener {
            (requireActivity() as BackAction).back()
        }

        binding.findSessionButton.setOnClickListener {
            viewModel.findSession()
        }
        binding.retryButton.setOnClickListener {
            viewModel.connectToSession()
        }
        binding.connectButton.setOnClickListener {
            viewModel.connectToSession()
        }
        viewModel.init(isFirstRun = savedInstanceState == null)
        (requireActivity() as BackAction).addCallback(viewLifecycleOwner) {
            viewModel.clear()
            parentFragmentManager.popBackStack()
        }
    }
}