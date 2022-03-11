package com.lopystudios.carfaxassignment.domain.repository

import com.lopystudios.carfaxassignment.data.remote.dto.Listings
import com.lopystudios.carfaxassignment.domain.model.Car


interface CarfaxRepository {

    suspend fun getListings(): List<Listings>

    suspend fun getCachedCars(): List<Car>

    suspend fun addCarToCache(car: Car)

    suspend fun clearCache()

    suspend fun getCacheItemsCount(): Int
}