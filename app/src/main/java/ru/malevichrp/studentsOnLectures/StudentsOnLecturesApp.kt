package ru.malevichrp.studentsOnLectures

import android.app.Application
import ru.malevichrp.studentsOnLectures.core.di.ClearViewModel
import ru.malevichrp.studentsOnLectures.core.di.Core
import ru.malevichrp.studentsOnLectures.core.di.ManageViewModels
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel

class StudentsOnLecturesApp : Application(), ProvideViewModel {
    private lateinit var viewModelFactory: ManageViewModels
    override fun onCreate() {
        super.onCreate()
        val clearViewModel = object : ClearViewModel {
            override fun clear(viewModelClass: Class<out MyViewModel>) {
                viewModelFactory.clear(viewModelClass)
            }
        }
        val core = Core(this, clearViewModel)
        val make = ProvideViewModel.Make(core)
        viewModelFactory = ManageViewModels.Factory(make)
    }

    override fun <T : MyViewModel> provideViewModel(clazz: Class<T>): T {
        return viewModelFactory.provideViewModel(clazz)
    }
}
