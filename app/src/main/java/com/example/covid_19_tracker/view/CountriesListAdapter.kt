package com.example.covid_19_tracker.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19_tracker.R
import com.example.covid_19_tracker.model.CountryStatus
import kotlinx.android.synthetic.main.row.*
import kotlinx.android.synthetic.main.row.view.*


class CountriesListAdapter(private val countriesStatus : List<CountryStatus>)  : RecyclerView.Adapter<CountriesListAdapter.CountriesListViewHolder>(){

    class CountriesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesListViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        val view : View =inflater.inflate(R.layout.row, parent, false)
        return CountriesListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countriesStatus.size
    }

    override fun onBindViewHolder(holder: CountriesListViewHolder, position: Int) {
        holder.itemView.countryNameTextView.text = countriesStatus[position].country


    }
}