package com.example.fragments.components

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import com.example.fragments.model.Hotel
import com.example.fragments.service.MemoryRepository
import com.example.fragments.view.HotelListPresenter
import com.example.fragments.view.HotelListView

class HotelListFragment : ListFragment(), HotelListView{
    private val presenter = HotelListPresenter(this, MemoryRepository)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.searchHotels("")
    }

    override fun showHotels(hotels: List<Hotel>) {
        val adapter = ArrayAdapter<Hotel>(requireContext(), android.R.layout.simple_list_item_1, hotels)
        listAdapter = adapter
    }

    override fun showHotelDetails(hotel: Hotel) {
        TODO("Not yet implemented")
    }
}