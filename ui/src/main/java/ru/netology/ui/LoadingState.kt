package ru.netology.ui

sealed interface LoadingState {
    object Load : LoadingState
    object Success: LoadingState
    data class Error(val message: String) : LoadingState
}