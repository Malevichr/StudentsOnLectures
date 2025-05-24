package ru.malevichrp.studentsOnLectures.core

import android.app.Application
import android.util.Log
import ru.malevichrp.studentsOnLectures.core.di.ClearViewModel
import ru.malevichrp.studentsOnLectures.core.di.Core
import ru.malevichrp.studentsOnLectures.core.di.ManageViewModels
import ru.malevichrp.studentsOnLectures.core.di.ProvideViewModel
import ru.malevichrp.studentsOnLectures.core.presentation.MyViewModel

class SLApp : Application(), ProvideViewModel {
    private lateinit var viewModelFactory: ManageViewModels
    override fun onCreate() {
        super.onCreate()
        val clearViewModel = object : ClearViewModel {
            override fun clear(viewModelClass: Class<out MyViewModel>) {
                Log.d("mlvc", "$viewModelClass cleared")
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

