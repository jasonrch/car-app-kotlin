package com.lopystudios.carfaxassignment.domain.repository

import com.lopystudios.carfaxassignment.data.remote.dto.CarFax
import io.reactivex.rxjava3.core.Observable


interface CarfaxRepository {

    fun getListings(): Observable<CarFax>
}