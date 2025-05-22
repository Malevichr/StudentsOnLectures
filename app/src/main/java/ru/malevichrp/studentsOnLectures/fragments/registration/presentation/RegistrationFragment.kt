package ru.malevichrp.studentsOnLectures.fragments.registration.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.AbstractFragment
import ru.malevichrp.studentsOnLectures.core.presentation.BackAction
import ru.malevichrp.studentsOnLectures.databinding.FragmentRegistrationBinding
import ru.malevichrp.studentsOnLectures.fragments.registration.data.RegistrationData

class RegistrationFragment :
    AbstractFragment.Async<RegistrationUiState, RegistrationViewModel, FragmentRegistrationBinding>() {
    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegistrationBinding = FragmentRegistrationBinding.inflate(inflater, container, false)

    override val update: (RegistrationUiState) -> Unit = { uiState ->
        uiState.show(binding.nameInput)

        if (uiState.navigateToBack())
            (requireActivity() as BackAction).back()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).provideViewModel(RegistrationViewModel::class.java)
        binding.backButton.setOnClickListener {
            (requireActivity() as BackAction).back()
        }
        binding.registerButton.setOnClickListener {
            val registrationData = RegistrationData(
                fullName = binding.nameInput.inputText(),
                isTeacher = binding.isTeacherSwitch.isChecked
            )
            viewModel.register(registrationData)
        }
    }
}