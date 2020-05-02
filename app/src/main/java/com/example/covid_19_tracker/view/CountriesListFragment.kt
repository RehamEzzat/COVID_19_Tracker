package com.example.covid_19_tracker.view

import android.os.Bundle
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
import com.example.covid_19_tracker.model.CountryStatus

/**
 * A simple [Fragment] subclass.
 */
class CountriesListFragment : Fragment() {
    var countriesListViewModel : CountriesListViewModel? = null

    var adapter: CountriesListAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_countries_list, container, false)

        countriesStatusRecyclerView.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(view.context)
        countriesStatusRecyclerView.layoutManager = layoutManager

        countriesListViewModel = ViewModelProviders.of(this).get(CountriesListViewModel::class.java) // try it in lazy block

        return view
    }

    override fun onStart() {
        super.onStart()
        countriesListViewModel!!.getCountriesStatus().observe(this, Observer<List<CountryStatus>>{
            adapter = CountriesListAdapter(it)
        })
    }

}
