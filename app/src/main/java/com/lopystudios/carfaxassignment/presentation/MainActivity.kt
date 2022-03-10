package com.lopystudios.carfaxassignment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lopystudios.carfaxassignment.common.Constants.CAR_KEY
import com.lopystudios.carfaxassignment.domain.model.Car
import com.lopystudios.carfaxassignment.presentation.car_details.CarDetailScreen
import com.lopystudios.carfaxassignment.presentation.car_list.CarListScreen
import com.lopystudios.carfaxassignment.presentation.ui.theme.CarfaxAssignmentTheme
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarfaxAssignmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.CarListScreen.route
                    ) {
                        composable(
                            route = Screens.CarListScreen.route
                        ) {
                            CarListScreen(navController = navController)
                        }
                        composable(
                            route = Screens.CarDetailScreen.route
                        ) { backStackEntry ->
                            val carJson = backStackEntry.arguments?.getString(CAR_KEY)
                            val moshi = Moshi.Builder().build()
                            val jsonAdapter = moshi.adapter(Car::class.java).lenient()
                            val carObject = jsonAdapter.fromJson(carJson)

                            CarDetailScreen(carObject)
                        }
                    }
                }
            }
        }
    }
}
