package com.example.covid_19_tracker.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19_tracker.R
import com.example.covid_19_tracker.model.CountryStatus
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row.view.*



class CountriesListAdapter(private val countriesStatuses : List<CountryStatus> ,
                           private val countryDetailsListener: CountriesListFragment.CountryDetailsListener,
                           private val countryStatusNotificationListener: CountriesListFragment.CountryStatusNotificationListener)
    : RecyclerView.Adapter<CountriesListAdapter.CountriesListViewHolder>(){


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

        Picasso.get()
            .load(countriesStatuses[position].flagUrl)
            .into(holder.itemView.flagImageView)

        holder.itemView.countryNewCases.text = countriesStatuses[position].todayCases.toString()
        holder.itemView.countryActiveCases.text = countriesStatuses[position].active.toString()
        holder.itemView.countryDeathCases.text = countriesStatuses[position].deaths.toString()
        holder.itemView.countryRecoveredCases.text = countriesStatuses[position].recovered.toString()

        holder.itemView.notificationButton.isChecked = countriesStatuses[position].isSubscriber

        holder.itemView.notificationButton.setOnCheckedChangeListener { checkBox, isChecked ->
            if(isChecked) { //subscribe (update database)
                Log.i(TAG, "notificationButton")
                countriesStatuses[position].isSubscriber = true
            }
            else {  //unsubscribe (update database)
                Log.i(TAG, "notificationButton not checked")
                countriesStatuses[position].isSubscriber = false
            }
            countryStatusNotificationListener.subscribeCountryStatus(countriesStatuses[position])
        }
        holder.itemView.setOnClickListener {
            countryDetailsListener.viewCountryDetails(countriesStatuses[position].country)
            /*Aml*/
            /*val intent = Intent(context , DetailsCountryActivity::class.java)
            intent.putExtra("country_name", countriesStatuses.get(position).country)
            context.startActivity(intent)*/
            /*Aml*/
        }

    }
}