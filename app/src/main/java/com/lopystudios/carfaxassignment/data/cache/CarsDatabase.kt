package com.lopystudios.carfaxassignment.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lopystudios.carfaxassignment.data.remote.dto.Listings
import com.lopystudios.carfaxassignment.domain.model.Car

@Database(entities = [Car::class], version = 1)
abstract class CarsDatabase : RoomDatabase() {

    abstract fun carsDao(): CarsDao
}