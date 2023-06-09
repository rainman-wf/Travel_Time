package ru.netology.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flights")
data class FlightEntity(
    @PrimaryKey
    @ColumnInfo(name = "search_token") val searchToken: String,
    val price: Int,
    @ColumnInfo(name = "service_class") val serviceClass: String,
    @ColumnInfo(name = "start_city") val startCity: String,
    @ColumnInfo(name = "start_date") val startDate: String,
    @ColumnInfo(name = "start_location_code") val startLocationCode: String,
    @ColumnInfo(name = "end_city") val endCity: String,
    @ColumnInfo(name = "end_date") val endDate: String,
    @ColumnInfo(name = "end_location_code") val endLocationCode: String
)