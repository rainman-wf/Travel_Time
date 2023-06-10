package ru.netology.domain.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import ru.netology.domain.model.Flight

interface FlightsRepository {

    val repositoryScope: CoroutineScope get() = CoroutineScope(Dispatchers.Default)

    val flights: Flow<List<Flight>>
    val liked: Flow<List<String>>

    suspend fun like(id: String)
    suspend fun unlike(id: String)

    suspend fun getById(id: String): Flight?

    suspend fun load()
}