package com.lopystudios.carfaxassignment.presentation.car_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.lopystudios.carfaxassignment.presentation.Screens
import com.lopystudios.carfaxassignment.presentation.car_list.components.CarItemComponent

@Composable
fun CarListScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        repeat(10) {
            CarItemComponent(
                onItemClick = {
                    navController.navigate(Screens.CarDetailScreen.route)
                },
                onCallDealerClick = {}
            )
        }
    }
}