package com.lopystudios.carfaxassignment.presentation

sealed class Screens(val route: String) {
    object CarListScreen: Screens("car_list_screen")
    object CarDetailScreen: Screens("car_detail_screen")
}