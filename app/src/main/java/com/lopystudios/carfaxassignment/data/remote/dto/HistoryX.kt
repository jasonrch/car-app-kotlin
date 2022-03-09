package com.lopystudios.carfaxassignment.data.remote.dto

data class HistoryX(
    val city: String,
    val date: String,
    val description: String,
    val odometerReading: Int,
    val source: String,
    val state: String
)