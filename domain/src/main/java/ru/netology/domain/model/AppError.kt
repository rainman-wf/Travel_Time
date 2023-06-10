package ru.netology.domain.model

sealed class AppError : RuntimeException()

data class ApiError(val code: Int, override val message: String) : AppError()
data class DatabaseError(override val message: String) : AppError()
object NetworkError: AppError()
object WhoKnowsError : AppError()