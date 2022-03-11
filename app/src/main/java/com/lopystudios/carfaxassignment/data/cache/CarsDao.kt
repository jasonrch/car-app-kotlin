package com.lopystudios.carfaxassignment.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lopystudios.carfaxassignment.domain.model.Car

@Dao
interface CarsDao {

    @Query("SELECT * FROM cars")
    suspend fun getAllCars(): List<Car>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: Car)

    @Query("DELETE FROM cars")
    suspend fun deleteAllCars()

    @Query("SELECT COUNT(id) FROM cars")
    suspend fun getCount(): Int
}
