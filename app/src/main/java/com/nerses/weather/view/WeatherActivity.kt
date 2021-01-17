package com.nerses.weather.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.nerses.weather.*
import com.nerses.weather.model.LocationModel
import com.nerses.weather.model.mapper.WeatherMapper
import com.nerses.weather.viewmodel.LocationViewModel
import com.nerses.weather.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class WeatherActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var textSuggestMsg: TextView
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val mainViewModel: MainViewModel by viewModel()
    private val locationViewModel: LocationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        swipeRefreshLayout = findViewById(R.id.swipe)
        textSuggestMsg = findViewById(R.id.text_suggest_msg)

        mainViewModel.weatherModel.observe(this, {
            if (it != null) {
                connectionAlert(it)
            }
            swipeRefreshLayout.isRefreshing = false
        })

        mainViewModel.mainRepository.getLocalWeather()?.observe(this, {
            val weatherModel = WeatherMapper().toModel(it)
            if (weatherModel == null) {
                textSuggestMsg.visibility = VISIBLE
            } else {
                textSuggestMsg.visibility = GONE
            }
            swipeRefreshLayout.isRefreshing = false
        })
        swipeRefreshLayout.setOnRefreshListener {
            getData()
        }

        getData()
    }

    private fun checkLocationPermission(): Boolean {
        return if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )
            }
            false
        } else {
            true
        }
    }

    private fun connectionAlert(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(this, R.style.MyDialogStyle)
        alertDialogBuilder.setTitle(R.string.error)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setNegativeButton(R.string._cancel) { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.setPositiveButton(R.string.refresh) { dialog, _ ->
            if (mainViewModel.networkHelper.isNetworkConnected()) {
                getData()
                dialog.dismiss()
            } else {
                alertDialogBuilder.show()
            }
        }
        alertDialogBuilder.create().show()
    }

    private fun getData() {
        if (checkLocationPermission()) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->

                        location?.latitude?.let {latitude ->
                            LocationModel(latitude, location.longitude) }
                            ?.let {loc ->
                            locationViewModel.save(loc)

                    }
                    mainViewModel.fetchWeather(
                        location?.latitude.toString(),
                        location?.longitude.toString(),
                        MINUTELY,
                        METRIC,
                        BuildConfig.API_KEY
                    )
                }
        } else {
            var location = locationViewModel.getLocation()
            if (location == null){
                location = LocationModel(0.0,0.0)
                locationViewModel.save(location)
            }
            mainViewModel.fetchWeather(
                location.latitude.toString(),
                location.longitude.toString(),
                MINUTELY,
                METRIC,
                BuildConfig.API_KEY
            )
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED)
                    ) {
                        getData()
                    }
                } else {
                    checkLocationPermission()
                }
                return
            }
        }
    }

}