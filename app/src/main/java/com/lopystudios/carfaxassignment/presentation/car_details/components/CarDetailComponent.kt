package com.lopystudios.carfaxassignment.presentation.car_details.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lopystudios.carfaxassignment.R

@Composable
fun CarDetailComponent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_test_image),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "2022 Jeep Gladiator Willys",
            modifier = Modifier.padding(bottom = 8.dp, start = 50.dp, top = 12.dp)
        )

        Text(
            text = "$21,045 | 64.2k mi",
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

        VehicleInfoComponent()

        Divider(
            thickness = 1.dp,
            modifier = Modifier
                .padding(top = 40.dp, bottom = 40.dp)

        )

        Button(
            onClick = { },
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
fun VehicleInfoComponent() {
    Column {
        VehicleInfoItemComponent("Location", "New York, NY")
        VehicleInfoItemComponent("Exterior Color", "Gray")
        VehicleInfoItemComponent("Interior Color", "Black")
        VehicleInfoItemComponent("Drive Type", "4x4")
        VehicleInfoItemComponent("Transmission", "Automatic")
        VehicleInfoItemComponent("Body Style", "Pickup Truck")
        VehicleInfoItemComponent("Engine", "6 Cyl 3.7 L")
        VehicleInfoItemComponent("Fuel", "Diesel")
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

@Preview
@Composable
fun CarDetailComponentPreview() {
    CarDetailComponent()
}