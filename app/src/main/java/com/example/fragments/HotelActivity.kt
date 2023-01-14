package com.example.fragments

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.example.fragments.components.HotelDetailsFragment
import com.example.fragments.components.HotelListFragment
import com.example.fragments.model.Hotel

class HotelActivity : AppCompatActivity(), HotelListFragment.OnHotelClickListener,
    SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    private var lastSearchTerm: String = ""
    private var searchView: SearchView? = null

    private val listFragment: HotelListFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentList) as HotelListFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_SEARCH_TERM, lastSearchTerm)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        lastSearchTerm = savedInstanceState.getString(EXTRA_SEARCH_TERM) ?: ""
    }

    override fun onHotelClick(hotel: Hotel) {
        if (isTablet()) {
            showDetailsFragment(hotel.id)
        } else {
            showDetailsActivity(hotel.id)
        }
    }

    private fun isTablet() = findViewById<View>(R.id.details) != null

    private fun showDetailsFragment(hotelId: Long) {
        searchView?.setOnQueryTextListener(null)
        val fragment = HotelDetailsFragment.newInstance(hotelId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.details, fragment, HotelDetailsFragment.TAG_DETAILS)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.hotel, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        searchItem?.setOnActionExpandListener(this)
        searchView = searchItem?.actionView as SearchView
        searchView?.queryHint = getString(R.string.hint_search)
        searchView?.setOnQueryTextListener(this)
        if (lastSearchTerm.isNotEmpty()) {
            Handler().post {
                val query = lastSearchTerm
                searchItem.expandActionView()
                searchView?.setQuery(query, true)
                searchView?.clearFocus()
            }
        }
        return true
    }

    private fun showDetailsActivity(hotelId: Long) {
        HotelDetailsActivity.open(this, hotelId)
    }

    override fun onQueryTextSubmit(p0: String?) = true

    override fun onQueryTextChange(newText: String?): Boolean {
        lastSearchTerm = newText ?: ""
        listFragment.search(lastSearchTerm)
        return true
    }

    override fun onMenuItemActionExpand(item: MenuItem): Boolean = true

    override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
        lastSearchTerm = ""
        listFragment.clearSearch()
        return true
    }

    companion object {
        const val EXTRA_SEARCH_TERM = "lastSearch"
    }
}