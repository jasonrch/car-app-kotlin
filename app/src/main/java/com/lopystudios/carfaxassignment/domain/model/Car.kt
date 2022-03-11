package com.lopystudios.carfaxassignment.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(
        @PrimaryKey val id: String,
        val photoUrl: String,
        val year: Int,
        val make: String,
        val model: String,
        val trim: String,
        val price: Int,
        val mileage: Int,
        val city: String,
        val state: String,
        val dealerPhoneNumber: String,
        val interiorColor: String,
        val exteriorColor: String,
        val driveType: String,
        val transmission: String,
        val engine: String,
        val bodyStyle: String,
        val fuel: String
) {
        fun baseInfo() = "$year $make $model $trim"

        fun location() = "$city, $state"
}
