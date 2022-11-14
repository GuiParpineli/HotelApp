package com.example.fragments.repository

import com.example.fragments.model.Hotel
import javax.security.auth.callback.Callback

interface HotelRepository {
    fun save(hotal: Hotel)
    fun remove(vararg hotel: Hotel)
    fun hotelById(id: Long, callback: (Hotel?) -> Unit)
    fun search(term: String, callback: (List<Hotel>) -> Unit)
}