package ru.malevichrp.studentsOnLectures.fragments.login.chooseProfile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.AbstractFragment
import ru.malevichrp.studentsOnLectures.databinding.FragmentChooseProfileBinding
import ru.malevichrp.studentsOnLectures.fragments.login.authorization.presentation.NavigateToAuth
import ru.malevichrp.studentsOnLectures.fragments.login.registration.presentation.NavigateToRegistration

class ChooseProfileFragment : AbstractFragment.BindingUi<FragmentChooseProfileBinding>() {
    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChooseProfileBinding =
        FragmentChooseProfileBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: ChooseProfileViewModel =
            (requireActivity() as ProvideViewModel).provideViewModel(ChooseProfileViewModel::class.java)

        binding.registrationButton.setOnClickListener {
            viewModel.toRegistration()
            (requireActivity() as NavigateToRegistration).navigateToRegistration()
        }
        binding.teacherButton.setOnClickListener {
            viewModel.toTeacherProfile()
            (requireActivity() as NavigateToAuth).navigateToAuth()
        }
        binding.studentButton.setOnClickListener {
            viewModel.toStudentProfile()
            (requireActivity() as NavigateToAuth).navigateToAuth()
        }
    }
}