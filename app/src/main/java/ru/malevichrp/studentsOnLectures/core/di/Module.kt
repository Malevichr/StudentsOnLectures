package ru.malevichrp.studentsOnLectures.core.di

interface Module<T> {
    fun viewModel(): T
}