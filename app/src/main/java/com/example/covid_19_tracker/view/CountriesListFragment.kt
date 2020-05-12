package com.example.covid_19_tracker.view

import android.content.Intent
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
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.work.WorkInfo
import com.example.covid_19_tracker.detailsCountry.view.DetailsCountryActivity
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

    val countryStatusNotificationListener = object : CountryStatusNotificationListener {
        override fun subscribeCountryStatus(countryStatus: CountryStatus) {
            countriesListViewModel!!.updateCountryStatus(countryStatus)
        }
    }

    val countryDetailsListener = object : CountryDetailsListener{
        override fun viewCountryDetails(countryName: String) {
            val intent = Intent(activity , DetailsCountryActivity::class.java)
            intent.putExtra("country_name", countryName)
            startActivity(intent)
        }
    }

    interface CountryStatusNotificationListener{
        fun subscribeCountryStatus(countryStatus: CountryStatus)
    }

    interface CountryDetailsListener{
        fun viewCountryDetails(countryName: String)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_countries_list, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.countriesStatusRecyclerView)
        recyclerView!!.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(view.context)
        recyclerView!!.layoutManager = layoutManager

        countriesListViewModel = ViewModelProviders.of(this).get(CountriesListViewModel::class.java)

        view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout).setOnRefreshListener {
            var isDone : Boolean = false
            countriesListViewModel!!.updateCountriesStatus().observe(this, Observer<WorkInfo>{
                Log.i(TAG, "Finish loading")

                if(it != null && it.state == WorkInfo.State.ENQUEUED && !isDone){
                    isDone = true
                }else if(it != null && it.state == WorkInfo.State.ENQUEUED && isDone){
                    view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout).isRefreshing = false
                    isDone = false
                }
            })
        }

        return view
    }

    override fun onStart() {
        super.onStart()

        //countriesListViewModel!!.updateCountriesStatus()

        countriesListViewModel!!.getCountriesStatus().observe(this, Observer<List<CountryStatus>>{
            //Log.i(TAG, "herer onStart: "+it.size)
            adapter = CountriesListAdapter(it, countryDetailsListener, countryStatusNotificationListener)
            recyclerView!!.adapter = adapter
        })
    }

}
