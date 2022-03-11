package com.lopystudios.carfaxassignment.presentation.car_list

import com.lopystudios.carfaxassignment.domain.model.Car

data class CarListState(
    val isLoading: Boolean = false,
    val cars: List<Car> = emptyList(),
    val error: String = ""
)