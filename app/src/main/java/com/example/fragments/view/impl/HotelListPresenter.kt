package com.example.fragments.view.impl

import com.example.fragments.model.Hotel
import com.example.fragments.repository.HotelRepository
import com.example.fragments.view.HotelListView

class HotelListPresenter(
    private val view: HotelListView,
    private val repository: HotelRepository
) {

    fun searchHotels(term: String) {
        repository.search(term) { hotels ->
            view.showHotels(hotels)
        }
    }

    fun showHotelDetails(hotel: Hotel) {
        view.showHotelDetails(hotel)
    }
}