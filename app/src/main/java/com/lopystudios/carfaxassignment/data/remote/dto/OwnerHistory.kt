package com.lopystudios.carfaxassignment.data.remote.dto

data class OwnerHistory(
    val history: List<History>,
    val icon: String,
    val iconUrl: String,
    val text: String
)