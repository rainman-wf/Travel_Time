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

    private val _flight = MutableLiveData<Flight?>()
    val flight: LiveData<Flight?> = _flight

    val errorState = SingleLiveEvent<Unit>()

    fun loadFlight(id: String) {
        viewModelScope.launch {
            _flight.postValue(repository.getById(id))
        }
    }

    fun like(id: String) {
        val isLiked = flight.value?.isLiked ?: run {
            errorState.postValue(Unit)
            return
        }
        viewModelScope.launch {
            if (!isLiked) repository.like(id) else repository.unlike(id)
            loadFlight(id)
        }
    }
}