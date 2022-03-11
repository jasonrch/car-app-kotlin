package com.lopystudios.carfaxassignment.data.repository

import com.lopystudios.carfaxassignment.data.cache.CarsDatabase
import com.lopystudios.carfaxassignment.data.remote.CarfaxApi
import com.lopystudios.carfaxassignment.data.remote.dto.Listings
import com.lopystudios.carfaxassignment.domain.model.Car
import com.lopystudios.carfaxassignment.domain.repository.CarfaxRepository
import javax.inject.Inject


class CarfaxRepositoryImpl @Inject constructor(
    private val api: CarfaxApi,
    private val cacheDatabase: CarsDatabase
) : CarfaxRepository {

    private val cacheDatabaseDao = cacheDatabase.carsDao()

    override suspend fun getListings(): List<Listings> {
        return api.getCars().listings
    }

    override suspend fun getCachedCars(): List<Car> {
        return cacheDatabaseDao.getAllCars()
    }

    override suspend fun addCarToCache(car: Car) {
        cacheDatabaseDao.insertCar(car)
    }

    override suspend fun clearCache() {
        cacheDatabaseDao.deleteAllCars()
    }

    override suspend fun getCacheItemsCount(): Int {
        return cacheDatabaseDao.getCount()
    }
}