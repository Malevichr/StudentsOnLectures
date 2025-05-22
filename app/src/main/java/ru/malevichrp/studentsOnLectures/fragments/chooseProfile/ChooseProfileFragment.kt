package ru.malevichrp.studentsOnLectures.fragments.chooseProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.malevichrp.studentsOnLectures.core.presentation.AbstractFragment
import ru.malevichrp.studentsOnLectures.databinding.FragmentChooseProfileBinding
import ru.malevichrp.studentsOnLectures.fragments.authorization.NavigateToAuth
import ru.malevichrp.studentsOnLectures.fragments.registration.presentation.NavigateToRegistration

class ChooseProfileFragment : AbstractFragment.BindingUi<FragmentChooseProfileBinding>() {
    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChooseProfileBinding =
        FragmentChooseProfileBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registrationButton.setOnClickListener {
            (requireActivity() as NavigateToRegistration).navigateToRegistration()
        }
        binding.teacherButton.setOnClickListener {
            (requireActivity() as NavigateToAuth).navigateToAuth()
        }
        binding.studentButton.setOnClickListener {
            (requireActivity() as NavigateToAuth).navigateToAuth()
        }
    }
}