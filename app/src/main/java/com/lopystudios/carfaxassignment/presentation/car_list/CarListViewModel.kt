package com.lopystudios.carfaxassignment.presentation.car_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lopystudios.carfaxassignment.common.Resource
import com.lopystudios.carfaxassignment.domain.use_case.get_cars.GetCars
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class CarListViewModel @Inject constructor(
    private val getCarsUseCase: GetCars,
) : ViewModel() {

    private val _state = mutableStateOf(CarListState())
    val state: State<CarListState> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    init {
        getCars()
    }

    fun refreshCars() {
        getCars()
    }

    private fun getCars() {
        getCarsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _isRefreshing.value = false
                    _state.value = CarListState(cars = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _isRefreshing.value = false
                    _state.value = CarListState(
                        error = result.message ?: "",
                        cars = result.data ?: emptyList()
                    )
                }
                is Resource.Loading -> {
                    _isRefreshing.value = true
                    _state.value = CarListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}