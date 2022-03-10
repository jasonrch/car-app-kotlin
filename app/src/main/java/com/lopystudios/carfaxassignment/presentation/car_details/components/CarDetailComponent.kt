package com.lopystudios.carfaxassignment.presentation.car_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lopystudios.carfaxassignment.R
import com.lopystudios.carfaxassignment.domain.model.Car

@Composable
fun CarDetailComponent(
    car: Car,
    onCallDealerClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        AsyncImage(
            model = car.photoUrl,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_launcher_background)
        )

        Text(
            text = car.baseInfo().replace('+', ' '),
            modifier = Modifier.padding(bottom = 8.dp, start = 50.dp, top = 12.dp)
        )

        Text(
            text = "$${car.price} | ${car.mileage} mi",
            modifier = Modifier.padding(bottom = 8.dp, start = 50.dp),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 24.sp
        )

        Divider(thickness = 1.dp)

        Text(
            text = "Vehicle Info",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp, start = 50.dp),
            fontSize = 18.sp
        )

        VehicleInfoComponent(car = car)

        Divider(
            thickness = 1.dp,
            modifier = Modifier
                .padding(top = 40.dp, bottom = 40.dp)

        )

        Button(
            onClick = { onCallDealerClick() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            ),
            shape = RectangleShape,
        ) {
            Text(
                text = "CALL DEALER",
                fontSize = 22.sp,
            )
        }
    }
}


@Composable
fun VehicleInfoComponent(car: Car) {
    Column {
        VehicleInfoItemComponent("Location", car.location())
        VehicleInfoItemComponent("Exterior Color", car.exteriorColor)
        VehicleInfoItemComponent("Interior Color", car.interiorColor)
        VehicleInfoItemComponent("Drive Type", car.driveType)
        VehicleInfoItemComponent("Transmission", car.transmission)
        VehicleInfoItemComponent("Body Style", car.bodyStyle)
        VehicleInfoItemComponent("Engine", car.engine)
        VehicleInfoItemComponent("Fuel", car.fuel)
    }
}

@Composable
fun VehicleInfoItemComponent(type: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = type, color = Color.LightGray)
        }
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = value, color = Color.Black)
        }
    }
}