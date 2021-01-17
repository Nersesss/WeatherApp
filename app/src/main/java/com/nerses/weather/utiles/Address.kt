package com.nerses.weather.utiles

import android.content.Context
import android.location.Address
import android.location.Geocoder
import java.io.IOException
import java.util.*

    fun getAddress(context: Context, latitude: Double, longitude: Double): String? {
        var addresses: List<Address>? = null
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return if (addresses != null && addresses.isNotEmpty()) {
            addresses[0].locality
        } else {
            null
        }
    }

