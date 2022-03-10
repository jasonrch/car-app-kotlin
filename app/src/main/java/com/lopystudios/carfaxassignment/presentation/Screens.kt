package com.lopystudios.carfaxassignment.presentation

sealed class Screens(val route: String) {
    object CarListScreen: Screens("car-list-screen")
    object CarDetailScreen: Screens("car-detail-screen/car={car}")
}