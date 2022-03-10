package com.lopystudios.carfaxassignment.presentation.car_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lopystudios.carfaxassignment.R
import com.lopystudios.carfaxassignment.domain.model.Car

@Composable
fun CarItemComponent(
    onItemClick: () -> Unit,
    onCallDealerClick: () -> Unit,
    car: Car
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { onItemClick() },
        elevation = 10.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = car.photoUrl,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                error = painterResource(id = R.drawable.ic_error_image)
            )

            PriceAndMileageComponent(car)

            Divider(
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clickable { onCallDealerClick() },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "CALL DEALER",
                    fontSize = 22.sp,
                    color = Color.Blue
                )
            }
        }
    }

}

@Composable
fun PriceAndMileageComponent(car: Car) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = car.baseInfo(),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        //TODO: format price to money and miles to ks
        Text(text = "${car.price} | ${car.mileage} mi", modifier = Modifier.padding(bottom = 8.dp))
        Text(text = car.location())
    }
}
