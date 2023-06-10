package ru.netology.data.utils

import retrofit2.Response
import ru.netology.domain.model.ApiError
import ru.netology.domain.model.DatabaseError
import ru.netology.domain.model.NetworkError
import java.net.SocketException

suspend fun <T>dbQuery(query: suspend () -> T) : T {
    return try {
        query()
    } catch (e: Exception) {
        throw DatabaseError
    }
}

suspend fun <T> apiRequest(request: suspend () -> Response<T>): T {

    val response = try {
        request()
    } catch (e: SocketException) {
        throw NetworkError
    } catch (e: Exception) {
        throw NetworkError
    }
    if (!response.isSuccessful) throw ApiError(response.code(), response.message())
    return response.body() ?: throw ApiError(0, "null body responses")
}