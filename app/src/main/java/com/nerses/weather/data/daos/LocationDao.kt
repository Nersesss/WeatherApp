package com.nerses.weather.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nerses.weather.data.entity.LocationEntity
@Dao
interface LocationDao {

    @Query("SELECT * FROM location ")
    fun getLocation(): LocationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: LocationEntity)
}