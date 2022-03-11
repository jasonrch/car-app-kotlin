package com.lopystudios.carfaxassignment.data.remote

import com.lopystudios.carfaxassignment.data.remote.dto.CarFax
import retrofit2.http.GET

interface CarfaxApi {

    @GET("assignment.json")
    suspend fun getCars(): CarFax
}