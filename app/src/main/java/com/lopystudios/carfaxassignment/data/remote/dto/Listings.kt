package com.lopystudios.carfaxassignment.data.remote.dto

import com.lopystudios.carfaxassignment.domain.model.Car

data class Listings(
    val bodytype: String,
    val currentPrice: Int,
    val dealer: Dealer,
    val drivetype: String,
    val engine: String,
    val exteriorColor: String,
    val fuel: String,
    val id: String,
    val images: Images,
    val interiorColor: String,
    val make: String,
    val mileage: Int,
    val model: String,
    val transmission: String,
    val trim: String,
    val vin: String,
    val year: Int
)

fun Listings.toCar(): Car {
    return Car(
        id = id,
        photoUrl = images.firstPhoto.large,
        year = year,
        make = make,
        model = model,
        trim = trim,
        price = currentPrice,
        mileage = mileage,
        city = dealer.city,
        state = dealer.state,
        dealerPhoneNumber = dealer.phone,
        interiorColor = interiorColor,
        exteriorColor = exteriorColor,
        driveType = drivetype,
        transmission = transmission,
        engine = engine,
        bodyStyle = bodytype,
        fuel = fuel
    )
}