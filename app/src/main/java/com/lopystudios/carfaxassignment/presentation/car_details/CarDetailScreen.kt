package com.lopystudios.carfaxassignment.presentation.car_details

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.lopystudios.carfaxassignment.domain.model.Car
import com.lopystudios.carfaxassignment.presentation.car_details.components.CarDetailComponent

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CarDetailScreen(
    car: Car? = null
) {

    val context = LocalContext.current
    val phonePermissionState = rememberPermissionState(
        Manifest.permission.CALL_PHONE
    )

    Box(modifier = Modifier.fillMaxSize()) {
        if (car != null) {
            CarDetailComponent(
                car = car,
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
                }
            )
        }
    }
}