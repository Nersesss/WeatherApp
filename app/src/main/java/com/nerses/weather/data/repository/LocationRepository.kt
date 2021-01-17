package com.nerses.weather.data.repository

import com.nerses.weather.data.daos.LocationDao
import com.nerses.weather.model.LocationModel
import com.nerses.weather.model.mapper.LocationMapper

class LocationRepository(
    private val locationDao: LocationDao) {

    fun getLocation(): LocationModel? {
        return locationDao.getLocation()?.let { LocationMapper().toModel(it) }
    }

    suspend fun save(locationModel: LocationModel) {
        locationDao.insert(LocationMapper().toEntity(locationModel))
    }
}