package ru.malevichrp.studentsOnLectures.fragments.teacher.group.presentation

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

interface TimeFormater{
    fun format(time: Long): String
    class Base : TimeFormater {
        override fun format(time: Long): String {
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")
            val instant = Instant.ofEpochMilli(time)
            val dateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()
            return dateTime.format(formatter)
        }
    }
}