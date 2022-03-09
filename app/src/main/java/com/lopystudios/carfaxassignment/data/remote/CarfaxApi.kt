package com.lopystudios.carfaxassignment.data.remote

import com.lopystudios.carfaxassignment.data.remote.dto.CarFax
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface CarfaxApi {

    @GET("assignment.json")
    fun getCars(): Observable<CarFax>
}