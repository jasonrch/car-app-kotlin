package com.lopystudios.carfaxassignment.data.remote.dto

data class ServiceHistory(
    val history: List<HistoryX>,
    val icon: String,
    val iconUrl: String,
    val number: Int,
    val text: String
)