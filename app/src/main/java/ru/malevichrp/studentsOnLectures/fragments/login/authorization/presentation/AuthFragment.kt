package ru.malevichrp.studentsOnLectures.fragments.login.authorization.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.AbstractFragment
import ru.malevichrp.studentsOnLectures.core.presentation.BackAction
import ru.malevichrp.studentsOnLectures.databinding.FragmentAuthorizationBinding
import ru.malevichrp.studentsOnLectures.fragments.login.authorization.data.AuthData
import ru.malevichrp.studentsOnLectures.fragments.student.searchSession.presentation.NavigateToStudentSearch
import ru.malevichrp.studentsOnLectures.fragments.teacher.NavigateToTeacherGroups

class AuthFragment :
    AbstractFragment.Async<AuthUiState, AuthViewModel, FragmentAuthorizationBinding>() {
    override val update: (AuthUiState) -> Unit = { uiState ->
        uiState.show(binding.nameInput,
            binding.profileText)
        uiState.navigateTeacher(requireActivity() as NavigateToTeacherGroups)
        uiState.navigateStudent(requireActivity() as NavigateToStudentSearch)
    }
    private val onBackCallBack = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            viewModel.clear()
            this.isEnabled = false
        }
    }

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAuthorizationBinding =
        FragmentAuthorizationBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).provideViewModel(AuthViewModel::class.java)
        binding.nameInput.addHint("Full name")

        binding.logInButton.setOnClickListener {
            val authData = AuthData(
                binding.nameInput.inputText()
            )
            viewModel.login(authData)
        }
        binding.backButton.setOnClickListener {
            (requireActivity() as BackAction).back()
        }

        (requireActivity() as BackAction).addBackAction(onBackCallBack)

        update(viewModel.init())
    }
}