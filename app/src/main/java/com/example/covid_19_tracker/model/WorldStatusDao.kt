package com.example.covid_19_tracker.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface WorldStatusDao {
    @Query("SELECT * FROM  world_status")
    fun getWorldStatus () : LiveData<WorldStatus>

    @Insert(onConflict = REPLACE)
    fun insert(worldStatus: WorldStatus)
}