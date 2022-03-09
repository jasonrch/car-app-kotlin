package com.lopystudios.carfaxassignment.data.repository

import com.lopystudios.carfaxassignment.data.remote.CarfaxApi
import com.lopystudios.carfaxassignment.data.remote.dto.CarFax
import com.lopystudios.carfaxassignment.domain.repository.CarfaxRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


class CarfaxRepositoryImpl @Inject constructor(
    private val api: CarfaxApi
) : CarfaxRepository {

    override fun getListings(): Observable<CarFax> {
        return api.getCars()
    }
}