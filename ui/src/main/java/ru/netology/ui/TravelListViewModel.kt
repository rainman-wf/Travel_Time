package ru.netology.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.netology.domain.repository.FlightsRepository
import javax.inject.Inject

@HiltViewModel
class TravelListViewModel @Inject constructor(
    private val repository: FlightsRepository
) : ViewModel() {

    val flights = repository.flights.asLiveData(viewModelScope.coroutineContext)

    val loadingState = SingleLiveEvent<LoadingState>()

    init {
        viewModelScope.launch {
            loadingState.postValue(LoadingState.Load)
            try {
                repository.load()
                delay(3000)
                loadingState.postValue(LoadingState.Success)
            } catch (e: Exception) {
                loadingState.postValue(LoadingState.Error(e.message.toString()))
            }
        }
    }




    fun like(flightId: String) {
        val isLiked = flights.value!!.singleOrNull { it.searchToken == flightId }?.isLiked ?: kotlin.run {
            loadingState.postValue(LoadingState.Error("Invalid item"))
            return
        }
        viewModelScope.launch {
            if (!isLiked) repository.like(flightId) else repository.unlike(flightId)
        }
    }
}