package com.lopystudios.carfaxassignment.presentation.car_list.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lopystudios.carfaxassignment.R

@Composable
fun CarItemComponent(
    onItemClick: () -> Unit,
    onCallDealerClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { onItemClick() },
        elevation = 10.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.ic_test_image),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop,
            )

            PriceAndMileageComponent("$20,215", "0 mi")

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
fun PriceAndMileageComponent(price: String, mileage: String) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "2022 Jeep Gladiator Willys",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(text = "$price | $mileage", modifier = Modifier.padding(bottom = 8.dp))
        Text(text = "Lyndhurst, NJ")
    }
}

@Preview(showBackground = true)
@Composable
fun CarItemComponentPreview() {
    CarItemComponent(onItemClick = {}, onCallDealerClick = {})
}
