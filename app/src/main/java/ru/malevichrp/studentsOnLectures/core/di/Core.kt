package ru.malevichrp.studentsOnLectures.core.di

import android.content.Context
import android.content.SharedPreferences
import ru.malevichrp.studentsOnLectures.core.presentation.RunAsync

class Core(
    context: Context,
    val clearViewModel: ClearViewModel
) {
    val runAsync: RunAsync = RunAsync.Base()
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("slAppData", Context.MODE_PRIVATE)

    val runUiTests = false
    val size = 1000
    val sharedCollection = SharedCollection(sharedPreferences)
}

class SharedCollection(sharedPreferences: SharedPreferences) {
}