package com.example.covid_19_tracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.covid_19_tracker.R
import com.example.covid_19_tracker.model.WorldStatus
import com.example.covid_19_tracker.viewmodel.WorldStatusViewModel
import kotlinx.android.synthetic.main.fragment_world.*

/**
 * A simple [Fragment] subclass.
 */
class WorldFragment : Fragment() {

    private val TAG = "WorldFragment"
    var worldStatusViewModel: WorldStatusViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_world, container, false)

        worldStatusViewModel = ViewModelProviders.of(this).get(WorldStatusViewModel::class.java)

        return view
    }

    override fun onStart() {
        super.onStart()

        worldStatusViewModel!!.getWorldStatus().observe(this, Observer<WorldStatus>{
            if (it != null) {
                worldTodayNewCases.text = it.todayCases.toString()
                worldTodayDeaths.text = it.todayDeaths.toString()

                worldTotalCases.text = it.cases.toString()
                worldTotalActive.text = it.active.toString()
                worldTotalCritical.text = it.critical.toString()
                worldTotalDeaths.text = it.deaths.toString()
                worldTotalRecovered.text = it.recovered.toString()
                worldTotalTests.text = it.tests.toString()

                world_note.text = world_note.text.toString() + " " + it.affectedCountries.toString()
            }
        })
    }
}
