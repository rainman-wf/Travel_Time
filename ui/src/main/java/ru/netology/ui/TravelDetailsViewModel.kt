package ru.netology.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.netology.domain.model.Flight
import ru.netology.domain.repository.FlightsRepository
import javax.inject.Inject

@HiltViewModel
class TravelDetailsViewModel @Inject constructor(
    private val repository: FlightsRepository
) : ViewModel() {

    private val _flight = MutableLiveData<Flight>()
    val flight: LiveData<Flight> get() = _flight

    val errorState = SingleLiveEvent<Unit>()

    fun getFlightById(id: String) {
        viewModelScope.launch {
            val flight = repository.getById(id) ?: run {
                errorState.postValue(Unit)
                return@launch
            }

            _flight.postValue(flight)
        }
    }

    fun like(id: String) {
        viewModelScope.launch {
            repository.like(id)
            val new = repository.getById(id) ?: run {
                errorState.postValue(Unit)
                return@launch
            }
            _flight.postValue(new)
        }
    }
}