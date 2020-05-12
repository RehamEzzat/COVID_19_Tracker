package com.example.covid_19_tracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.covid_19_tracker.model.Repository
import com.example.covid_19_tracker.model.WorldStatus

class WorldStatusViewModel (application: Application) : AndroidViewModel(application){
    private val repository: Repository by lazy {
        Repository(application)
    }

    fun getWorldStatus(): LiveData<WorldStatus> {
        return repository.getLocalWorldStatus()
    }
}