package com.lopystudios.carfaxassignment.domain.use_case.get_cars

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.lopystudios.carfaxassignment.common.Constants.MAX_CARS_CACHE_SIZE
import com.lopystudios.carfaxassignment.common.Resource
import com.lopystudios.carfaxassignment.data.remote.dto.toCar
import com.lopystudios.carfaxassignment.domain.model.Car
import com.lopystudios.carfaxassignment.domain.repository.CarfaxRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCars @Inject constructor(
    private val repository: CarfaxRepository,
    @ApplicationContext private val context: Context
) {
    operator fun invoke(): Flow<Resource<List<Car>>> = flow {
        try {
            emit(Resource.Loading<List<Car>>())

            val cars: List<Car>

            if (isInternetAvailable()) {
                cars = repository.getListings().map { it.toCar() }
                updateCacheData(cars = cars)

                emit(Resource.Success<List<Car>>(cars))
            } else {
                cars = repository.getCachedCars()

                emit(Resource.Error<List<Car>>(message = "Using cached cars", cars))
            }
        } catch (e: HttpException) {
            emit(Resource.Error<List<Car>>(e.localizedMessage ?: "An unexpected error occurred", repository.getCachedCars()))
        } catch (e: IOException) {
            emit(Resource.Error<List<Car>>("Couldn't reach server. Check your internet connection.", repository.getCachedCars()))
        }
    }

    private suspend fun updateCacheData(cars: List<Car>) {
        if(cars.isNotEmpty()) {
            //clear the cache everytime we reach a limit of cars saved
            if(shouldDeleteCurrentCache(repository.getCount())) {
                repository.clearCache()
            }

            for(car in cars) {
                repository.addCarToCache(car)
            }
        }
    }

    private fun shouldDeleteCurrentCache(count: Int): Boolean {
        return count > MAX_CARS_CACHE_SIZE
    }


    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        }
        return false
    }
}