package com.lopystudios.carfaxassignment.data.remote.dto

data class AccidentHistory(
    val accidentSummary: List<String>,
    val icon: String,
    val iconUrl: String,
    val text: String
)