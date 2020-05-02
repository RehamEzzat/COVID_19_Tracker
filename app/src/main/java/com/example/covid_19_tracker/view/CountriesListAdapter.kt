package com.example.covid_19_tracker.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19_tracker.R
import com.example.covid_19_tracker.model.CountryStatus
import kotlinx.android.synthetic.main.row.view.*


class CountriesListAdapter(private val countriesStatuses : List<CountryStatus>)  : RecyclerView.Adapter<CountriesListAdapter.CountriesListViewHolder>(){

    class CountriesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    private val TAG = "CountriesListAdapter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesListViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        val view : View =inflater.inflate(R.layout.row, parent, false)
        return CountriesListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countriesStatuses.size
    }

    override fun onBindViewHolder(holder: CountriesListViewHolder, position: Int) {
        holder.itemView.countryNameTextView.text = countriesStatuses[position].country
        holder.itemView.notificationButton.setOnCheckedChangeListener { checkBox, isChecked ->
            if(isChecked) //subscribe (update database)
                Log.i(TAG, "notificationButton")
            else //unsubscribe (update database)
                Log.i(TAG, "notificationButton not checked")
        }
        holder.itemView.setOnClickListener {
            /*Aml*/
            /*Aml*/
        }

    }
}