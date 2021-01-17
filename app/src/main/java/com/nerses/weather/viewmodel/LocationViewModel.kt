package com.nerses.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nerses.weather.data.repository.LocationRepository
import com.nerses.weather.model.LocationModel
import kotlinx.coroutines.launch

class LocationViewModel(
   private val locationRepository: LocationRepository,
    ) : ViewModel() {

    fun getLocation():LocationModel?{
        return locationRepository.getLocation()
    }

     fun save(locationModel: LocationModel){
        viewModelScope.launch {
            locationRepository.save(locationModel)
        }
    }
}