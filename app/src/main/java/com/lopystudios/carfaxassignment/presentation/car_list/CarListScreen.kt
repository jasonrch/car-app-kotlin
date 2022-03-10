package com.lopystudios.carfaxassignment.presentation.car_list

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.lopystudios.carfaxassignment.common.Constants.CAR_KEY
import com.lopystudios.carfaxassignment.data.remote.dto.toCar
import com.lopystudios.carfaxassignment.domain.model.Car
import com.lopystudios.carfaxassignment.presentation.Screens
import com.lopystudios.carfaxassignment.presentation.car_list.components.CarItemComponent
import com.squareup.moshi.Moshi
import java.net.URLEncoder


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CarListScreen(
    navController: NavController,
    viewModel: CarListViewModel = hiltViewModel()
) {

    val items by viewModel.items.subscribeAsState(initial = emptyList())

    val context = LocalContext.current
    val phonePermissionState = rememberPermissionState(
        android.Manifest.permission.CALL_PHONE
    )

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items.map { it.toCar() }) { car ->

                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter(Car::class.java).lenient()
                val carJson = jsonAdapter.toJson(car)
                val carJsonEncoded = URLEncoder.encode(carJson, "utf-8")

                CarItemComponent(
                    onItemClick = {
                        navController.navigate(
                            Screens.CarDetailScreen.route.replace("{$CAR_KEY}", carJsonEncoded)
                        )
                    },
                    onCallDealerClick = {
                        if (phonePermissionState.status.isGranted) {
                            val intent = Intent(
                                Intent.ACTION_CALL,
                                Uri.parse("tel:" + car.dealerPhoneNumber)
                            )
                            context.startActivity(intent)

                        } else {
                            phonePermissionState.launchPermissionRequest()
                        }
                    },
                    car = car
                )
            }
        }
    }
}