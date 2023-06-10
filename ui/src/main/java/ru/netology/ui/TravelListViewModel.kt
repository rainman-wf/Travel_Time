package ru.netology.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.netology.domain.model.Flight
import ru.netology.domain.repository.FlightsRepository
import javax.inject.Inject

@HiltViewModel
class TravelListViewModel @Inject constructor(
    private val repository: FlightsRepository
) : ViewModel() {

    private val flights = repository.flights.asLiveData(viewModelScope.coroutineContext)

    private val likedList = repository.liked.asLiveData(viewModelScope.coroutineContext)

    private val _likableFlights = MediatorLiveData<List<LikableFlight>>(emptyList())
    val likableFlights: LiveData<List<LikableFlight>> get() = _likableFlights

    val loadingState = SingleLiveEvent<LoadingState>()

    init {
        _likableFlights.addSource(flights) {
            _likableFlights.postValue(combineData(it, likedList.value ?: emptyList()))
        }

        _likableFlights.addSource(likedList) {
            _likableFlights.postValue(combineData(flights.value ?: emptyList(), it))
        }

        viewModelScope.launch {
            loadingState.postValue(LoadingState.Load)
            try {
                repository.load()
                loadingState.postValue(LoadingState.Success)
            } catch (e: Exception) {
                loadingState.postValue(LoadingState.Error(e.message.toString()))
            }
        }
    }

    private fun combineData(flights: List<Flight>, ids: List<String>): List<LikableFlight> {
        return flights.map {
            LikableFlight(
                it,
                ids.contains(it.searchToken)
            )
        }
    }

    fun like(flightId: String) {
        val id = likedList.value!!.singleOrNull { it == flightId }
        viewModelScope.launch {
            id?.let { repository.like(flightId) } ?: repository.unlike(flightId)
        }
    }
}