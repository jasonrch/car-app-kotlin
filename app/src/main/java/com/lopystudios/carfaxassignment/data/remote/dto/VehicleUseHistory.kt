package com.lopystudios.carfaxassignment.data.remote.dto

data class VehicleUseHistory(
    val history: List<HistoryXX>,
    val icon: String,
    val iconUrl: String,
    val text: String
)