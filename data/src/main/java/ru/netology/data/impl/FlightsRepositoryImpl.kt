package ru.netology.data.impl

import kotlinx.coroutines.flow.Flow
import ru.netology.domain.model.Flight
import ru.netology.domain.repository.FlightsRepository

class FlightsRepositoryImpl: FlightsRepository {

    override val flights: Flow<List<Flight>>
        get() = TODO("Not yet implemented")

    override fun like(id: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun unlike(id: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getById(id: String): Flight? {
        TODO("Not yet implemented")
    }
}