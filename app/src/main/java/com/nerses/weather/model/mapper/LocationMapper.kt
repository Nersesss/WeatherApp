package com.nerses.weather.model.mapper

import com.nerses.weather.data.entity.LocationEntity
import com.nerses.weather.model.LocationModel

class LocationMapper {

    fun toModel(locationEntity: LocationEntity): LocationModel {
        return LocationModel(locationEntity.latitude, locationEntity.longitude)
    }

    fun toEntity(locationModel: LocationModel): LocationEntity {
        return LocationEntity(1, locationModel.latitude, locationModel.longitude)
    }
}