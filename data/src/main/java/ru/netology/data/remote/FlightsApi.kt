package ru.netology.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.netology.data.remote.request.RequestCodeBody
import ru.netology.data.remote.response.FlightListResponse
import ru.netology.data.remote.response.FlightResponse

interface FlightsApi {
    @POST("avia-service/twirp/aviaapijsonrpcv1.WebAviaService/GetCheap")
    suspend fun getAll(@Body requestCode: RequestCodeBody) : Response<FlightListResponse>
}