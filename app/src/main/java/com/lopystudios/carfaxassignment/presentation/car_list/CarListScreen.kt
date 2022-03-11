package com.lopystudios.carfaxassignment.presentation.car_list

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lopystudios.carfaxassignment.common.Constants.CAR_KEY
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
    val context = LocalContext.current
    val phonePermissionState = rememberPermissionState(
        android.Manifest.permission.CALL_PHONE
    )

    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.refreshCars() },
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.cars) { car ->
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

            if (state.error.isNotBlank()) {
                if (state.cars.isEmpty()) {
                    Text(
                        text = "An unexpected error occurred. Check your internet and swipe down to refresh",
                        Modifier.padding(start = 16.dp, end = 16.dp)
                            .align(Alignment.Center)
                    )
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}