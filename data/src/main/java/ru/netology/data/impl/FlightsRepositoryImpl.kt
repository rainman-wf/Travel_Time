package ru.netology.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.netology.data.local.FlightsDao
import ru.netology.data.local.entity.FlightsWithSeatsEntity
import ru.netology.data.local.entity.LocalLikedFlightEntity
import ru.netology.data.remote.FlightsApi
import ru.netology.data.remote.request.RequestCodeBody
import ru.netology.data.utils.apiRequest
import ru.netology.data.utils.dbQuery
import ru.netology.domain.model.Flight
import ru.netology.domain.repository.FlightsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlightsRepositoryImpl @Inject constructor(
    private val dao: FlightsDao,
    private val api: FlightsApi
) : FlightsRepository {

    override val liked: Flow<List<String>> = dao.getAllLiked()

    override val flights: Flow<List<Flight>> =
        dao.getAll().map { it.map(FlightsWithSeatsEntity::toModel) }

    override suspend fun like(id: String) {
        dbQuery { dao.like(LocalLikedFlightEntity(id)) }
    }

    override suspend fun unlike(id: String) {
        dbQuery { dao.unlike(LocalLikedFlightEntity(id)) }
    }

    override suspend fun getById(id: String): Flight? {
        return dbQuery { dao.getFlightById(id)?.toModel() }
    }

    override suspend fun isLiked(id: String): Boolean {
        return withContext(repositoryScope.coroutineContext) {
            dbQuery { dao.getLiked(id) } != null
        }
    }

    override suspend fun load() {
        val response = apiRequest {
            api.getAll(RequestCodeBody())
        }

        val seats = response.flights.map { flight ->
            flight.seats.map { it.toEntity(flight.searchToken) }
        }.flatten()

        val flights = response.flights.map { it.toEntity() }

        dbQuery { dao.insert(flights, seats) }

    }
}