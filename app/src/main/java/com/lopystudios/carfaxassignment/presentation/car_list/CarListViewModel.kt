package com.lopystudios.carfaxassignment.presentation.car_list

import androidx.lifecycle.ViewModel
import com.lopystudios.carfaxassignment.data.remote.dto.Listings
import com.lopystudios.carfaxassignment.domain.repository.CarfaxRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(
    private val repository: CarfaxRepository
) : ViewModel() {

    val items: Observable<List<Listings>>
        get() {
            val result = repository.getListings()
                .map {
                    it.listings
                }
                .observeOn(Schedulers.single())

            return result
        }
}