package ru.malevichrp.studentsOnLectures.core.di

import android.content.Context
import android.content.SharedPreferences
import ru.malevichrp.studentsOnLectures.core.data.BooleanCache
import ru.malevichrp.studentsOnLectures.core.data.LongCache
import ru.malevichrp.studentsOnLectures.core.presentation.RunAsync
import ru.malevichrp.studentsOnLectures.data.CacheModule

class Core(
    context: Context,
    val clearViewModel: ClearViewModel
) {
    val runAsync: RunAsync = RunAsync.Base()
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("slAppData", Context.MODE_PRIVATE)
    val sharedCollection = SharedCollection(sharedPreferences)
    val cacheModule = CacheModule.Base(context)
}

class SharedCollection(sharedPreferences: SharedPreferences) {
    val isTeacher = BooleanCache.Base(sharedPreferences, "isTeacher", false)
    val targetTeacherId = LongCache.Base(sharedPreferences, "targetTeacherId", -1)
    val targetGroupId = LongCache.Base(sharedPreferences, "targetGroupId", -1)
}