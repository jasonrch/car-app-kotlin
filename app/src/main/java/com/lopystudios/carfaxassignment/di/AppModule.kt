package com.lopystudios.carfaxassignment.di

import android.app.Application
import androidx.room.Room
import com.lopystudios.carfaxassignment.common.Constants
import com.lopystudios.carfaxassignment.data.cache.CarsDatabase
import com.lopystudios.carfaxassignment.data.remote.CarfaxApi
import com.lopystudios.carfaxassignment.data.repository.CarfaxRepositoryImpl
import com.lopystudios.carfaxassignment.domain.repository.CarfaxRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCarfaxAPi(): CarfaxApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CarfaxApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEmployeesRepository(
        api: CarfaxApi,
        cachedDb: CarsDatabase
    ): CarfaxRepository {
        return CarfaxRepositoryImpl(api = api, cacheDatabase = cachedDb)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): CarsDatabase =
        Room.databaseBuilder(app, CarsDatabase::class.java, "cars_database")
            .build()
}