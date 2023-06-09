package ru.netology.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.netology.domain.model.Flight

interface FlightsRepository {
    val flights: Flow<List<Flight>>

    fun like(id: String) : Boolean
    fun unlike(id: String) : Boolean

    fun getById(id: String) : Flight?
}