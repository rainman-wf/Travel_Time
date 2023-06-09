package ru.netology.data.remote.request

import ru.netology.domain.model.LocationCode

data class RequestCodeBody(
    val startLocationCode: LocationCode = LocationCode.LED
)
