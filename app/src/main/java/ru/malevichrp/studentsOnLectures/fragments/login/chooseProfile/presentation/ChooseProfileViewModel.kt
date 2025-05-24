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
        clear()
    }

    fun toTeacherProfile() {
        repository.changeTeacherProfile()
        clear()
    }

    fun toRegistration() {
        clear()
    }

    override fun clear() {
        clearViewModel.clear(this.javaClass)
    }
}
