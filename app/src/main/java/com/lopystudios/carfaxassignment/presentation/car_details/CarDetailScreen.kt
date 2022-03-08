package com.lopystudios.carfaxassignment.presentation.car_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lopystudios.carfaxassignment.presentation.car_details.components.CarDetailComponent

@Composable
fun CarDetailScreen() {

    Box(modifier = Modifier.fillMaxSize()) {
        CarDetailComponent()
    }
}