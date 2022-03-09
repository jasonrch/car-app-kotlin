package com.lopystudios.carfaxassignment.presentation.car_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lopystudios.carfaxassignment.data.remote.dto.CarFax
import com.lopystudios.carfaxassignment.data.remote.dto.Listings
import com.lopystudios.carfaxassignment.presentation.Screens
import com.lopystudios.carfaxassignment.presentation.car_list.components.CarItemComponent

@Composable
fun CarListScreen(
    navController: NavController,
    viewModel: CarListViewModel = hiltViewModel()
) {

    val items by viewModel.items.subscribeAsState(initial = emptyList())

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items) { listing ->
                CarItemComponent(
                    onItemClick = { /*TODO*/ },
                    onCallDealerClick = { /*TODO*/ },
                    listing = listing
                )
            }
        }
    }
}