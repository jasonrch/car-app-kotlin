package com.lopystudios.carfaxassignment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lopystudios.carfaxassignment.presentation.car_details.CarDetailScreen
import com.lopystudios.carfaxassignment.presentation.car_list.CarListScreen
import com.lopystudios.carfaxassignment.presentation.ui.theme.CarfaxAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarfaxAssignmentTheme {
                // A surface container using the 'background' color from the theme
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
                        ) {
                            CarDetailScreen()
                        }
                    }
                }
            }
        }
    }
}
