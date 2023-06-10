package ru.netology.common.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.fromDateTimeStringToLocalDate() : LocalDate {
    return LocalDateTime.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss xxxx z")).toLocalDate()
}