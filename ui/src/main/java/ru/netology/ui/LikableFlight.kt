package ru.netology.ui

import ru.netology.domain.model.Flight

data class LikableFlight(
    val flight: Flight,
    val liked: Boolean
)
