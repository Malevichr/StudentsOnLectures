package ru.malevichrp.studentsOnLectures.fragments.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.AbstractFragment
import ru.malevichrp.studentsOnLectures.core.presentation.BackAction
import ru.malevichrp.studentsOnLectures.databinding.FragmentRegistrationBinding

class RegistrationFragment : AbstractFragment.BindingUi<FragmentRegistrationBinding>() {
    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegistrationBinding = FragmentRegistrationBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel =
            (requireActivity() as ProvideViewModel).provideViewModel(RegistrationViewModel::class.java)
        binding.backButton.setOnClickListener {
            (requireActivity() as BackAction).back()
        }
        binding.registerButton.setOnClickListener {
            val registrationData = RegistrationData(
                fullName = binding.nameInput.text.toString(),
                isTeacher = binding.isTeacherSwitch.isChecked
            )
            viewModel.register(registrationData)
            (requireActivity() as BackAction).back()
        }
    }
}