package ru.malevichrp.studentsOnLectures.fragments.login.chooseProfile.presentation

import ru.malevichrp.studentsOnLectures.core.di.ClearViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel
import ru.malevichrp.studentsOnLectures.fragments.login.chooseProfile.repsoitory.ChooseProfileRepository

class ChooseProfileViewModel(
    private val repository: ChooseProfileRepository,
    private val clearViewModel: ClearViewModel
) : MyViewModel {
    fun toStudentProfile() {
        repository.changeStudentProfile()
        clearViewModel.clear(this.javaClass)
    }

    fun toTeacherProfile() {
        repository.changeTeacherProfile()
        clearViewModel.clear(this.javaClass)
    }

    fun toRegistration() {
        clearViewModel.clear(this.javaClass)
    }
}
