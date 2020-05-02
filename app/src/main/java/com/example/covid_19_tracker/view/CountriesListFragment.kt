package com.example.covid_19_tracker.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.covid_19_tracker.R
import com.example.covid_19_tracker.viewmodel.CountriesListViewModel
import kotlinx.android.synthetic.main.fragment_countries_list.*
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.covid_19_tracker.model.CountryStatus

/**
 * A simple [Fragment] subclass.
 */
class CountriesListFragment : Fragment() {
    private val TAG = "CountriesListFragment"
    var countriesListViewModel : CountriesListViewModel? = null

    var recyclerView: RecyclerView? = null
    var adapter: CountriesListAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_countries_list, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.countriesStatusRecyclerView)
        recyclerView!!.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(view.context)
        recyclerView!!.layoutManager = layoutManager

        countriesListViewModel = ViewModelProviders.of(this).get(CountriesListViewModel::class.java)

        view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout).setOnRefreshListener {
            countriesListViewModel!!.updateCountriesStatus()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        //countriesListViewModel!!.updateCountriesStatus() /******will be removed, just for testing******/
        countriesListViewModel!!.getCountriesStatus().observe(this, Observer<List<CountryStatus>>{
            Log.i(TAG, "herer onStart: "+it.size)
            adapter = CountriesListAdapter(it)
            recyclerView!!.adapter = adapter
        })
    }

}
