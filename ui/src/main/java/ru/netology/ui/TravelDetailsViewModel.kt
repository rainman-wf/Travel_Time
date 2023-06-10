package ru.netology.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.netology.common.utils.log
import ru.netology.domain.model.DatabaseError
import ru.netology.domain.repository.FlightsRepository
import javax.inject.Inject

@HiltViewModel
class TravelDetailsViewModel @Inject constructor(
    private val repository: FlightsRepository
) : ViewModel() {

    private val _flight = MutableLiveData<LikableFlight>()
    val flight: LiveData<LikableFlight> get() = _flight

    val errorState = SingleLiveEvent<Unit>()

    fun getFlightById(id: String) {
        viewModelScope.launch {
            val flight = repository.getById(id) ?: run {
                errorState.postValue(Unit)
                return@launch
            }

            val liked = try {
                repository.isLiked(id)
            } catch (e: DatabaseError) {
                log(e.message)
                return@launch
            }
            _flight.postValue(LikableFlight(flight, liked))
        }
    }

    fun like(id: String) {
        viewModelScope.launch {
            val old = _flight.value ?: run {
                errorState.postValue(Unit)
                return@launch
            }
            repository.like(id)
            _flight.postValue(old.copy(liked = repository.isLiked(id)))
        }
    }
}