package ru.malevichrp.studentsOnLectures.core.data

import android.content.SharedPreferences

interface LongCache {
    fun save(newValue: Long)
    fun read(): Long
    fun increment(): Long
    fun default(): Long
    class Base(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val defaultValue: Long = 0,
    ) : LongCache {
        override fun save(newValue: Long) {
            sharedPreferences.edit().putLong(key, newValue).apply()
        }

        override fun read(): Long {
            return sharedPreferences.getLong(key, defaultValue)
        }

        override fun increment(): Long {
            val newValue = read() + 1
            save(newValue)
            return newValue
        }

        override fun default(): Long {
            save(defaultValue)
            return defaultValue
        }
    }
}